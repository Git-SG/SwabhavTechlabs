package com.aurionpro.list.model;

public class Book {
	
	private String title;
	private String author;
	private double price;
	private int publicationYear;
	
	public Book(String title, String author, double price, int publicationYear) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.publicationYear = publicationYear;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", price=" + price + ", publicationYear="
				+ publicationYear + "]";
	}
	
	

}
