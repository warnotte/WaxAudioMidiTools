package org.warnotte.waxaudiomiditools.MidiToSynth;

public class Tuple<S1, S2> {

	private S1 s1;
	private S2 s2;
	
	public Tuple(S1 s1, S2 s2)
	{
		this.setS1(s1);
		this.setS2(s2);
	}

	public void setS2(S2 s2) {
		this.s2 = s2;
	}

	public S2 getS2() {
		return s2;
	}

	public void setS1(S1 s1) {
		this.s1 = s1;
	}

	public S1 getS1() {
		return s1;
	}
}
