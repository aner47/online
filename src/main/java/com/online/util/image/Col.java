package com.online.util.image;

public class Col {

	String label;
	
	String value;
	
	int colspan =1;

	public Col(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
	public Col(String label, String value,int colspan) {
		super();
		this.label = label;
		this.value = value;
		this.colspan = colspan;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return label + "：" + value;
	}
}
