/**
 * 
 */
package net.paragon.config.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paragon.config.entity.Configuration;
import net.paragon.framework.repository.BaseRepository;

/**
 * @author ducbui
 *
 */
@Repository
public interface ConfigurationRepository extends BaseRepository <Configuration, Long>{
	Optional<Configuration> findByName(String name);
	List<Configuration> findByGroup(String group);

	@Query("SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.name) like LOWER(CONCAT('%',:keyword,'%')) or"
			+ " LOWER(entity.value) like LOWER(CONCAT('%',:keyword,'%')) or"
			+ " LOWER(entity.valueExtended) like LOWER(CONCAT('%',:keyword,'%')) or"
			+ " LOWER(entity.info) like LOWER(CONCAT('%',:keyword,'%'))"
			+ ")"
	)
	Page<Configuration> search(@Param("keyword") String keyword, Pageable pageable);
}
