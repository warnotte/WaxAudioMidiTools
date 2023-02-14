package org.warnotte.waxaudiomiditools.CControlers;

import io.github.warnotte.waxlib3.OBJ2GUI.Annotations.GUI_CLASS;
import io.github.warnotte.waxlib3.OBJ2GUI.Annotations.GUI_FIELD_TYPE;
import io.github.warnotte.waxlib3.waxlibswingcomponents.Utils.Curve.Copiable;

@GUI_CLASS(type=GUI_CLASS.Type.BoxLayout, BoxLayout_property=GUI_CLASS.Type_BoxLayout.Y)
public class SignGen_VCA extends SignGenBase implements Gateable
{
	//@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.CHECKBOX)
    public boolean BYPASS = false; // Bypass du VCA
   // @GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.CHECKBOX)
    public boolean GATE = false;
    
    enum state {ATTACK, DECAY, SUSTAIN, RELEASE, IDLE}
    transient state etat = state.IDLE;
    
  //  transient  private int bypass=0;
  //  transient private float time_retain;
  //  transient private float time_ecoul;
    
    
    @GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.CHECKBOX)
    public boolean Invert;
    @GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
    public float Sa_time=50;
    @GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
    public float Sa_level=1.0f;
    @GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
    public float Sd_time=1000;
    public float Sd_level=0.0f;
    public float Ss_time;
    @GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
    public float Ss_level=0.5f;
    @GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
    public float Sr_time=500;
    public float Sr_level=0.0f;
 //   transient private float LAST_VOL=0;
    
    public SignGen_VCA()
    {
    	name="VCA";
    }
    
    @Override
	public void copysettings(Copiable to)
	{
		super.copysettings(to);
		copysettings((SignGen_VCA) to);
	}
	
    public void copysettings(SignGen_VCA to)
	{
		to.Invert=this.Invert;
		to.Sa_time=this.Sa_time;
		to.Sa_level=this.Sa_level;
		to.Sd_time=this.Sd_time;
		to.Sd_level=this.Sd_level;
		to.Ss_time=this.Ss_time;
		to.Ss_level=this.Ss_level;
		to.Sr_time=this.Sr_time;
		to.Sr_level=this.Sr_level;
	}
	

    boolean flag_gate = false;
    float time_gate = 0;
	private float old_VALEUR;
	@Override
	public void evolue(int Channel, long elapsedtime, float valeur_preset) {
		super.evolue(Channel, elapsedtime, valeur_preset);

		// System.err.println("Gate =" + this.GATE.getINL());
		value=0;
		time_gate+=dif_time;
		 
		if (this.BYPASS == false) {
			// SI PRESSION ENCORE
			if (this.GATE == true) {
				if (flag_gate==false)
				{
					flag_gate=true;
					time_gate=0;
					etat=state.ATTACK;
			//		System.err.printf("VCA %d[%s]=%4.4f\r\n",this.hashCode(),etat.toString(), time_gate);

				}
				
				if ((etat==state.ATTACK) && (time_gate>Sa_time))
				{
					time_gate=0;
					etat=state.DECAY;
			//		System.err.printf("VCA %d[%s]=%4.4f\r\n",this.hashCode(),etat.toString(), time_gate);

					
				}
				if ((etat==state.DECAY) && (time_gate>Sd_time))
				{
					time_gate=0;
					etat=state.SUSTAIN;
			//		System.err.printf("VCA %d[%s]=%4.4f\r\n",this.hashCode(),etat.toString(), time_gate);

					
				}
			
				if (etat==state.ATTACK)
				{
				//	System.err.printf("VCA %d[%s]=%4.4f\r\n",this.hashCode(),etat.toString(), time_gate);
					value = (Sa_level * (time_gate)) / Sa_time;
					//value=1;
				//	System.err.printf(""+value);
				}
				if (etat==state.DECAY)
				{
					float ecartLvl = Ss_level-Sa_level;
				//	float ecartTmp = Sd_time; 
					
					float V = ecartLvl/Sd_time;
					V = Sa_level+time_gate*V;
					
					value = V;// ((Sa_level * ((time) )) / Sd_time);
				}
				if (etat==state.SUSTAIN)
					value=Ss_level;
				old_VALEUR=value;
		
			}
			else
			{
				if (flag_gate==true)
				{
					time_gate=0;
					flag_gate=false;
					etat=state.RELEASE;
				//	System.err.printf("VCA %d[%s]=%4.4f\r\n",this.hashCode(),etat.toString(), time_gate);

				}
				
				if ((etat==state.RELEASE) && (time_gate>Sr_time))
				{
					etat=state.IDLE;
				//	System.err.printf("VCA %d[%s]=%4.4f\r\n",this.hashCode(),etat.toString(), time_gate);

				}
				
				if (etat==state.RELEASE)
				{
					float ecartLvl = old_VALEUR/*Ss_level*/; // Faut prendre le old_lvl pas le ss c pas tjrs lui.
			//		float ecartTmp = Sr_time; 
					
					float V = ecartLvl/Sr_time;
					V = old_VALEUR/*Ss_level*/-time_gate*V;
					value=V;
				}
				
				if (etat==state.IDLE)
					value=0;
			}

		}
		if (etat==null)
			etat=state.IDLE;
		//String s = etat.toString();

		
		if (this.Invert==true)
			value=1.0f-value;

		value = (1 - Mix) * valeur_preset + (value) * (Mix);

	//	if (value != 0)
	//


		if (value <= 0)
			value = 0;
		if (value >= 1)
			value = 1;

	
		// old_elapsedtime=elapsedtime;
	}
	
	
	/*
	public void evolue(int Channel, long elapsedtime, float valeur_preset) {
		super.evolue(Channel, elapsedtime, valeur_preset);

		// System.err.println("Gate =" + this.GATE.getINL());

		if (this.BYPASS == false) {
			// SI PRESSION ENCORE
			if (this.GATE == true) {
				time_retain = 0;
				// System.err.printf("%f \n",time_ecoule);
				// Chercher le numero du segment actuel
				int nr_seg = -1;

				float elapsedtimeM = (time);
				if (elapsedtimeM <= Sa_time) {
					nr_seg = 0;
				} else if ((elapsedtimeM > Sa_time)
						|| (elapsedtimeM <= (Sd_time + Sa_time)))

				{
					// SI pas de SUSTAIN alors DECAY sinon pas de decay et
					// direct sustain
					if (Ss_level == 0) {
						nr_seg = 1;
					} else {
						nr_seg = 2;
					}
				}

				if (Sa_time == 0)
					Sa_time = 0.001f;

				// System.err.printf("NR SEG %d\n", nr_seg);
				if (nr_seg != -1) {
					// ATTACK
					if (nr_seg == 0) {
					//	 System.err.printf("Attack \n");
						value = (Sa_level * (time)) / Sa_time;
						// time += ((float) 1 / (float) 44100);
						LAST_VOL = value;
					} else
					// DECAY
					if (nr_seg == 1) {
					//	System.err.printf("Decay\n");
						value = Sa_level
								- ((Sa_level * ((time) - Sa_time)) / Sd_time);
						// time += ((float) 1 / (float) 44100);
						LAST_VOL = value;
					} else
					// SUSTAIN
					if (nr_seg == 2) {
					//	System.err.printf("Sustain\n");
						// 1time-=(float)1/(float)44100;
						// Remet au level (donc ajuste la hauteux)
						if (value > Ss_level) {
							value = value - (value / 1000);
						} else {
							value = Sa_level * Ss_level;
						}
						
						value=valeur_preset;

						LAST_VOL = value;
					}
				}

				if (this.Invert == true) {
					this.value = Math.abs((float) value - (float) 1);
				}

				
			} 
			else // Plus la gate ... probablement en Cmode release
			{
				time = 0;

				if (Sr_time != 0) {
				//	if (LAST_VOL != 0) {
						if ((time_retain) <= Sr_time) {
							//System.err.printf("Release\n");
							time_retain += dif_time;// ((float) 1 / (float)
													// 44100);
							value = LAST_VOL - ((LAST_VOL * (time_retain)) / Sr_time);

							// System.err.printf("" +
							// 		"Retain %f %f\n", value, time_retain);
							// getch();
							 LAST_VOL=value;
						}
				//	}
				}

				if (this.Invert == true) {
					value = Math.abs((float) this.value - (float) 1);
				}

				
				// System.err.println("L"+voice+" = "+value);
			}
		} else {
			// BYPASS
			

		}

		// System.err.println(""+value);

		value = (1 - Mix) * valeur_preset + (value) * (Mix);

	//	if (value != 0)
	//		System.err.println("vv=" + value);


		if (value <= 0)
			value = 0;
		if (value >= 1)
			value = 1;

		sendSignalToReceiver(Channel, (int) ((value) * 127));
		// old_elapsedtime=elapsedtime;
	}
	*/
	@Override
	public void sync()
	{
		super.sync();
		time=0;
	}
	
	public void gate(boolean v)
	{
		GATE=v;
		if (v==true)
			time=0;
	}

	public synchronized boolean isBYPASS() {
		return BYPASS;
	}

	public synchronized void setBYPASS(boolean bypass) {
		BYPASS = bypass;
	}

	public synchronized boolean isGATE() {
		return GATE;
	}

	public synchronized void setGATE(boolean gate) {
		GATE = gate;
	}

	public synchronized boolean isInvert() {
		return Invert;
	}

	public synchronized void setInvert(boolean invert) {
		Invert = invert;
	}

	public synchronized float getSa_time() {
		return Sa_time;
	}

	public synchronized void setSa_time(float sa_time) {
		Sa_time = sa_time;
	}

	public synchronized float getSa_level() {
		return Sa_level;
	}

	public synchronized void setSa_level(float sa_level) {
		Sa_level = sa_level;
	}

	public synchronized float getSd_time() {
		return Sd_time;
	}

	public synchronized void setSd_time(float sd_time) {
		Sd_time = sd_time;
	}

	public synchronized float getSd_level() {
		return Sd_level;
	}

	public synchronized void setSd_level(float sd_level) {
		Sd_level = sd_level;
	}

	public synchronized float getSs_time() {
		return Ss_time;
	}

	public synchronized void setSs_time(float ss_time) {
		Ss_time = ss_time;
	}

	public synchronized float getSs_level() {
		return Ss_level;
	}

	public synchronized void setSs_level(float ss_level) {
		Ss_level = ss_level;
	}

	public synchronized float getSr_time() {
		return Sr_time;
	}

	public synchronized void setSr_time(float sr_time) {
		Sr_time = sr_time;
	}

	public synchronized float getSr_level() {
		return Sr_level;
	}

	public synchronized void setSr_level(float sr_level) {
		Sr_level = sr_level;
	}

	
}
