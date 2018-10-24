package org.warnotte.waxaudiomiditools.MidiToSynth;
import java.awt.event.KeyEvent;



public class KeyFilter implements Filter
{

	int KeyCode;
	
	public KeyFilter(int keycode)
	{
		this.KeyCode=keycode;
	}
	
	public boolean isFiltered(Object o)
	{
		if ((o instanceof KeyEvent)==false)
			return false;
		KeyEvent e = (KeyEvent) o;
		if (e.getKeyCode()==KeyCode)//KeyEvent.VK_A)
			return true;
		return false;
	}

}
