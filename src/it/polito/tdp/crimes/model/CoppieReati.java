package it.polito.tdp.crimes.model;

public class CoppieReati {
	
	String v1;
	String v2;
	public CoppieReati(String v1, String v2) {
		super();
		this.v1 = v1;
		this.v2 = v2;
	}
	public String getV1() {
		return v1;
	}
	public String getV2() {
		return v2;
	}
	@Override
	public String toString() {
		return "CoppieReati [v1=" + v1 + ", v2=" + v2 + "]";
	}
	
	
	

}
