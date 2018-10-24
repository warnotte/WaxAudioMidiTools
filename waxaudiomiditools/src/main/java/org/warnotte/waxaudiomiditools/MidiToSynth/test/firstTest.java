package org.warnotte.waxaudiomiditools.MidiToSynth.test;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

import org.warnotte.waxaudiomiditools.MidiToSynth.MidiToSynth;

public class firstTest implements Receiver {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
	}
	
	@SuppressWarnings("resource")
	public firstTest() throws Exception
	{
		MidiToSynth mts=null;
		mts = new MidiToSynth(this);
		mts.configMidi();
		mts.createVcaFrame();
		mts.createOtherFrame();
		
		//mts.close();
		while(true)
		{
			mts.evolue();
			for (int i = 0 ; i < 16;i++)
			{
			System.err.println("Val["+i+"]="+mts.getSynth().getValue(i));
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void send(MidiMessage message, long timeStamp) {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

}
