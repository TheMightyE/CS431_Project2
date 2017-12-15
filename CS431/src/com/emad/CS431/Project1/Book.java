package com.emad.CS431.Project1;

public class Book {
	private String isbn;
	private String qty;
	private String title;
	private String author;
	private double cost;
	private String type;

	public Book(String isbn, String qty, String title, String author, double cost, String type) {
		this.isbn = isbn;
		this.qty = qty;
		this.title = title;
		this.author = author;
		this.cost = cost;
		this.type = type;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getQty() {
		return qty;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public double getCost() {
		return cost;
	}

	public String getType() {
		return type;
	}

}
