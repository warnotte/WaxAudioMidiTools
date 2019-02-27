package org.warnotte.waxaudiomiditools.MidiToSynth;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.warnotte.OBJ2GUI.ParseurAnnotations;
import org.warnotte.OBJ2GUI.Annotations.GUI_CLASS;
import org.warnotte.waxaudiomiditools.MidiConfig.ConfigMIDIINPUT;
import org.warnotte.waxaudiomiditools.MidiToSynth.GUI.DBTableModelTABLE_MidiCCFilter;
import org.warnotte.waxaudiomiditools.MidiToSynth.GUI.DBTableModelTABLE_MidiFilter;
import org.warnotte.waxaudiomiditools.MidiToSynth.GUI.MyComboBoxRenderer_TARGETRENDERER;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@GUI_CLASS(type = GUI_CLASS.Type.BoxLayout, BoxLayout_property = GUI_CLASS.Type_BoxLayout.Y)
public class MidiToSynth implements Receiver {

//	public SYNTH synth = null;
	public transient Transmitter trans = null;
	transient Transmitter trans_control = null;

	private SYNTH<VCABase> synth=null;
	transient public static boolean learn;
	
	public transient JTable table2;
	public transient JTable table ;
	Receiver parent_receiver = null;
	
	public MidiToSynth(Receiver manage)
	{
		this.parent_receiver=manage;
		setSynth(new SYNTH<VCABase>());
	}
	
	private void refresh_tables_filters() {
		((DBTableModelTABLE_MidiCCFilter)table2.getModel()).fireTableDataChanged();
		((DBTableModelTABLE_MidiFilter)table.getModel()).fireTableDataChanged();
		
	}
	
	public void send(MidiMessage message, long timeStamp) {
		
		if ((message instanceof ShortMessage)==false) return;
		ShortMessage sm1 = ((ShortMessage) message);
	//	System.err.println("Message = "+message);
		
		if ((sm1.getCommand() == ShortMessage.NOTE_ON) || (sm1.getCommand() == ShortMessage.NOTE_OFF)) 
		{
			boolean NOTEON = ((sm1.getCommand() == ShortMessage.NOTE_ON) && (sm1.getData2() != 0));
			ShortMessage sm = ((ShortMessage) message);
			int note = sm.getData1();
			
			if (learn == true)
			{
				for (int i = 0 ; i < getSynth().getVca().size();i++)
				{
					VCABase ttt = getSynth().getVca().get(i);
					if (ttt.isLearn()==true)
					{
						System.err.println("VCABase "+i+" - set to "+note+" noteon:"+NOTEON);
					MidiFilter filtre = new MidiFilter(sm1.getChannel(), note, note+1);
					
					if (getSynth().getFilters().get(ttt)==null)
					{
						ArrayList<Filter> filtersCC_list = new ArrayList<Filter>();
						filtersCC_list.add(filtre);
						getSynth().filters.put(ttt, filtersCC_list);
					}
					else
						((ArrayList<Filter>)getSynth().getFilters().get(ttt)).add(filtre);

					ttt.setLearn(false);
					
					
					
					refresh_tables_filters();
					}
				}
				learn=false;
			}
			else
			AnswerToEvent(message, NOTEON, note);
			
			
		    
		}
		else
		if ((sm1.getCommand()== ShortMessage.CONTROL_CHANGE))
		{
			

			
			//System.err.println("Midi received == "+message+" chan = "+sm1.getChannel()+" time = "+timeStamp+ " note = "+sm1.getData1());
			int CC = sm1.getData1();
			//int Value = sm1.getData2();
			
			if (learn == true)
			{
				for (int i = 0 ; i < getSynth().getVca().size();i++)
				{
					VCABase ttt = getSynth().getVca().get(i);
					if (ttt.isLearn()==true)
					{
						System.err.println("VCABase "+i+" - set to "+CC);
						
						MidiCCFilter filtre = new MidiCCFilter(sm1.getChannel(), CC, TARGET_DESTINATION.AMOUNT);
					
					if (getSynth().getFilters_control().get(ttt)==null)
					{
						ArrayList<Filter> filtersCC_list = new ArrayList<Filter>();
						filtersCC_list.add(filtre);
						getSynth().filters_control.put(ttt, filtersCC_list);
					}
					else
					{
						HashMap<VCABase, List<Filter>> map = getSynth().getFilters_control();
						map.get(ttt).add(filtre);
					}
					ttt.setLearn(false);
					
					refresh_tables_filters();
					}
				}
				learn=false;
			}
			else
			{
			
			//System.err.println("Chan = "+sm1.getChannel()+" CC = "+CC + " === "+Value);
			AnswerToCCEvent(sm1);
			}
			
			parent_receiver.send(message, timeStamp);
		}
		
	}

	

	public void AnswerToEvent(Object message, boolean NOTEON, int note) {
		Set<Entry<VCABase, List<Filter>>> entries = getSynth().getFilters().entrySet();
		for (Iterator<Entry<VCABase, List<Filter>>> iterator = entries.iterator(); iterator.hasNext();)
		{
			Entry<VCABase, List<Filter>> entry = iterator.next();
			VCABase dest_ttt = entry.getKey();
			List<Filter> filters = entry.getValue();
			for (Iterator<Filter> iterator2 = filters.iterator(); iterator2.hasNext();)
			{
				Filter filter = iterator2.next();
				if (filter.isFiltered(message)==true)
					if (NOTEON)
						dest_ttt.noteOn(note);
					else
						dest_ttt.noteOff();
			}
		}
	}
	private void AnswerToCCEvent(ShortMessage message) {
		Set<Entry<VCABase, List<Filter>>> entries = getSynth().getFilters_control().entrySet();
		for (Iterator<Entry<VCABase, List<Filter>>> iterator = entries.iterator(); iterator.hasNext();)
		{
			Entry<VCABase, List<Filter>> entry = iterator.next();
			VCABase dest_ttt = entry.getKey();
			List<Filter> filters = entry.getValue();
			for (Iterator<Filter> iterator2 = filters.iterator(); iterator2.hasNext();)
			{
				//int CC = message.getData1();
				int Value = message.getData2();
				MidiCCFilter filter = (MidiCCFilter) iterator2.next();
				if (filter.isFiltered(message)==true)
				{
					dest_ttt.controlChange(filter.getDestination(), Value);
				}
			}
		}
	}

	public void close() {
		trans.close();
	}
	
	public void createOtherFrame() throws Exception {
		JFrame frame = createVcaFrame();
		frame.setSize(1000,1000);
		frame.setVisible(true);


		// Cr�e la fenetre de config de cette classe.

		createVcaFrame();
		
		
		
		
		final DBTableModelTABLE_MidiFilter model = new DBTableModelTABLE_MidiFilter(this);
		 table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		JFrame frame3 = new JFrame("MidiFilter");
		frame3.setTitle("MidiFilter");
		frame3.setLayout(new BorderLayout());
		frame3.add(new JScrollPane(table), BorderLayout.CENTER);
		table.addMouseListener(new MouseListener(){

			

		
			public void mouseReleased(MouseEvent e) {
				
				if (e.getButton()==2)
				{
				int row = table.convertRowIndexToModel(table.getSelectedRow());
				Tuple<VCABase, MidiFilter> ttt = model.getByListIndex(row);
				System.err.println("VCABase == "+ttt.getS1());
				System.err.println("VCABase == "+ttt.getS2());
				ArrayList<Filter> list = (ArrayList<Filter>) getSynth().filters.get(ttt.getS1());
				boolean ret = list.remove(ttt.getS2());
				System.err.println("Retour delete == "+ret);
				((DBTableModelTABLE_MidiFilter)table.getModel()).fireTableDataChanged();
				}
			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		frame3.setSize(640,480);
		frame3.setVisible(true);
		
		final DBTableModelTABLE_MidiCCFilter model2 = new DBTableModelTABLE_MidiCCFilter(this);
		table2 = new JTable(model2);
		table2.setAutoCreateRowSorter(true);
		TableColumn col = table2.getColumnModel().getColumn(4);
		col.setCellEditor(new DefaultCellEditor(new JComboBox<Object>(TARGET_DESTINATION.values())));
		col.setCellRenderer(new MyComboBoxRenderer_TARGETRENDERER());
		
		table2.addMouseListener(new MouseListener(){

			

			public void mouseReleased(MouseEvent e) {
				
				if (e.getButton()==2)
				{
					
				int row = table2.convertRowIndexToModel(table2.getSelectedRow());
				Tuple<VCABase, MidiCCFilter> ttt = model2.getByListIndex(row);
				System.err.println("VCABase == "+ttt.getS1());
				System.err.println("VCABase == "+ttt.getS2());
				ArrayList<Filter> list = (ArrayList<Filter>) getSynth().filters_control.get(ttt.getS1());
				boolean ret = list.remove(ttt.getS2());
				System.err.println("Retour delete == "+ret);
				((DBTableModelTABLE_MidiCCFilter)table2.getModel()).fireTableDataChanged();
				}
			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		JFrame frame4 = new JFrame();
		frame3.setTitle("MidiCCFilter");
		frame4.setLayout(new BorderLayout());
		frame4.add(new JScrollPane(table2), BorderLayout.CENTER);
		frame4.setSize(640,480);
		frame4.setVisible(true);
	
		/*final DBTableModelTABLE_MAPPINGENTRIES model3 = new DBTableModelTABLE_MAPPINGENTRIES(this);
		table3 = new JTable(model3);
		table3.setAutoCreateRowSorter(true);
		TableColumn col3= table3.getColumnModel().getColumn(4);
		col3.setCellEditor(new DefaultCellEditor(new JComboBox(DrawMode.values())));
		col3.setCellRenderer(new MyComboBoxRenderer_TARGETRENDERER());
		
		
		JFrame frame5 = new JFrame();
		frame5.setTitle("MappingEntries");
		frame5.setLayout(new BorderLayout());
		frame5.add(new JScrollPane(table3), BorderLayout.CENTER);
		frame5.setSize(640,480);
		frame5.setVisible(true);*/
		
	}
	
	public JFrame createVcaFrame() throws Exception {
		return ParseurAnnotations.CreateFrameFromObject("OpenglConfig", this, null);
	}
	
	public void configMidi()
	{
		// Cr�e le midi
		try {
			trans = ConfigMIDIINPUT.createWindow(trans);
		
			
		trans.setReceiver(this);
		
		trans_control = ConfigMIDIINPUT.createWindow(trans_control);
		trans_control.setReceiver(this);
		trans_control.setReceiver(parent_receiver);
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public void load_env(String string) throws IOException {
		File f = new File(string);
		FileInputStream fos = new FileInputStream(f);
		XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);
		@SuppressWarnings("unchecked")
		SYNTH<VCABase> s = (SYNTH<VCABase>) xstream.fromXML(fos);
		setSynth(s);
		fos.close();
		
		//FileName_ENV=XMLfilename;
	}



	
	
	public void save_env(String XMLfilename) throws IOException
	{
		File f = new File(XMLfilename);
		FileOutputStream fos = new FileOutputStream(f);
		XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);
		xstream.toXML(getSynth(), fos);
		fos.flush();
		fos.close();
		//FileName_ENV=XMLfilename;
	}

	public void setSynth(SYNTH<VCABase> synth) {
		this.synth = synth;
	}

	public SYNTH<VCABase> getSynth() {
		return synth;
	}

	public void evolue() {
		synth.evolue();
	}
	

	public void AllNoteOff()
	{
		synth.noteOffAll();
		
	}
	
	public void AllNoteOn()
	{
		synth.noteOnAll();
		
	}

	public void AllValuesToOne()
	{
		synth.AllValuesToOne();
		
	}
}
