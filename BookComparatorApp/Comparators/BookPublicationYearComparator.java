package com.aurionpro.list.comparator;

import java.util.Comparator;

import com.aurionpro.list.model.Book;

public class BookPublicationYearComparator implements Comparator<Book> {

	@Override
	public int compare(Book book1, Book book2) {
		
		return book1.getPublicationYear() - book2.getPublicationYear();
	}

}
