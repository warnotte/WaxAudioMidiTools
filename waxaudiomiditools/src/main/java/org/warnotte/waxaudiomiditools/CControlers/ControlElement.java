package org.warnotte.waxaudiomiditools.CControlers;

public class ControlElement {

	private String Label;
	private int CC;
	
	private boolean NRPN=false;
	private int MSB=-1;
	private int LSB=-1;
	private int OtherValue=-1;
	private boolean doubleNRPN=false;
	
	
	
	public String toString()
	{
		return Label+" #"+CC;
	}
	
	public synchronized int getMSB() {
		return MSB;
	}

	public synchronized void setMSB(int msb) {
		MSB = msb;
	}

	public synchronized int getLSB() {
		return LSB;
	}

	public synchronized void setLSB(int lsb) {
		LSB = lsb;
	}

	public synchronized int getOtherValue() {
		return OtherValue;
	}

	public synchronized void setOtherValue(int otherValue) {
		OtherValue = otherValue;
	}

	public ControlElement(String label, int cc) {
		super();
		setLabel(label);
		setCC(cc);
	}
	
	public ControlElement(String label, int MSB, int LSB, int OtherValue) {
		super();
		NRPN=true;
		setLabel(label);
		setMSB(MSB);
		setLSB(LSB);
		setOtherValue(OtherValue);
	}
	public ControlElement(String label, int MSB, int LSB, boolean doubleNRPN) {
		this(label, MSB, LSB,-1);
		this.doubleNRPN=doubleNRPN;
	}

	public synchronized boolean isNRPN() {
		return NRPN;
	}

	public synchronized void setNRPN(boolean nrpn) {
		NRPN = nrpn;
	}

	public void setCC(int cC) {
		CC = cC;
	}

	public int getCC() {
		return CC;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getLabel() {
		return Label;
	}

	public synchronized boolean isDoubleNRPN() {
		return doubleNRPN;
	}

	public synchronized void setDoubleNRPN(boolean doubleNRPN) {
		this.doubleNRPN = doubleNRPN;
	}
	
}
