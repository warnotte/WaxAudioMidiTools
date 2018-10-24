package org.warnotte.waxaudiomiditools.CControlers;

import java.util.ArrayList;

public class KnowMidiList extends ArrayList<ControlElement> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3809174574743752274L;
	
	public void init_SYNTH()
	{
		this.add(new ControlElement("OSC TYPE",70));
		this.add(new ControlElement("OSC EDIT 1",14));
		this.add(new ControlElement("OSC EDIT 2",15));
		this.add(new ControlElement("GLIDE",5));
		
		this.add(new ControlElement("FILTER TYPE",83));
		this.add(new ControlElement("FILTER CUTOFF",74));
		this.add(new ControlElement("FILTER RESONNANCE",71));
		this.add(new ControlElement("FILTER EG INT",79));
		this.add(new ControlElement("FILTER DRIVE",84));
		
		this.add(new ControlElement("LEVEL",7));
		
		this.add(new ControlElement("PAN",10));
		this.add(new ControlElement("EG TIME",75));
		this.add(new ControlElement("AMP EG",86));
	//	this.add(new ControlElement("ROLL",85));
		
		this.add(new ControlElement("FX SEND",91));
		this.add(new ControlElement("FX SELECT",81));
		this.add(new ControlElement("MOD TYPE",87));
		this.add(new ControlElement("MOD DEPTH",90));
		this.add(new ControlElement("MOD SPEED",89));
		this.add(new ControlElement("MOD DEST",88));
		this.add(new ControlElement("MOD BPM SYNC",82));
			
		this.add(new ControlElement("FX 1 TYPE",12));
		this.add(new ControlElement("FX 1 EDIT1",92));
		this.add(new ControlElement("FX 1 EDIT2",93));
		
		this.add(new ControlElement("FX 2 TYPE",13));
		this.add(new ControlElement("FX 2 EDIT1",94));
		this.add(new ControlElement("FX 2 EDIT2",95));
		
		this.add(new ControlElement("FX 3 TYPE",24));
		this.add(new ControlElement("FX 3 EDIT1",25));
		this.add(new ControlElement("FX 3 EDIT2",26));
		this.add(new ControlElement("FX CHAIN",23));
		
		this.add(new ControlElement("WAVE",0x08, 0x01, 0x06));
		
	}
	public void init_DRUM(int drumChannel)
	{
		
		
	/*	| 09  20  | Drum1 Wave                       | Drum    |  MSB,LSB   | 0~143 		(*T1-2)                |
		| 09  21  | Drum1 Pitch                      | Drum    |  MSB       | 0~127 		(64=equal pitch)       |
		| 09  27  | Drum1 Level                      | Drum    |  MSB       | 0~127 		|
		| 09  28  | Drum1 Pan                        | Drum    |  MSB       | 0~127 		(64=center)            |
		| 09  29  | Drum1 EG Time                    | Drum    |  MSB       | 0~127 		|
		| 09  2A  | Drum1 Amp EG                     | Drum    |  MSB       | 		0~63/64~127 : Off/On         |
		| 09  2B  | Drum1 Roll                       | Drum    |  MSB       | 		0~63/64~127 : Off/On         |
		| 09  2D  | Drum1 Effect Send                | Drum    |  MSB       | 		0~63/64~127 : Off/On         |
		| 09  2E  | Drum1 Effect Select              | Drum    |  MSB       | *T1-3 		|
		| 09  2F  | Drum1 Modulation Type            | Drum    |  MSB       | *T1-4 		|
		| 09  30  | Drum1 Modulation Depth           | Drum    |  MSB       | 		0~64~127 : -63~0~63          |
		| 09  31  | Drum1 Modulation Speed           | Drum    |  MSB       | 0~127 		|
		| 09  32  | Drum1 Modulation Destination     | Drum    |  MSB       | *T1-5 		|
		| 09  33  | Drum1 Modulation BPM Sync        | Drum    |  MSB       | 		0~63/64~127 : Off/On         |
		| 09  34  | Drum1 Motion Seq Type            | Drum    |  MSB       | *T1-6 		|
		|         |                                  |         |            | 		|		
		| 09  40  | Drum2 Wave                       | Drum    |  MSB,LSB   | 0~143 (*T1-2)                |	
		| 09  41  | Drum2 Pitch                      | Drum    |  MSB       | 0~127 
		(64=equal pitch)       |
		*/
		
		int cn = drumChannel;// 0x07-1;//0x09;
		
		this.add(new ControlElement("DRUM1_WAVE",cn, 0x20, true));
		this.add(new ControlElement("DRUM1_PITCH",cn, 0x21,0x06));
		this.add(new ControlElement("DRUM1_LEVEL",cn, 0x27,0x06));
		this.add(new ControlElement("DRUM1_PAN",cn, 0x28,0x06));
		this.add(new ControlElement("DRUM1_EG_TIME",cn, 0x29,0x06));
		this.add(new ControlElement("DRUM1_AMP_EG",cn, 0x2A,0x06));
		this.add(new ControlElement("DRUM1_ROLL",cn, 0x2B,0x06));
		this.add(new ControlElement("DRUM1_FX_SEND",cn, 0x2D,0x06));
		this.add(new ControlElement("DRUM1_FX_SELECT",cn, 0x2E,0x06));
		this.add(new ControlElement("DRUM1_MOD_TYPE",cn, 0x2F,0x06));
		this.add(new ControlElement("DRUM1_MOD_DEPTH",cn, 0x30,0x06));
		this.add(new ControlElement("DRUM1_MOD_SPEED",cn, 0x31,0x06));
		this.add(new ControlElement("DRUM1_MOD_DESTINATION",cn, 0x32,0x06));
		this.add(new ControlElement("DRUM1_MOD_BPM_SYNC",cn, 0x33,0x06));
		this.add(new ControlElement("DRUM1_MOTION_SEQ_TYPE",cn, 0x34,0x06));
		
		this.add(new ControlElement("DRUM2_WAVE",cn, 0x40, true));
		this.add(new ControlElement("DRUM2_PITCH",cn, 0x41,0x06));
		this.add(new ControlElement("DRUM2_LEVEL",cn, 0x47,0x06));
		this.add(new ControlElement("DRUM2_PAN",cn, 0x48,0x06));
		this.add(new ControlElement("DRUM2_EG_TIME",cn, 0x49,0x06));
		this.add(new ControlElement("DRUM2_AMP_EG",cn, 0x4A,0x06));
		this.add(new ControlElement("DRUM2_ROLL",cn, 0x4B,0x06));
		this.add(new ControlElement("DRUM2_FX_SEND",cn, 0x4D,0x06));
		this.add(new ControlElement("DRUM2_FX_SELECT",cn, 0x4E,0x06));
		this.add(new ControlElement("DRUM2_MOD_TYPE",cn, 0x4F,0x06));
		this.add(new ControlElement("DRUM2_MOD_DEPTH",cn, 0x50,0x06));
		this.add(new ControlElement("DRUM2_MOD_SPEED",cn, 0x51,0x06));
		this.add(new ControlElement("DRUM2_MOD_DESTINATION",cn, 0x52,0x06));
		this.add(new ControlElement("DRUM2_MOD_BPM_SYNC",cn, 0x53,0x06));
		this.add(new ControlElement("DRUM2_MOTION_SEQ_TYPE",cn, 0x54,0x06));
		
		this.add(new ControlElement("DRUM3_WAVE",cn, 0x60, true));
		this.add(new ControlElement("DRUM3_PITCH",cn, 0x61,0x06));
		this.add(new ControlElement("DRUM3_LEVEL",cn, 0x67,0x06));
		this.add(new ControlElement("DRUM3_PAN",cn, 0x68,0x06));
		this.add(new ControlElement("DRUM3_EG_TIME",cn, 0x69,0x06));
		this.add(new ControlElement("DRUM3_AMP_EG",cn, 0x6A,0x06));
		this.add(new ControlElement("DRUM3_ROLL",cn, 0x6B,0x06));
		this.add(new ControlElement("DRUM3_FX_SEND",cn, 0x6D,0x06));
		this.add(new ControlElement("DRUM3_FX_SELECT",cn, 0x6E,0x06));
		this.add(new ControlElement("DRUM3_MOD_TYPE",cn, 0x6F,0x06));
		this.add(new ControlElement("DRUM3_MOD_DEPTH",cn, 0x70,0x06));
		this.add(new ControlElement("DRUM3_MOD_SPEED",cn, 0x71,0x06));
		this.add(new ControlElement("DRUM3_MOD_DESTINATION",cn, 0x72,0x06));
		this.add(new ControlElement("DRUM3_MOD_BPM_SYNC",cn, 0x73,0x06));
		this.add(new ControlElement("DRUM3_MOTION_SEQ_TYPE",cn, 0x74,0x06));
		
		cn = cn+1;
		this.add(new ControlElement("DRUM4_WAVE",cn, 0x00, true));
		this.add(new ControlElement("DRUM4_PITCH",cn, 0x01,0x06));
		this.add(new ControlElement("DRUM4_LEVEL",cn, 0x07,0x06));
		this.add(new ControlElement("DRUM4_PAN",cn, 0x08,0x06));
		this.add(new ControlElement("DRUM4_EG_TIME",cn, 0x09,0x06));
		this.add(new ControlElement("DRUM4_AMP_EG",cn, 0x0A,0x06));
		this.add(new ControlElement("DRUM4_ROLL",cn, 0x0B,0x06));
		this.add(new ControlElement("DRUM4_FX_SEND",cn, 0x0D,0x06));
		this.add(new ControlElement("DRUM4_FX_SELECT",cn, 0x0E,0x06));
		this.add(new ControlElement("DRUM4_MOD_TYPE",cn, 0x0F,0x06));
		this.add(new ControlElement("DRUM4_MOD_DEPTH",cn, 0x10,0x06));
		this.add(new ControlElement("DRUM4_MOD_SPEED",cn, 0x11,0x06));
		this.add(new ControlElement("DRUM4_MOD_DESTINATION",cn, 0x12,0x06));
		this.add(new ControlElement("DRUM4_MOD_BPM_SYNC",cn, 0x13,0x06));
		this.add(new ControlElement("DRUM4_MOTION_SEQ_TYPE",cn, 0x14,0x06));
		
		this.add(new ControlElement("DRUM5_WAVE",cn, 0x20, true));
		this.add(new ControlElement("DRUM5_PITCH",cn, 0x21,0x06));
		this.add(new ControlElement("DRUM5_LEVEL",cn, 0x27,0x06));
		this.add(new ControlElement("DRUM5_PAN",cn, 0x28,0x06));
		this.add(new ControlElement("DRUM5_EG_TIME",cn, 0x29,0x06));
		this.add(new ControlElement("DRUM5_AMP_EG",cn, 0x2A,0x06));
		this.add(new ControlElement("DRUM5_ROLL",cn, 0x2B,0x06));
		this.add(new ControlElement("DRUM5_FX_SEND",cn, 0x2D,0x06));
		this.add(new ControlElement("DRUM5_FX_SELECT",cn, 0x2E,0x06));
		this.add(new ControlElement("DRUM5_MOD_TYPE",cn, 0x2F,0x06));
		this.add(new ControlElement("DRUM5_MOD_DEPTH",cn, 0x30,0x06));
		this.add(new ControlElement("DRUM5_MOD_SPEED",cn, 0x31,0x06));
		this.add(new ControlElement("DRUM5_MOD_DESTINATION",cn, 0x32,0x06));
		this.add(new ControlElement("DRUM5_MOD_BPM_SYNC",cn, 0x33,0x06));
		this.add(new ControlElement("DRUM5_MOTION_SEQ_TYPE",cn, 0x34,0x06));
		
		this.add(new ControlElement("DRUM6_WAVE",cn, 0x40, true));
		this.add(new ControlElement("DRUM6_PITCH",cn, 0x41,0x06));
		this.add(new ControlElement("DRUM6_LEVEL",cn, 0x47,0x06));
		this.add(new ControlElement("DRUM6_PAN",cn, 0x48,0x06));
		this.add(new ControlElement("DRUM6_EG_TIME",cn, 0x49,0x06));
		this.add(new ControlElement("DRUM6_AMP_EG",cn, 0x4A,0x06));
		this.add(new ControlElement("DRUM6_ROLL",cn, 0x4B,0x06));
		this.add(new ControlElement("DRUM6_FX_SEND",cn, 0x4D,0x06));
		this.add(new ControlElement("DRUM6_FX_SELECT",cn, 0x4E,0x06));
		this.add(new ControlElement("DRUM6_MOD_TYPE",cn, 0x4F,0x06));
		this.add(new ControlElement("DRUM6_MOD_DEPTH",cn, 0x50,0x06));
		this.add(new ControlElement("DRUM6_MOD_SPEED",cn, 0x51,0x06));
		this.add(new ControlElement("DRUM6_MOD_DESTINATION",cn, 0x52,0x06));
		this.add(new ControlElement("DRUM6_MOD_BPM_SYNC",cn, 0x53,0x06));
		this.add(new ControlElement("DRUM6_MOTION_SEQ_TYPE",cn, 0x54,0x06));
	
		this.add(new ControlElement("DRUM7_WAVE",cn, 0x60, true));
		this.add(new ControlElement("DRUM7_PITCH",cn, 0x61,0x06));
		this.add(new ControlElement("DRUM7_LEVEL",cn, 0x67,0x06));
		this.add(new ControlElement("DRUM7_PAN",cn, 0x68,0x06));
		this.add(new ControlElement("DRUM7_EG_TIME",cn, 0x69,0x06));
		this.add(new ControlElement("DRUM7_AMP_EG",cn, 0x6A,0x06));
		this.add(new ControlElement("DRUM7_ROLL",cn, 0x6B,0x06));
		this.add(new ControlElement("DRUM7_FX_SEND",cn, 0x6D,0x06));
		this.add(new ControlElement("DRUM7_FX_SELECT",cn, 0x6E,0x06));
		this.add(new ControlElement("DRUM7_MOD_TYPE",cn, 0x6F,0x06));
		this.add(new ControlElement("DRUM7_MOD_DEPTH",cn, 0x70,0x06));
		this.add(new ControlElement("DRUM7_MOD_SPEED",cn, 0x71,0x06));
		this.add(new ControlElement("DRUM7_MOD_DESTINATION",cn, 0x72,0x06));
		this.add(new ControlElement("DRUM7_MOD_BPM_SYNC",cn, 0x73,0x06));
		this.add(new ControlElement("DRUM7_MOTION_SEQ_TYPE",cn, 0x74,0x06));
	
		cn = cn+1;
		this.add(new ControlElement("DRUM8_WAVE",cn, 0x00, true));
		this.add(new ControlElement("DRUM8_PITCH",cn, 0x01,0x06));
		this.add(new ControlElement("DRUM8_LEVEL",cn, 0x07,0x06));
		this.add(new ControlElement("DRUM8_PAN",cn, 0x08,0x06));
		this.add(new ControlElement("DRUM8_EG_TIME",cn, 0x09,0x06));
		this.add(new ControlElement("DRUM8_AMP_EG",cn, 0x0A,0x06));
		this.add(new ControlElement("DRUM8_ROLL",cn, 0x0B,0x06));
		this.add(new ControlElement("DRUM8_FX_SEND",cn, 0x0D,0x06));
		this.add(new ControlElement("DRUM8_FX_SELECT",cn, 0x0E,0x06));
		this.add(new ControlElement("DRUM8_MOD_TYPE",cn, 0x0F,0x06));
		this.add(new ControlElement("DRUM8_MOD_DEPTH",cn, 0x10,0x06));
		this.add(new ControlElement("DRUM8_MOD_SPEED",cn, 0x11,0x06));
		this.add(new ControlElement("DRUM8_MOD_DESTINATION",cn, 0x12,0x06));
		this.add(new ControlElement("DRUM8_MOD_BPM_SYNC",cn, 0x13,0x06));
		this.add(new ControlElement("DRUM8_MOTION_SEQ_TYPE",cn, 0x14,0x06));
	
		this.add(new ControlElement("DRUM9_WAVE",cn, 0x20, true));
		this.add(new ControlElement("DRUM9_PITCH",cn, 0x21,0x06));
		this.add(new ControlElement("DRUM9_LEVEL",cn, 0x27,0x06));
		this.add(new ControlElement("DRUM9_PAN",cn, 0x28,0x06));
		this.add(new ControlElement("DRUM9_EG_TIME",cn, 0x29,0x06));
		this.add(new ControlElement("DRUM9_AMP_EG",cn, 0x2A,0x06));
		this.add(new ControlElement("DRUM9_ROLL",cn, 0x2B,0x06));
		this.add(new ControlElement("DRUM9_FX_SEND",cn, 0x2D,0x06));
		this.add(new ControlElement("DRUM9_FX_SELECT",cn, 0x2E,0x06));
		this.add(new ControlElement("DRUM9_MOD_TYPE",cn, 0x2F,0x06));
		this.add(new ControlElement("DRUM9_MOD_DEPTH",cn, 0x30,0x06));
		this.add(new ControlElement("DRUM9_MOD_SPEED",cn, 0x31,0x06));
		this.add(new ControlElement("DRUM9_MOD_DESTINATION",cn, 0x32,0x06));
		this.add(new ControlElement("DRUM9_MOD_BPM_SYNC",cn, 0x33,0x06));
		this.add(new ControlElement("DRUM9_MOTION_SEQ_TYPE",cn, 0x34,0x06));
	
		
	/*	
		| 0B  60  | Synth Accent Level               | Global  |  MSB       | 0~127 
		|
		| 0B  61  | Synth Accent Motion Seq SW       | Global  |  MSB       | 
		0~42/43~127 : Off/Trig Hold  |
		| 0B  62  | Drum Accent Level                | Global  |  MSB       | 0~127 
		|
		| 0B  63  | Drum Accent Motion Seq SW        | Global  |  MSB       | 
		0~42/43~127 : Off/Trig Hold|
		| 0B  70  | Swing                            | Global  |  MSB       | *T1-7 
		|
		| 0B  71  | RollType                         | Global  |  MSB       | *T1-8 
		|
		| 0B  76  | Mute 1                           | Global  |  MSB,LSB   | *T1-9 
		|
		| 0B  77  | Mute 2                           | Global  |  MSB,LSB   | 
		*T1-10                       |
		
		*/
		//this.add(new ControlElement("DRUM_Synth_Accent_Level",cn, 0x34,0x06));
		
	
	}
	
	public KnowMidiList()
	{
		
		
		
		/*this.add(new ControlElement("DRUM_WAVE",0x08, 0x20, 0x06));
		sendNRPN2( Channel, 0x09, 0x20, 0x06, 0x00, 0x26, valeur);*
		*/
		
	}
	
	
	
	public ControlElement getByCC(int cc)
	{
		for (int i = 0; i < size(); i++) {
			ControlElement c = get(i);
			if (c.getCC()==cc)
				return c;
		}
		return null;
	}
	public ControlElement getByLabel(String label)
	{
		for (int i = 0; i < size(); i++) {
			ControlElement c = get(i);
			if (c.getLabel().equalsIgnoreCase(label))
				return c;
		}
		return null;
	}
}
