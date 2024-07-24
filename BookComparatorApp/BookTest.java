package com.aurionpro.list.test;

import com.aurionpro.list.comparator.BookAuthorComparator;
import com.aurionpro.list.comparator.BookPriceComparator;
import com.aurionpro.list.comparator.BookPublicationYearComparator;
import com.aurionpro.list.comparator.BookTitleComparator;
import com.aurionpro.list.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		List<Book> books = new ArrayList<Book>();
		
//		System.out.println("Enter number of books");
//		int numberOfBooks = scanner.nextInt();
//		createBooks(books, numberOfBooks, scanner);  // This is for user input
//		printBooks(books);
		
		books.add(new Book("STU", "Sarthak", 1000, 2010));
		books.add(new Book("abc", "gangan", 2000, 2011));
		books.add(new Book("123", "sarthak", 1500, 2009));
		books.add(new Book("Xyz", "gangan", 1100, 2010));
		books.add(new Book("ijk", "Gangan", 1000, 2011));
		
		printBooks(books);

		System.out.println("\nBooks sorted by --> Author, Title, Price");
		
		Collections.sort(books, new BookAuthorComparator().thenComparing(new BookTitleComparator()).thenComparing(new BookPriceComparator()));
		printBooks(books);
		
		System.out.println("\nBooks sorted by -->Publication year, Author, Price");
		
		Collections.sort(books, new BookPublicationYearComparator().thenComparing(new BookAuthorComparator()).thenComparing(new BookPriceComparator()));
		printBooks(books);

	}

	private static void printBooks(List<Book> books) {
		for(Book book: books)
			System.out.println(book);
	}

	private static void createBooks(List<Book> books, int numberOfBooks, Scanner scanner) {
		
		System.out.println("Enter Book Title: ");
		String title = scanner.nextLine();
		System.out.println("Enter Author name: ");
		String author = scanner.nextLine();
		System.out.println("Enter Price:");
		double price = scanner.nextDouble();
		System.out.println("Enter publication year: ");
		int publicationYear = scanner.nextInt();
		
		books.add(new Book(title, author, price, publicationYear));
	}
	

}
