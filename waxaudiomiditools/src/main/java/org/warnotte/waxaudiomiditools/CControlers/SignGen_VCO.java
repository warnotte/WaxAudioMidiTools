package org.warnotte.waxaudiomiditools.CControlers;
import org.warnotte.OBJ2GUI.Annotations.GUI_CLASS;
import org.warnotte.OBJ2GUI.Annotations.GUI_FIELD_TYPE;
import org.warnotte.Utils.Curve.Copiable;
import org.warnotte.waxlib2.TemplatePropertyMerger.property_mode;
import org.warnotte.waxlib2.TemplatePropertyMerger.Annotations.PROPERTY_interface;
import org.warnotte.waxlib2.TemplatePropertyMerger.Annotations.PROPERTY_interface.gui_type;



@GUI_CLASS(type=GUI_CLASS.Type.BoxLayout, BoxLayout_property=GUI_CLASS.Type_BoxLayout.Y)
public class SignGen_VCO extends SignGenBase
{
	{
		create_SINUS_TABLE();
	}
	
	public enum type {SIN, SAW, TRI, SQR, PWM}; 
	
	private static float[] SINUS;
	@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
	float Freq = 1;
	@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.COMBO)
	type OscType = type.SIN;
	@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
	private float Actual_Phase=0;
	@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
	private float Actual_PWM=0.5f;
	
	

	static int MaxFrequency = 44100;
	
	public SignGen_VCO()
	{
		name="VCO";
		BypassSend=true;
		
	}
	
	@Override
	public void copysettings(Copiable to)
	{
		super.copysettings(to);
		((SignGen_VCO)to).setFreq(this.getFreq());
	}
	
	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized float getFreq()
	{
		return Freq;
	}

	public synchronized void setFreq(float freq)
	{
		this.Freq = freq;
	}

	@Override
	public void evolue(int Channel, long elapsedtime,float valeur_preset)
	{
		super.evolue(Channel, elapsedtime,valeur_preset);
		
		//value = (float) ((float) Math.sin((time/1000f)*Freq)); // +-1.0f
		
		value =synthesis(time,  1.0f, Freq, Actual_Phase, Actual_PWM, OscType);
		
		
		
		value = (value + 1.0f) /2 ;
		
		value=value*valeur_preset;
		
		value = (1-Mix)*valeur_preset + value * (Mix); 
		
	//	sendSignalToReceiver(Channel, (int) (value*127f));
		
	}
	

    private float synthesis(float time, float Actual_Amplitude, float Actual_Frequence, float Actual_Phase, float Actual_PWM, type Type)
	{
    	
    	// Phase shifting ...
    	time = time + 2*(Actual_Phase*1000);
    	/*if (time >= 1)
        {
       	 float diff = time-1;
       	 time=-1;
       	 	time+=diff;
     		
        }*/
    	
    	/*
    	if (time>1)
    		time=time-(int)time;
    	if (time<-1)
    		time=time-(int)time;
		*/
    	
    	// Pour avoir des secondes.
    	time/=1000.0f;
    	
    	if (Type==type.SIN)
              {
            	  return  ((float) Math.sin((time)*Freq*4)); // +-1.0f
         
              }else
    	if (Type==type.SAW)
    	{
	            	  float V = 0;
	            	  

	            	  time*=Freq;
	            	  //Transforme en -1 1
	            	  time = time-(int)time;

	            	  time-=0.5f;
	            	  time*=2;
	            	  
	                	if ((time <0) && (time >=-1)) 
	                		V = -(2*Actual_Amplitude*time);
	    				if ((time <=1) && (time >=0)) 
	    					V = 2*Actual_Amplitude*time;
	    				V-=Actual_Amplitude;
	    				V /= 1;
	    				return V;
				
			}else
                  
    	if (Type==type.TRI)
              {
            	//  time=66.35645f;
            	  time*=Freq;
            	  time = time-(int)time;
            	  time-=0.5f;
            	  
            	  
            	  float V= (time)*Actual_Amplitude;
                  V *= 2;
                  return V;
                //  break;
              }else
    	if (Type==type.SQR)
              {
            	  float V = ((float) Math.sin((time ) * Freq*4)); // +-1.0f
  				if ((V < 0.0))
  					V = -(2 * Actual_Amplitude * 1);
  				if ((V >= 0.0))
  					V = 2 * Actual_Amplitude * 1;
  				V /= 2;
                  return V;
                //  break;
              }

    	else
    	if (Type==type.PWM)
              {
            	  //Transforme en -1 1
            	  time*=Freq;
            	  time = time-(int)time;

            	  time-=0.5f;
            	  time*=2;
            	  
              	if ((time) <= Actual_PWM*2-1.0f) 
              		return Actual_Amplitude;
  				else
  					return -Actual_Amplitude;
              	
              //  break;
              }

              
          
    	  return 0;
	}
	

    private static float[] create_SINUS_TABLE()
	{
    	SINUS = new float[MaxFrequency*2]; // La bonne table merveilleusement lourde :p
        for (int i = -MaxFrequency; i < MaxFrequency; i++)
        {
        	SINUS[i+MaxFrequency] = (float) Math.sin (Math.PI*2*((float)i/(float)MaxFrequency));
        }
        return SINUS;
	}

    @PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE, gui_type=gui_type.COMBO)
	public synchronized type getOscType() {
		return OscType;
	}

	public synchronized void setOscType(type oscType) {
		OscType = oscType;
	}
	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized float getActual_Phase() {
		return Actual_Phase;
	}

	public synchronized void setActual_Phase(float actual_Phase) {
		Actual_Phase = actual_Phase;
	}

	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized float getActual_PWM() {
		return Actual_PWM;
	}

	public synchronized void setActual_PWM(float actual_PWM) {
		Actual_PWM = actual_PWM;
	}
	
}
