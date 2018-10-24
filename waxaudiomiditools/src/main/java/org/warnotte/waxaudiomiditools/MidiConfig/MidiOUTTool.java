package org.warnotte.waxaudiomiditools.MidiConfig;

import static java.lang.System.exit;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.swing.JFrame;

public class MidiOUTTool {


	private static Receiver outRcv=null;
	static MidiDevice outputDevice = null;

	

	   public static void init_midisender() throws MidiUnavailableException
	   {
		   close();
		   // Montre le dialogue de setup
		   ConfigMIDIOUTPUT md1 = new ConfigMIDIOUTPUT(new JFrame());
			md1.pack();
			md1.setVisible(true);
			retourMidiConfig retour = md1.getDonnees();
		
		   
		    /*
	        *        The device name/index to listen to.
	        */
		//	String strDeviceName = (String) retourMidiConfig;
	       int nDeviceIndex = new Integer(""+retour.getIndex());
	//       boolean bUseDefaultSynthesizer = false;

	       if ((retour.getInfos() == null) && (nDeviceIndex < 0))
	       {
	    	   System.out.println("device name/index not specified!");
	       }

	       MidiDevice.Info info;

	       
	      
	           info = MidiCommon.getMidiDeviceInfo(nDeviceIndex);
	       

	     /*  if (info == null)
	       {
	           if (strDeviceName != null)
	           {
	        	   System.out.println("no device info found for name " + strDeviceName);
	           }
	           else
	           {
	        	   System.out.println("no device info found for index " + nDeviceIndex);
	           }

	           exit(1);
	       }*/

	       MidiDevice inputDevice = null;

	       try
	       {
	       	
	           inputDevice = MidiSystem.getMidiDevice(info);
	           inputDevice.open();
	           
	          
	       }
	       catch (MidiUnavailableException e)
	       {
	    	   System.out.println(e);
	       }

	       if (inputDevice == null)
	       {
	    	   System.out.println("wasn't able to retrieve MidiDevice");
	           exit(1);
	       }
	         
	       outRcv = inputDevice.getReceiver();

	       
	     /*  //outRcv.send( createEvent( ShortMessage.NOTE_ON , 1, 60 ,127 ) , -1  );
	       //outRcv.send( createEvent( ShortMessage.CONTROL_CHANGE , 1, 0x01 ,127 ) , -1  );
	       
	       try
	       {
	      		out("now running; interupt the program with [ENTER] when finished");
	           in.read();
	       }
	       catch (IOException ioe)
	       {
	       }

	       inputDevice.close();*/
	  
	      
	   }
	    
	    public static ShortMessage createEvent(int type, int chan, int num, int
	    		 velocity) {
	    		ShortMessage message = new ShortMessage(); try {
	    		 message.setMessage(type, (chan-1), num, velocity); return message; }
	    		catch (Exception ex) { ex.printStackTrace(); } return null;
	    		 }
	    		
	    		 public ShortMessage createControlChangeEvent(int type, int chan, int
	    		 num) {
	    		  ShortMessage message = new ShortMessage(); try {
	    		  message.setMessage(176 , chan,  type, num); return message;
	    		  } catch (Exception ex) { ex.printStackTrace(); } return null; }

				public static void close() {
					if (outRcv!=null)
					outRcv.close();
				}

				public static Receiver getOutRcv() {
					return outRcv;
				}

				static Thread t = new Thread();
				
				public static void AllNotesOff() {
					if (getOutRcv()!=null)
					{
					
						for (int canal = 1; canal < 17;canal++)
						{
							//ShortMessage message = MidiOUTTool.createEvent( 123, canal, 0 ,0 );
							ShortMessage message = MidiOUTTool.createEvent( 176, canal, 123 ,0 );
						getOutRcv().send(message  , -1  );
						}
						
					}
				}

			/*	public static void setOutRcv(Receiver outRcv) {
					MidiTool.outRcv = outRcv;
				}*/
	    



}
