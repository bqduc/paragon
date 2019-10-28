package net.paragon.csx.service;

import net.paragon.csx.entity.Book;
import net.paragon.exceptions.ObjectNotFoundException;
import net.paragon.framework.service.GenericService;

public interface BookService extends GenericService<Book, Long> {
	Book getByIsbn(String isbn) throws ObjectNotFoundException;
	Book getByIsbn13(String isbn13) throws ObjectNotFoundException;
}
