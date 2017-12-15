package com.emad.CS431.Project1;

public class Movie {
	private int id;
	private String title, qty;
	private double cost;
	int year;
	private String type;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getQty() {
		return qty;
	}

	public double getCost() {
		return cost;
	}

	public int getYear() {
		return year;
	}

	public String getType() {
		return type;
	}

	public Movie(int id, String title, String qty, double cost, int year, String type) {
		this.id = id;
		this.title = title;
		this.qty = qty;
		this.cost = cost;
		this.year = year;
		this.type = type;
	}

}
