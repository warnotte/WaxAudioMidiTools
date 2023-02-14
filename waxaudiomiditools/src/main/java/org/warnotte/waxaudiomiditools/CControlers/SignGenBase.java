package org.warnotte.waxaudiomiditools.CControlers;

import javax.swing.JFrame;

import io.github.warnotte.obj2gui2.PROPERTY_button;
import io.github.warnotte.waxlib3.OBJ2GUI.Annotations.GUI_CLASS;
import io.github.warnotte.waxlib3.OBJ2GUI.Annotations.GUI_FIELD_TYPE;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;
import io.github.warnotte.waxlib3.waxlibswingcomponents.Utils.Curve.Copiable;



@GUI_CLASS(type=GUI_CLASS.Type.BoxLayout, BoxLayout_property=GUI_CLASS.Type_BoxLayout.Y)
public class SignGenBase implements Syncable , Copiable
{

	@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
	float Mix = 1.0f;
	
	@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.METHOD_CALL, method_name="configCC")
	@PROPERTY_button(method_name="configCC", text="CC Config")
	private final Object Bouton_2 = null; // Px importe son type en fait.
	@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
	int CC = 74;

	transient float value = 0;
	transient float old_value = 0;
	transient int time = 0; // in ms.
	transient long old_elapsedtime=0; // in ms.
	transient long dif_time = 0;
	
	@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.CHECKBOX)
	boolean BypassSend = false;
	
	@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.METHOD_CALL, method_name="sync")
	@PROPERTY_button(method_name="sync", text="Sync")
	private final Object Bouton_1 = null; // Px importe son type en fait.
	
	protected String name;
		
	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized float getMix() {
		return Mix;
	}

	public synchronized void setMix(float mix) {
		Mix = mix;
	}
	
	public void evolue(int Channel, long elapsed, float valeur_preset)
	{
		synchronized(this)
		{
			if (old_elapsedtime!=0)
			dif_time = elapsed;//(long)(elapsed-old_elapsedtime);
			time+=dif_time;
			
			old_elapsedtime=elapsed;
			
			
			
		}
		
		
	}
	
	public synchronized float getValue()
	{
	//	if (value!=0.0)
	//		System.err.println("otkeo");
		return value;
	}

	public synchronized void setValue(float value)
	{
		this.value = value;
	}

	
	public synchronized void sync()
	{
		time=0;
	}

	/**
	 * Recupere le temps interne 
	 * @return
	 */
	public float getInternalTime()
	{
		return time/1000f;
	}
	
	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized int getCC()
	{
		return CC;
	}

	public synchronized void setCC(int cc)
	{
		CC = cc;
	}

/*	public void setChannel(int channel)
	{
		_ccdest.setChannel(channel);
	}*/

	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized boolean isBypassSend()
	{
		return BypassSend;
	}

	public synchronized void setBypassSend(boolean bypassSend)
	{
		BypassSend = bypassSend;
	}



	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}

	
	public void copysettings(Copiable to) {
		((SignGenBase)to).setCC(this.getCC());
		((SignGenBase)to).setMix(Mix);
		((SignGenBase)to).setBypassSend(this.isBypassSend());
		
	}
	
	public void configCC()
	{
		System.err.println("Config CC");
		Dialog_ChangeCC dc = new Dialog_ChangeCC(new JFrame(), getCC());
		dc.setModal(true);
		dc.setVisible(true);
		System.err.println("Value recue == "+dc.getCC());
		this.setCC(dc.getCC());
	}

	
}
