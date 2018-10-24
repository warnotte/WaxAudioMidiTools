package org.warnotte.waxaudiomiditools.MidiConfig;

import javax.sound.midi.MidiDevice;

public class retourMidiConfig 
	{
		public retourMidiConfig()
		{
			
		}
		private MidiDevice.Info infos;
		private int index = -1;
		
		public String toString()
		{
			return getIndex()+": "+getInfos().getName()+" "+getInfos().getVendor()+" "+getInfos().getDescription();
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public void setInfos(MidiDevice.Info infos) {
			this.infos = infos;
		}

		public MidiDevice.Info getInfos() {
			return infos;
		}
	}