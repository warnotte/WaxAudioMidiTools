package org.warnotte.waxaudiomiditools.MidiToSynth.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.warnotte.waxaudiomiditools.MidiToSynth.Filter;
import org.warnotte.waxaudiomiditools.MidiToSynth.MidiFilter;
import org.warnotte.waxaudiomiditools.MidiToSynth.MidiToSynth;
import org.warnotte.waxaudiomiditools.MidiToSynth.Tuple;
import org.warnotte.waxaudiomiditools.MidiToSynth.VCABase;

public class DBTableModelTABLE_MidiFilter extends DBTableModelMidiToSynthAbstract
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -9172760302250409910L;

	public DBTableModelTABLE_MidiFilter(MidiToSynth dbTable)
	{
		super(dbTable, new String [] {"Id","ILVCA","Channel","NoteMin", "NoteMax"});
	}
	
	@Override
	public int getColumnCount()
	{
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Tuple<VCABase, MidiFilter> tuple = getByListIndex(rowIndex);//db_table.getByID(rowIndex); // ???? attention avec ca
		MidiFilter p = (MidiFilter) tuple.getS2();
		VCABase vca = (VCABase) tuple.getS1();
		
		if (p == null)
		{
			System.err.println("Oups " + rowIndex);
			return "999";
		}
		switch (columnIndex)
		{
			case 0:return rowIndex;
			case 1:return vca;
			case 2:return p.getCanal();
			case 3:return p.getNote_min();
			case 4:return p.getNote_max();
			default:return "666";
		}
	
	}

	public Tuple<VCABase, MidiFilter> getByListIndex(int rowIndex) {
		HashMap<VCABase, List<Filter>> map = getDb_table().getSynth().getFilters();
		Set<Entry<VCABase, List<Filter>>> set = map.entrySet();
		Iterator<Entry<VCABase, List<Filter>>> it = set.iterator();
		
		int cpt=0;
		while (it.hasNext())
		{
			Entry<VCABase, List<Filter>> entry = it.next();
			
			VCABase vca = entry.getKey();
			List<Filter> list = entry.getValue();
			for (int i = 0; i < list.size();i++)
			{
				Filter filter = list.get(i);
				if (filter instanceof MidiFilter)
				{
					if (cpt==rowIndex)
						
						return new Tuple<VCABase, MidiFilter>(vca ,(MidiFilter) filter);
					cpt++;
					
					
				}
			}
			
		}
		return null;
		
	}

	public void setValueAt(Object value, int rowIndex, int col) {
		Tuple<VCABase, MidiFilter> tuple = getByListIndex(rowIndex);//db_table.getByID(rowIndex); // ???? attention avec ca
		MidiFilter p = (MidiFilter) tuple.getS2();
		VCABase vca = (VCABase) tuple.getS1();
		
		if (p == null)
		{
			System.err.println("DBTableModelTABLE_MidiFilter::OupsProbleme " + rowIndex);
		}
        switch (col)
		{
        	case 1:
			{
				
				List<Filter> o = (List<Filter>)getDb_table().getSynth().getFilters().get(vca);
				o.remove(p);
				System.err.println("Remove from VCA : "+vca.getName());
				
				List<Filter> n = (List<Filter>)getDb_table().getSynth().getFilters().get(value);
				if (n==null)
					{n = new ArrayList<Filter>();
					getDb_table().getSynth().getFilters().put((VCABase)value, n);
					}
				System.err.println("add to VCA : "+(((VCABase)value).getName()));
				n.add(p);
				
				System.err.println(""+o);
				// supprime le VCA du tuple.
				// Rajout le VCA du tuple.
				break;
			}

        	case 2: p.setCanal(new Float(""+value).intValue());break;
			case 3: p.setNote_min(new Float(""+value).intValue());break;
			case 4: p.setNote_max(new Float(""+value).intValue());break;
		}
        fireTableCellUpdated(rowIndex, col);
    }

	@Override
	public int getRowCount() {
		if ((getDb_table()==null) || (getDb_table().getSynth()==null)) return 0;
		HashMap<VCABase, List<Filter>> map = getDb_table().getSynth().getFilters();
		if (map==null) return 0;
		Set<Entry<VCABase, List<Filter>>> set = map.entrySet();
		Iterator<Entry<VCABase, List<Filter>>> it = set.iterator();
		
		int cpt=0;
		while (it.hasNext())
		{
			Entry<VCABase, List<Filter>> entry = it.next();
//			VCABase vca = entry.getKey();
			List<Filter> list = entry.getValue();
			for (int i = 0; i < list.size();i++)
			{
				Filter filter = list.get(i);
				if (filter instanceof MidiFilter)
					cpt++;
			}
			
		}
		return cpt;
	}



}
