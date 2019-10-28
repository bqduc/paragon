package net.paragon.csx.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paragon.csx.entity.Book;
import net.paragon.csx.repository.BookRepository;
import net.paragon.exceptions.ExecutionContextException;
import net.paragon.exceptions.ObjectNotFoundException;
import net.paragon.framework.model.ExecutionContext;
import net.paragon.framework.repository.BaseRepository;
import net.paragon.framework.service.GenericServiceImpl;

@Service
public class BookServiceImpl extends GenericServiceImpl<Book, Long> implements BookService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2757500334847725484L;

	@Inject 
	private BookRepository repository;
	
	protected BaseRepository<Book, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Book getByIsbn(String isbn) throws ObjectNotFoundException {
		return repository.findByIsbn(isbn).orElse(null);
	}

	@Override
	public Book getByIsbn13(String isbn13) throws ObjectNotFoundException {
		return repository.findByIsbn13(isbn13).orElse(null);
	}

	@Override
	public ExecutionContext load(ExecutionContext executionContext) throws ExecutionContextException {
		return super.load(executionContext);
	}
}
