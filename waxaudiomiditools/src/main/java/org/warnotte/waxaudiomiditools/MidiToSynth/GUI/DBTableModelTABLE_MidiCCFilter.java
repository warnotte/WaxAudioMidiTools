package org.warnotte.waxaudiomiditools.MidiToSynth.GUI;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.warnotte.waxaudiomiditools.MidiToSynth.Filter;
import org.warnotte.waxaudiomiditools.MidiToSynth.MidiCCFilter;
import org.warnotte.waxaudiomiditools.MidiToSynth.MidiToSynth;
import org.warnotte.waxaudiomiditools.MidiToSynth.TARGET_DESTINATION;
import org.warnotte.waxaudiomiditools.MidiToSynth.Tuple;
import org.warnotte.waxaudiomiditools.MidiToSynth.VCABase;

import java.util.Set;

public class DBTableModelTABLE_MidiCCFilter extends DBTableModelMidiToSynthAbstract
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -9172760302250409910L;

	public DBTableModelTABLE_MidiCCFilter(MidiToSynth dbTable)
	{
		super(dbTable, new String[] { "Id", "ILVCA", "Channel", "CC", "Target" });
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Tuple<VCABase, MidiCCFilter> tuple = getByListIndex(rowIndex);//db_table.getByID(rowIndex); // ???? attention avec ca
		MidiCCFilter p = tuple.getS2();
		VCABase vca = tuple.getS1();

		if (p == null)
		{
			System.err.println("Oups " + rowIndex);
			return "999";
		}
		switch (columnIndex)
		{
			case 0:
				return rowIndex;
			case 1:
				return vca.getName();
			case 2:
				return p.getCanal();
			case 3:
				return p.getCC();
			case 4:
				return p.getDestination();
			default:
				return "666";
		}

	}

	public Tuple<VCABase, MidiCCFilter> getByListIndex(int rowIndex)
	{
		HashMap<VCABase, List<Filter>> map = getDb_table().getSynth().getFilters_control();
		Set<Entry<VCABase, List<Filter>>> set = map.entrySet();
		Iterator<Entry<VCABase, List<Filter>>> it = set.iterator();

		int cpt = 0;
		while (it.hasNext())
		{
			Entry<VCABase, List<Filter>> entry = it.next();

			VCABase vca = entry.getKey();
			List<Filter> list = entry.getValue();
			for (int i = 0; i < list.size(); i++)
			{
				Filter filter = list.get(i);
				if (filter instanceof MidiCCFilter)
				{
					if (cpt == rowIndex)

						return new Tuple<VCABase, MidiCCFilter>(vca, (MidiCCFilter) filter);
					cpt++;

				}
			}

		}
		return null;

	}

	public void setValueAt(Object value, int rowIndex, int col)
	{

		Tuple<VCABase, MidiCCFilter> tuple = getByListIndex(rowIndex);//db_table.getByID(rowIndex); // ???? attention avec ca
		MidiCCFilter p = tuple.getS2();
		//VCABase vca = tuple.getS1();

		if (p == null)
		{
			System.err.println("DBTableModelTABLE_MidiFilter::OupsProbleme " + rowIndex);
		}
		switch (col)
		{
			case 2:
				p.setCanal(new Float("" + value).intValue());
				break;
			case 3:
				p.setCC(new Float("" + value).intValue());
				break;
			case 4:
				p.setDestination((TARGET_DESTINATION) value);
				break;
		}
		fireTableCellUpdated(rowIndex, col);
	}

	@Override
	public int getRowCount()
	{
		if ((getDb_table() == null) || (getDb_table().getSynth() == null))
			return 0;
		HashMap<VCABase, List<Filter>> map = (getDb_table()).getSynth().getFilters_control();
		if (map == null)
			return 0;
		Set<Entry<VCABase, List<Filter>>> set = map.entrySet();
		Iterator<Entry<VCABase, List<Filter>>> it = set.iterator();

		int cpt = 0;
		while (it.hasNext())
		{
			Entry<VCABase, List<Filter>> entry = it.next();
			//VCABase vca = entry.getKey();
			List<Filter> list = entry.getValue();
			for (int i = 0; i < list.size(); i++)
			{
				Filter filter = list.get(i);
				if (filter instanceof MidiCCFilter)
					cpt++;
			}

		}
		return cpt;
	}

}
