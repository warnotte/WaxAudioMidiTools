package org.warnotte.waxaudiomiditools.MidiToSynth.GUI;

import javax.swing.table.AbstractTableModel;

import org.warnotte.waxaudiomiditools.MidiToSynth.MidiToSynth;


public abstract class DBTableModelMidiToSynthAbstract extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1274468057173821593L;
	private MidiToSynth db_table;
	protected String[] columnNames = null;
	
	protected DBTableModelMidiToSynthAbstract(MidiToSynth db_table, String []columnNames)
	{
		this.setDb_table(db_table);
		this.columnNames=columnNames;
	}
	
	public String getColumnName(int col) { 
		if (columnNames==null)
		{
			System.err.println("You forget to set columnname");
			return "Void";
		}
		return columnNames[col].toString();
    }
	
	public int getColumnCount()
	{
		return columnNames.length;
	}

	public abstract int getRowCount();

	public abstract Object getValueAt(int rowIndex, int columnIndex);

	
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col > 0) {
            return true;
        } else {
            return false;
        }
    }

	public void setDb_table(MidiToSynth db_table) {
		this.db_table = db_table;
	}

	public MidiToSynth getDb_table() {
		return db_table;
	}
}