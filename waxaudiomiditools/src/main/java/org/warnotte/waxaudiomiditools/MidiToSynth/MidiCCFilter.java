package org.warnotte.waxaudiomiditools.MidiToSynth;
import javax.sound.midi.ShortMessage;


public class MidiCCFilter implements Filter
{

	private int CC;
	private int canal;
	private TARGET_DESTINATION destination;
	
	public MidiCCFilter(int canal, int CC, TARGET_DESTINATION destination)
	{
		this.setCanal(canal);
		this.setCC(CC);
		this.setDestination(destination);
	}

	public boolean isFiltered(Object o)
	{
		if ((o instanceof ShortMessage)==false)
			return false;
		ShortMessage sm = (ShortMessage)o;
		int chan = sm.getChannel();
		int cc = sm.getData1();
	//	int vel = sm.getData2();
		
	//	System.err.println("filter : "+chan+" note="+note);
			
		if ((getCanal()==-1) || (chan==getCanal()))
		if ((getCC()==-1) || (cc==getCC()))
			return true;
		return false;
	}

	public void setCanal(int canal) {
		this.canal = canal;
	}

	public int getCanal() {
		return canal;
	}

	public void setCC(int cC) {
		CC = cC;
	}

	public int getCC() {
		return CC;
	}

	public void setDestination(TARGET_DESTINATION destination) {
		this.destination = destination;
	}

	public TARGET_DESTINATION getDestination() {
		return destination;
	}

}
