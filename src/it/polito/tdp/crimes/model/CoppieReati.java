package it.polito.tdp.crimes.model;

public class CoppieReati {
	
	String v1;
	String v2;
	int peso;
	

	
	public CoppieReati(String v1, String v2, int peso) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.peso = peso;
	}
	public String getV1() {
		return v1;
	}
	public String getV2() {
		return v2;
	}
	public int getPeso() {
		return peso;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("v1 ");
		builder.append(v1);
		builder.append(" v2 ");
		builder.append(v2);
		builder.append(" peso: ");
		builder.append(peso);
		return builder.toString();
	}
	
	
	
	

}
