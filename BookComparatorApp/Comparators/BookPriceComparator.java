package com.aurionpro.list.comparator;

import java.util.Comparator;

import com.aurionpro.list.model.Book;

public class BookPriceComparator implements Comparator<Book>{

	@Override
	public int compare(Book book1, Book book2) {
		if (book1.getPrice() > book2.getPrice())
			return 1;
		if (book1.getPrice() < book2.getPrice())
			return -1;
		return 0;
	}

}
