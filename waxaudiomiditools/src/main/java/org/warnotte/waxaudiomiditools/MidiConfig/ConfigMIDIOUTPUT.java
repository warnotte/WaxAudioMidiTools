package org.warnotte.waxaudiomiditools.MidiConfig;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import org.jouvieje.FmodEx.Examples.MidiCommon;

public class ConfigMIDIOUTPUT extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JComboBox<retourMidiConfig> jComboBox_OUT = null;

	private JButton jButton_OK = null;

	private JLabel jLabel_OUT = null;

	/**
	 * @param owner
	 */
	public ConfigMIDIOUTPUT(Frame owner) {
		super(owner,"Configuration MIDI Out",true);
		initialize();
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 1;
			jLabel_OUT = new JLabel();
			jLabel_OUT.setText("Out");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(0, 0, 1, 0);
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.ipadx = 112;
			gridBagConstraints2.ipady = 76;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.ipadx = -3;
			gridBagConstraints.ipady = 61;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridx = 1;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJComboBox_OUT(), gridBagConstraints);
			jContentPane.add(getJButton_OK(), gridBagConstraints2);
			jContentPane.add(jLabel_OUT, gridBagConstraints4);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBox_OUT	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox<retourMidiConfig> getJComboBox_OUT() {
		if (jComboBox_OUT == null) {
			
			Vector<retourMidiConfig> list = MidiCommon.listDevicesAndExit(false, true, false);
			jComboBox_OUT = new JComboBox<retourMidiConfig>(list);
			jComboBox_OUT.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER)
					{
						setVisible(false);
						dispose();
					}
				}
			});
		}
		return jComboBox_OUT;
	}
	
	

	 

	  void jButtonCANCEL_actionPerformed(ActionEvent e) {
		  setVisible(false);
			dispose();
	  }

	/**
	 * This method initializes jButton_OK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_OK() {
		if (jButton_OK == null) {
			jButton_OK = new JButton();
			jButton_OK.setText("Accept");
			jButton_OK.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
					dispose();
				}
			});
			
		}
		return jButton_OK;
	}

	
	public static void main(String[] args)
	{
		ConfigMIDIOUTPUT md1 = new ConfigMIDIOUTPUT(new JFrame());
		md1.pack();
		md1.setVisible(true);
		retourMidiConfig retour = md1.getDonnees();
		System.err.println("IN name : "+retour.getInfos());
		System.err.println("IN idx  : "+retour.getIndex());
		
	}

	public retourMidiConfig getDonnees() {
		
	
		return 
				(retourMidiConfig) jComboBox_OUT.getSelectedObjects()[0];
				
	}
	
	public static Receiver createWindow(Receiver oldtransmitter) throws MidiUnavailableException
	{
		return configReceiver(oldtransmitter);
	}
	
	public static Receiver configReceiver(Receiver oldtransmitter) throws MidiUnavailableException
	{
		ConfigMIDIOUTPUT md1 = new ConfigMIDIOUTPUT(new JFrame());
		md1.pack();
		md1.setVisible(true);
		retourMidiConfig retour = md1.getDonnees();
		
		System.err.println("IN name : "+retour.getInfos());
		System.err.println("IN idx  : "+retour.getIndex());
		
		if (oldtransmitter!=null)
			oldtransmitter.close();
	
		int index = new Integer(""+retour.getIndex());
		Info info = MidiCommon.getMidiDeviceInfo(index);
		
		System.err.println("Output selected ["+retour.getInfos()+"] ?== "+info.getName()+" , "+info.getVendor()+", "+info.getDescription());
		//Info info = MidiCommon.getMidiDeviceInfo(""+retour[0], true);
		
		MidiDevice device = MidiSystem.getMidiDevice(info);
		device.open();
		oldtransmitter = device.getReceiver();
		return oldtransmitter;
	}
}
