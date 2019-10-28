/**
 * 
 */
package net.paragon.csx.repository.vbb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paragon.csx.entity.vbb.VbbTopic;
import net.paragon.framework.repository.BaseRepository;

/**
 * @author bqduc
 *
 */
@Repository
public interface VbbTopicRepository extends BaseRepository<VbbTopic, Long> {
	VbbTopic findByName(String name);

	@Query("SELECT entity FROM #{#entityName} entity "
			+ "WHERE ("
			+ " LOWER(entity.name) like LOWER(CONCAT('%',:keyword,'%'))"
			+ ")"
	)
	Page<VbbTopic> search(@Param("keyword") String keyword, Pageable pageable);
}
