package com.michalak.model;

public class Circle {

	public Circle() {}
	
	
	public Circle(int circleId, String name) {
		setName(name);
		setId(circleId);
	}	
	

	
	private int id;
	private String name;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	


}
