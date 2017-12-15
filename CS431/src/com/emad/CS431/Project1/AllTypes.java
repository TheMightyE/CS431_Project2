package com.emad.CS431.Project1;

public class AllTypes {
	private String type, title;
	private String quantity;
	private double cost;

	public AllTypes(String type, String title, String quantity, double cost) {
		this.type = type;
		this.title = title;
		this.quantity = quantity;
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getQuantity() {
		return quantity;
	}

	public double getCost() {
		return cost;
	}

}
