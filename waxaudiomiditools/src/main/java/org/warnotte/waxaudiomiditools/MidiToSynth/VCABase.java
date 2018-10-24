package org.warnotte.waxaudiomiditools.MidiToSynth;

import org.warnotte.OBJ2GUI.Annotations.GUI_CLASS;
import org.warnotte.OBJ2GUI.Annotations.GUI_FIELD_TYPE;
import org.warnotte.obj2gui2.PROPERTY_button;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCA_2ND;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCO;
import org.warnotte.waxlib2.TemplatePropertyMerger.property_mode;
import org.warnotte.waxlib2.TemplatePropertyMerger.Annotations.PROPERTY_FIELD_XXXABLE;
import org.warnotte.waxlib2.TemplatePropertyMerger.Annotations.PROPERTY_interface;

@GUI_CLASS(type = GUI_CLASS.Type.BoxLayout, BoxLayout_property = GUI_CLASS.Type_BoxLayout.Y)
public abstract class VCABase {

	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.TEXTFIELD)
	private String name = "";

	@PROPERTY_FIELD_XXXABLE
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.JPANEL, jPanelLocation = "CControlers.GUI.Panel_VCA2ND", jPanelParamDTOC="CControlers.SignGen_VCA_2ND")
	public SignGen_VCA_2ND MOD_VCA = new SignGen_VCA_2ND();
	
//	private transient float Value = 0;
	private transient int notes = 0;
	
	private en_Status st = en_Status.OFF;
	
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.CHECKBOX)
	private boolean Manual = false;
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.CHECKBOX)
	private boolean Syncable = true; // Pour faire que le timer repasse a 0;

	private transient long timer = 0;
	private transient int depth = 0;
	private long prevTimer = System.currentTimeMillis();
	
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.METHOD_CALL, method_name="setLearn")
	private transient boolean Learn = false;
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.METHOD_CALL, method_name="test")
	private transient boolean Test1 = false;
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.METHOD_CALL, method_name="sync")
	private transient boolean Sync = false;
	
	@PROPERTY_button(method_name="setLearn", text="Learn")
	public transient Object dummy_Learn = null;
	@PROPERTY_button(method_name="test", text="Test")
	public transient Object dummy_Test1 = null;
	@PROPERTY_button(method_name="sync", text="Sync")
	public transient Object dummy_Sync = null;
	
	
	
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.TEXTFIELD) float multiplier=1.0f;

	@PROPERTY_FIELD_XXXABLE
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.PANELISABLE)
	public SignGen_VCO MOD_VCO = new SignGen_VCO();
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.CHECKBOX)
	private boolean BypassVCA = true; // Pour faire que le timer repasse a 0;
	
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.TEXTFIELD)
	private float Amount = 1.0f;
	@GUI_FIELD_TYPE(type = GUI_FIELD_TYPE.Type.TEXTFIELD)
	private float decayValue = 0.05f;
	private transient float decayCCValue = 0; // 0 a 1 vient d'un CC recu pour moduler ca
	
	public VCABase(String string) {
		this.name = string;
	}
	public VCABase() {
		super();
	}

	public void test() {
		sync();
		noteOn(0);
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		noteOff();
	}

	public synchronized boolean isLearn() {
		return Learn;
	}

	public void setLearn() {
		Learn=true;
		MidiToSynth.learn=true;
	}

	public synchronized void noteOn(int note) {
		
	
			if (depth==0)
			{
				if (Syncable)
				{
				timer=0;
						MOD_VCO.sync();
						MOD_VCA.sync();
				}
			MOD_VCA.setGATE(true);
			
			}
		
		if (Learn==false)
		depth++;
	//	Value = 1.0f;
		notes = note;
	//	System.err.println("On");
	}

	public synchronized void sync() {
		timer=0;
	}

	public synchronized void noteOff() {
		depth--;
		if (depth <= 0) // Ceci avec le clavier amene parfois des kists quand on appuye sur plusieur touches j'ai l'impression ... (mais c ptet a cause du BIP BIP clavier)
		{
			getMOD_VCA().setGATE(false);
			st = en_Status.ON;
		}
		if (depth<0) depth=0;
	}

	
	
	public synchronized void evolue() {
		long elapsed = System.currentTimeMillis()-prevTimer;
		prevTimer = System.currentTimeMillis();
		timer+=elapsed;
		getMOD_VCO().evolue(0, elapsed, 1.0f);
		getMOD_VCA().evolue(0, elapsed, 1.0f);
		
		
		
	}

	public synchronized float getValue() {
		
		if (Manual==true)
			return Amount;
		
		float Value=0;
		if (depth>0)
			Value=1;
		float ret = Value;
		//if (BypassVCA==false)
			//if (target_VCA==TARGET_DESTINATION.AMOUNT)
			ret=getMOD_VCA().getValue();
			if (getMOD_VCO().isBypassSend()==false)
				//if (target_VCO==TARGET_DESTINATION.AMOUNT)
				ret*=getMOD_VCO().getValue();

		return ret;
	}

	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized float getDecayValue() {
		return decayValue;
	}

	public synchronized void setDecayValue(float decayValue) {
		this.decayValue = decayValue;
	}

	public synchronized int getNotes() {
		return notes;
	}

	public synchronized void setNotes(int notes) {
		this.notes = notes;
	}
	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public float getAmount() {
		return Amount;
	}

	public synchronized void setAmount(float amount) {
		Amount = amount;
	}

	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized boolean isManual() {
		return Manual;
	}

	public synchronized void setManual(boolean manual) {
		Manual = manual;
	}

	public void setLearn(boolean b) {
		this.Learn=b;
	}

	public synchronized long getTimer() {
		if (multiplier==0)
			multiplier=1.0f;
		return (long) (timer*multiplier);
	}

	public synchronized void setTimer(long timer) {
		this.timer = timer;
	}

	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized boolean isSyncable() {
		return Syncable;
	}

	public synchronized void setSyncable(boolean syncable) {
		Syncable = syncable;
	}

	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized float getMultiplier() {
		return multiplier;
	}

	public synchronized void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}

	
	public synchronized SignGen_VCO getMOD_VCO() {
		if (MOD_VCO==null)
			MOD_VCO=new SignGen_VCO();
		return MOD_VCO;
	}

	public synchronized void setMOD_VCO(SignGen_VCO mOD_VCO) {
		MOD_VCO = mOD_VCO;
	}

	public synchronized SignGen_VCA_2ND getMOD_VCA() {
		if (MOD_VCA==null)
		{
			MOD_VCA=new SignGen_VCA_2ND();
			
			
		}
		return MOD_VCA;
	}

	public synchronized void setMOD_VCA(SignGen_VCA_2ND MOD_VCA) {
		this.MOD_VCA = MOD_VCA;
	}

	public synchronized boolean isBypassVCA() {
		return BypassVCA;
	}

	public synchronized void setBypassVCA(boolean bypassVCA) {
		BypassVCA = bypassVCA;
	}

	@PROPERTY_interface(Operation=property_mode.PROPERTY_MERGEABLE)
	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
	
	public abstract void controlChange(Object number, int value);
	public void setDecayCCValue(float decayCCValue) {
		this.decayCCValue = decayCCValue;
	}
	public float getDecayCCValue() {
		return decayCCValue;
	}
	public  en_Status getSt() {
		return st;
	}
	public synchronized void setSt(en_Status st) {
		this.st = st;
	}
	public synchronized int getDepth() {
		return depth;
	}
	
	
	
}