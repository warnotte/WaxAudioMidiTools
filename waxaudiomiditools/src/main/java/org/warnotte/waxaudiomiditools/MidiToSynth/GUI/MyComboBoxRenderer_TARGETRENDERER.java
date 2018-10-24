package org.warnotte.waxaudiomiditools.MidiToSynth.GUI;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.warnotte.waxaudiomiditools.MidiToSynth.TARGET_DESTINATION;


public class MyComboBoxRenderer_TARGETRENDERER implements TableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JComboBox<Object>box = new JComboBox<Object>(TARGET_DESTINATION.values());
		box.setSelectedItem(value);
		return box;
	}

}
