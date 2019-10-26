/**
 * 
 */
package net.paragon.framework.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author bqduc
 *
 */
@NoRepositoryBean
public interface CodeBaseRepository<T, PK extends Serializable> extends JBaseRepository<T, PK> {
	Optional<T> findByCode(String code);
}
