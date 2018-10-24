package org.warnotte.waxaudiomiditools.MidiToSynth;
import javax.sound.midi.ShortMessage;



public class MidiFilter implements Filter
{
	private int note_min;
	private int note_max;
	private int canal;
	
	public MidiFilter(int canal, int note_min, int note_max)
	{
		this.setCanal(canal);
		this.setNote_max(note_max);
		this.setNote_min(note_min);
	}

	public boolean isFiltered(Object o)
	{
		if ((o instanceof ShortMessage)==false)
			return false;
		ShortMessage sm = (ShortMessage)o;
		int chan = sm.getChannel();
		int note = sm.getData1();
		//int vel = sm.getData2();
		
	//	System.err.println("filter : "+chan+" note="+note);
			
		if ((getCanal()==-1) || (chan==getCanal()))
		if ((getNote_min()==-1) ||(getNote_max()==-1) || ((note>=getNote_min()) && (note<getNote_max())))
			return true;
		return false;
	}

	public void setCanal(int canal) {
		this.canal = canal;
	}

	public int getCanal() {
		return canal;
	}

	public void setNote_min(int note_min) {
		this.note_min = note_min;
	}

	public int getNote_min() {
		return note_min;
	}

	public void setNote_max(int note_max) {
		this.note_max = note_max;
	}

	public int getNote_max() {
		return note_max;
	}

}
