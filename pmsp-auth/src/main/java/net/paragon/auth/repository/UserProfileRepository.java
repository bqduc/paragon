/**
 * 
 */
package net.paragon.auth.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paragon.auth.entity.UserProfile;
import net.paragon.framework.repository.BaseRepository;

/**
 * @author ducbui
 *
 */
@Repository
public interface UserProfileRepository extends BaseRepository <UserProfile, Long>{

	@Query("SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.login) like LOWER(CONCAT('%',:keyword,'%'))"
			+ ")"
	)
	Page<UserProfile> search(@Param("keyword") String keyword, Pageable pageable);
}
