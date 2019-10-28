/**
 * 
 */
package net.paragon.csx.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paragon.csx.entity.Book;
import net.paragon.framework.repository.BaseRepository;

/**
 * @author bqduc
 *
 */
@Repository
public interface BookRepository extends BaseRepository<Book, Long> {
	Page<Book> findAll(Pageable pageable);
	Page<Book> findAllByOrderByIdAsc(Pageable pageable);
	Optional<Book> findByIsbn(String isbn);
	Optional<Book> findByIsbn13(String isbn13);

	@Query("SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.isbn) like LOWER(CONCAT('%',:keyword,'%'))"
			+ "or LOWER(entity.isbne) like LOWER(CONCAT('%',:keyword,'%')) "
			+ "or LOWER(entity.isbno) like LOWER(CONCAT('%',:keyword,'%')) "
			+ "or LOWER(entity.isbn13) like LOWER(CONCAT('%',:keyword,'%')) "
			+ "or LOWER(entity.isbn13e) like LOWER(CONCAT('%',:keyword,'%')) "
			+ "or LOWER(entity.isbn13o) like LOWER(CONCAT('%',:keyword,'%')) "
			+ "or LOWER(entity.title) like LOWER(CONCAT('%',:keyword,'%')) "
			+ "or LOWER(entity.author) like LOWER(CONCAT('%',:keyword,'%')) "
			+ ")"
	)
	Page<Book> search(@Param("keyword") String keyword, Pageable pageable);
}
