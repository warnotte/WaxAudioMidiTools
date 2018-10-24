package org.warnotte.waxaudiomiditools.MidiToSynth;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import org.warnotte.OBJ2GUI.JWPanel;
import org.warnotte.OBJ2GUI.ParseurAnnotations;
import org.warnotte.OBJ2GUI.Annotations.GUI_CLASS;
import org.warnotte.OBJ2GUI.Annotations.GUI_FIELD_TYPE;


@GUI_CLASS(type = GUI_CLASS.Type.BoxLayout, BoxLayout_property = GUI_CLASS.Type_BoxLayout.Y)
public class SYNTH<T extends VCABase> {

	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.LISTLIKE)
	ArrayList<VCABase> vca = new ArrayList<VCABase>();

	HashMap<VCABase, List<Filter>> filters = new HashMap<VCABase, List<Filter>>();
	HashMap<VCABase, List<Filter>> filters_control = new HashMap<VCABase, List<Filter>>();

	public SYNTH() {
		
	//	config1();

	}

	@SuppressWarnings("unused")
	private void config1() {

		ArrayList<Filter> filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(9, 36, 38));
		filters_list.add(new KeyFilter(KeyEvent.VK_A));
		filters.put(vca.get(1), filters_list);

		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(9, 38, 40));
		filters_list.add(new KeyFilter(KeyEvent.VK_Z));
		filters.put(vca.get(2), filters_list);

		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(9, 40, 41));
		filters_list.add(new KeyFilter(KeyEvent.VK_E));
		filters.put(vca.get(3), filters_list);

		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(9, 42, 43));
		filters_list.add(new KeyFilter(KeyEvent.VK_R));
		filters.put(vca.get(4), filters_list);

		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(9, 43, 44));
		filters_list.add(new KeyFilter(KeyEvent.VK_T));
		filters.put(vca.get(5), filters_list);

		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(9, 45, 46));
		filters_list.add(new KeyFilter(KeyEvent.VK_Y));
		filters.put(vca.get(6), filters_list);

		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(9, 46, 47));
		filters_list.add(new KeyFilter(KeyEvent.VK_U));
		filters.put(vca.get(7), filters_list);

		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(9, 49, 50));
		filters_list.add(new KeyFilter(KeyEvent.VK_I));
		filters.put(vca.get(8), filters_list);

		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(9, 51, 52));
		filters_list.add(new KeyFilter(KeyEvent.VK_O));
		filters.put(vca.get(9), filters_list);

		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(0, -1, -1));
		filters_list.add(new KeyFilter(KeyEvent.VK_P));
		filters.put(vca.get(10), filters_list);
		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(1, -1, -1));
		filters_list.add(new KeyFilter(KeyEvent.VK_Q));
		filters.put(vca.get(11), filters_list);
		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(2, -1, -1));
		filters_list.add(new KeyFilter(KeyEvent.VK_S));
		filters.put(vca.get(12), filters_list);
		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(3, -1, -1));
		filters_list.add(new KeyFilter(KeyEvent.VK_D));
		filters.put(vca.get(13), filters_list);
		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(4, -1, -1));
		filters_list.add(new KeyFilter(KeyEvent.VK_F));
		filters.put(vca.get(14), filters_list);
		filters_list = new ArrayList<Filter>();
		filters_list.add(new MidiFilter(5, -1, -1));
		filters_list.add(new KeyFilter(KeyEvent.VK_G));
		filters.put(vca.get(15), filters_list);

		/*
		 * vca.get(0).setColorMode(true); vca.get(1).setColorMode(true);
		 * vca.get(2).setColorMode(true);
		 */

	/*	vca.get(10).setColorMode(false);
		vca.get(11).setColorMode(false);
		vca.get(12).setColorMode(false);
		vca.get(13).setColorMode(false);
		vca.get(14).setColorMode(false);
		vca.get(15).setColorMode(false);


		vca.get(10).setCol(new JWColor(255, 255, 255));
		vca.get(11).setCol(new JWColor(255, 255, 255));
		vca.get(12).setCol(new JWColor(255, 255, 255));
		vca.get(13).setCol(new JWColor(255, 255, 255));
		vca.get(14).setCol(new JWColor(255, 255, 255));
		vca.get(15).setCol(new JWColor(255, 255, 255));*/
		
		/*vca.get(0).setManual(true);
		ArrayList filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(0, 74, 0));
		filters_control.put(vca.get(0), filtersCC_list);
		vca.get(1).setManual(true);
		 filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(1, 74, 0));
		filters_control.put(vca.get(1), filtersCC_list);
		vca.get(2).setManual(true);
		 filtersCC_list = new ArrayList<Filter>();
			filtersCC_list.add(new MidiCCFilter(2, 74, 0));
			filters_control.put(vca.get(2), filtersCC_list);*/
	/*	
		filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(1, 74, 0));
		filters_control.put(vca.get(11), filtersCC_list);
		filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(2, 74, 0));
		filters_control.put(vca.get(12), filtersCC_list);
		filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(3, 74, 0));
		filters_control.put(vca.get(13), filtersCC_list);
		filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(4, 74, 0));
		filters_control.put(vca.get(14), filtersCC_list);
		filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(5, 74, 0));
		filters_control.put(vca.get(15), filtersCC_list);
		*/
		
		vca.get(16).setManual(true);
		vca.get(17).setManual(true);
		vca.get(18).setManual(true);
		vca.get(19).setManual(true);
		/*
		filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(1, 74, 0));
		filters_control.put(vca.get(16), filtersCC_list);
		filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(2, 74, 0));
		filters_control.put(vca.get(17), filtersCC_list);
		filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(3, 74, 0));
		filters_control.put(vca.get(18), filtersCC_list);
		filtersCC_list = new ArrayList<Filter>();
		filtersCC_list.add(new MidiCCFilter(4, 74, 0));
		filters_control.put(vca.get(19), filtersCC_list);*/
	}

	public HashMap<VCABase, List<Filter>> getFilters() {
		return filters;
	}

	public void NoteOn(int note, int channel) {
		vca.get(channel).noteOn(note);

		// System.err.println("synth ON "+channel);
	}

	public float getValue(int channel) {
		return vca.get(channel).getValue();
	}

	public float getNote(int channel) {
		return vca.get(channel).getNotes();
	}

	public void evolue() {
		for (int i = 0; i < vca.size(); i++) {
			vca.get(i).evolue();
		}

	}
/*
	public JWColor getColor(int i) {
		return vca.get(i).getCol();
	}
*/
	public float getAmount(int i) {
		return vca.get(i).getAmount();
	}

	public JFrame createVcaFrame() throws Exception {
		return ParseurAnnotations.CreateFrameFromObject("WR3lighter", this, null);
	}
	public JWPanel createJPanel() throws Exception {
		return (JWPanel) ParseurAnnotations.CreatePanelFromObject("WR3lighter", this);
	}
	
	public synchronized List<VCABase> getVca() {
		return vca;
	}

	public synchronized void setVca(ArrayList<VCABase> vca) {
		this.vca = vca;
	}

	public synchronized HashMap<VCABase, List<Filter>> getFilters_control() {
		return filters_control;
	}

	public void clearRemotes() {
		filters_control.clear();
		filters.clear();
		
	}

	public void noteOnAll() {
		for (int i = 0; i < vca.size(); i++) 
			vca.get(i).noteOn(63);
		
		
	}
	
	public void SyncAll() {
		for (int i = 0; i < vca.size(); i++) 
			vca.get(i).sync();
		
		
	}
	public void noteOffAll() {
		for (int i = 0; i < vca.size(); i++) 
			vca.get(i).noteOff();
		
	}

	
	public void AllValuesToOne()
	{
		for (int i = 0; i < vca.size(); i++) 
		{
			if (vca.get(i).isManual()==false)
			vca.get(i).setAmount(1.0f);
		}
			
	}

}
