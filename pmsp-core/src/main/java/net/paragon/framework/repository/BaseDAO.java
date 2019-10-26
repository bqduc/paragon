/**
 * 
 */
package net.paragon.framework.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author ducbq
 *
 */
public abstract class BaseDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3317395762045169767L;
	@PersistenceContext /* (unitName = GlobalConstants.APPLICATION_PERSISTENCE_UNIT) */
	protected EntityManager em;
}
