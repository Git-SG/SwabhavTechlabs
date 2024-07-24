package com.aurionpro.list.comparator;

import java.util.Comparator;

import com.aurionpro.list.model.Book;

public class BookAuthorComparator implements Comparator<Book>{
	
	@Override
	public int compare(Book book1, Book book2) {
		
		return book1.getAuthor().compareToIgnoreCase(book2.getAuthor());
	}

}
