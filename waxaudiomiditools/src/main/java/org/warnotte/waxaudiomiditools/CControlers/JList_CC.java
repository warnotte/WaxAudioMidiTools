package org.warnotte.waxaudiomiditools.CControlers;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class JList_CC extends JList<ControlElement> {

	DefaultListModel<ControlElement> dlm = new DefaultListModel<ControlElement>();
	KnowMidiList kml = new KnowMidiList();
	/**
	 * 
	 */
	private static final long serialVersionUID = -2618982965413059963L;

	public JList_CC(int actualCC)
	{
		kml.init_SYNTH();
		this.setModel(dlm);
		
		for (int i = 0; i < kml.size(); i++) {
			ControlElement ce = kml.get(i);
			dlm.add(i, ce);
		}
		
		ControlElement ce= kml.getByCC(actualCC);
		setSelectedValue(ce, true);
		
	}

	public int getSelectedCC() {
		
		//ControlElement ce= kml.(getSelectedValue());
		ControlElement ce = (ControlElement)getSelectedValue();
		
		return ce.getCC();
	}
}
