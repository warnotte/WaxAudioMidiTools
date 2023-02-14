package org.warnotte.waxaudiomiditools.CControlers;

import io.github.warnotte.waxlib3.OBJ2GUI.Annotations.GUI_CLASS;
import io.github.warnotte.waxlib3.OBJ2GUI.Annotations.GUI_FIELD_TYPE;
import io.github.warnotte.waxlib3.waxlibswingcomponents.Utils.Curve.Copiable;
import io.github.warnotte.waxlib3.waxlibswingcomponents.Utils.Curve.Curve;

@GUI_CLASS(type=GUI_CLASS.Type.BoxLayout, BoxLayout_property=GUI_CLASS.Type_BoxLayout.Y)
public class SignGen_VCA_2ND extends SignGenBase implements Gateable
{
   // @GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.CHECKBOX)
    public boolean GATE = false;
    
    enum state {ATTACK, DECAY, SUSTAIN, RELEASE, IDLE}
    transient state etat = state.IDLE;
    
    @GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.CHECKBOX)
    public boolean Invert;
    @GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.SLIDER, slider_type = GUI_FIELD_TYPE.Type_SLIDER.NORMAL, min = 0, max = 50000, divider = 10)
    public float Sa_time=1;
    public float Ss_time=1000;
    @GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.SLIDER, slider_type = GUI_FIELD_TYPE.Type_SLIDER.NORMAL, min = 0, max = 50000, divider = 10)
    public float Sr_time=500;
   // transient private float LAST_VOL=0;
    
    Curve att_crv = new Curve();
    Curve att_sus = new Curve();
    Curve att_rel = new Curve();
    
    public SignGen_VCA_2ND()
    {
    	name="VCA";
    	att_crv.setData(new float[]{0,1f});
    	att_sus.setData(new float[]{1,1f});
    	att_rel.setData(new float[]{1,0f});
    	
    }
    
    @Override
	public void copysettings(Copiable to)
	{
		super.copysettings(to);
		
		
		((SignGen_VCA_2ND)to).Invert=this.Invert;
		((SignGen_VCA_2ND)to).setSa_time(this.Sa_time);
		((SignGen_VCA_2ND)to).setSs_time(this.Ss_time);
		((SignGen_VCA_2ND)to).setSr_time(this.Sr_time);
		
		((SignGen_VCA_2ND)to).att_crv.copysettings(this.att_crv);
		((SignGen_VCA_2ND)to).att_sus.copysettings(this.att_sus);
		((SignGen_VCA_2ND)to).att_rel.copysettings(this.att_rel);
		
		
	}
	
    public synchronized Curve getAtt_crv()
	{
		return att_crv;
	}

	public synchronized void setAtt_crv(Curve att_crv)
	{
		this.att_crv = att_crv;
	}

	public synchronized Curve getAtt_sus()
	{
		return att_sus;
	}

	public synchronized void setAtt_sus(Curve att_sus)
	{
		this.att_sus = att_sus;
	}

	public synchronized Curve getAtt_rel()
	{
		return att_rel;
	}

	public synchronized void setAtt_rel(Curve att_rel)
	{
		this.att_rel = att_rel;
	}

	public void copysettings(SignGen_VCA_2ND to)
	{
	/*	to.Invert=this.Invert;
		to.Sa_time=this.Sa_time;
		to.Sa_level=this.Sa_level;
		to.Sd_time=this.Sd_time;
		to.Sd_level=this.Sd_level;
		to.Ss_time=this.Ss_time;
		to.Ss_level=this.Ss_level;
		to.Sr_time=this.Sr_time;
		to.Sr_level=this.Sr_level;*/
	}
	

    boolean flag_gate = false;
    
    float time_gate = 0;
    
	private transient float old_VALEUR=0;
	@Override
	public void evolue(int Channel, long elapsed, float valeur_preset) {
		super.evolue(Channel, elapsed, valeur_preset);

		try
		{
		
		//value=0;
        value = 0.0F;
        time_gate+=(dif_time);
		 
        
	
		
		
		
			// SI PRESSION ENCORE
        if (this.GATE == true) {
				
			//	System.err.printf("VCA %d[%s]=%4.4f\r\n",this.hashCode(),etat.toString(), time_gate);
				
				
			
				if (etat==state.ATTACK)
				{
			//		System.err.printf("VCA %d[%s]=%4.4f\r\n",this.hashCode(),etat.toString(), time_gate);
					
					if (Sa_time==0) // Si pas d'attack, alors a fond desuite le volume.
					{
						etat=state.SUSTAIN;
						value=1;
					}else
					if (time_gate>Sa_time)
						etat=state.SUSTAIN;
					else
						value = att_crv.getValue(time_gate/Sa_time);
				}
			//	else // si je mets le else y'a un saut dans le son (ca revient a 0)
				if (etat==state.SUSTAIN)
				{
					float idx=0;
					if (Ss_time!=0)
					 idx = time_gate/Ss_time;	
					if (idx>1) idx=idx-(int)idx;
					//System.err.println("IDX =="+idx);
					if (idx<=att_sus.size())
						value=att_sus.getValue(idx);
					
				}
				old_VALEUR=value;
		
			}
			else
			{
				if ((etat==state.RELEASE) && (time_gate>Sr_time))
				{
					etat=state.IDLE;
					value=0;
					// TODO : Send a note OFF ...
					
				//	System.err.printf("VCA %d[%s]=%4.4f\r\n",this.hashCode(),etat.toString(), time_gate);

				}
				else
				if (etat==state.RELEASE)
				{
					
					
					
			//		float ecartLvl = (float) 1.0; // Faut prendre le old_lvl pas le ss c pas tjrs lui.
			//		float ecartTmp = Sr_time; 
					
			//		float V = ecartLvl/Sr_time;
				//	V = old_VALEUR/*Ss_level*/-time_gate*V;
					value=att_rel.getValue(time_gate/Sr_time)*old_VALEUR;
				}
				
					
			}

		
		if (etat==null)
			etat=state.IDLE;
	//	String s = etat.toString();

		if (this.Invert==true)
			value=1.0f-value;


		value = (1 - Mix) * valeur_preset + (value) * (Mix);

	//	if (value != 0)
	//

		if (value <= 0)
			value = 0;
		if (value >= 1)
			value = 1;
		
		//	System.err.println("value =" + value);

		
		//int v = (int) ((value) * (valeur_preset*127));

		//System.err.println("vv=" + value);
		///sendSignalToReceiver(Channel, v);
		// old_elapsedtime=elapsedtime;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
		setGATE(v);
		
	}


	public synchronized boolean isGATE() {
		return GATE;
	}

	public synchronized void setGATE(boolean gate) {
		GATE = gate;
		time_gate=0;
		if (GATE==true) 
		{
			etat=state.ATTACK;
		}
		else 
			etat=state.RELEASE;
		
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


	public synchronized float getSs_time() {
		return Ss_time;
	}

	public synchronized void setSs_time(float ss_time) {
		Ss_time = ss_time;
	}

	public synchronized float getSr_time() {
		return Sr_time;
	}

	public synchronized void setSr_time(float sr_time) {
		Sr_time = sr_time;
	}

	public static void main(String args[]) throws InterruptedException
	{
		SignGen_VCA_2ND vca = new SignGen_VCA_2ND();
		vca.setSa_time(1000);
		vca.setGATE(true);
		
		for (int i = 0; i < 10000; i++)
		{
			vca.evolue(0, System.currentTimeMillis(), 127);
			System.err.println(""+vca.value);
			Thread.sleep(100);
		}
		
		vca.setGATE(false);
		
		
	}

	/**
	 * Nivelle a la premiere et dernier point du sustain pour qu'il soit au meme level que la fin de l'attack
	 * et le debut du release.
	 */
	public void ajust_sustain_lvl() {
		float valeur1 = att_crv.getLastData();
		float valeur2 = att_rel.getData(0);
		att_sus.setData(0, valeur1);
		att_sus.setLastData(valeur2);
		
	}
	
}
