package org.warnotte.waxaudiomiditools.MidiToSynth.GUI;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.warnotte.waxaudiomiditools.MidiToSynth.MidiToSynth;


public class MyComboBoxRenderer_VCA implements TableCellRenderer {
	
	public MidiToSynth synth;
	
	public MyComboBoxRenderer_VCA(MidiToSynth synth)
	{
		this.synth = synth;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JComboBox<Object> box = new JComboBox<Object>(synth.getSynth().getVca().toArray());
		box.setSelectedItem(value);
		return box;
	}

}
