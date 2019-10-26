/**
 * 
 */
package net.paragon.repository;

import org.springframework.stereotype.Repository;

import net.paragon.entity.Pond;
import net.paragon.framework.repository.CodeBaseRepository;

/**
 * @author bqduc
 *
 */
@Repository
public interface PondRepository extends CodeBaseRepository<Pond, Long> {
}
