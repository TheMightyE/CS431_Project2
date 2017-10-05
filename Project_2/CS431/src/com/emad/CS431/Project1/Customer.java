package com.emad.CS431.Project1;

public class Customer {
	private int id;
	private String item, type, phone, name;

	public int getId() {
		return id;
	}

	public String getItem() {
		return item;
	}

	public String getType() {
		return type;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public Customer(int id, String item, String type, String phone, String name) {
		this.id = id;
		this.item = item;
		this.type = type;
		this.phone = phone;
		this.name = name;
	}

}
