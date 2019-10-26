
package net.paragon.repository;


import java.util.List;

import org.springframework.stereotype.Component;

import net.paragon.auth.entity.UserProfile;
import net.paragon.framework.repository.BaseDAO;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Component//@Stateless
public class UserFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8466731564400416922L;


		public UserProfile create(UserProfile entity) {
        em.persist(entity);
        return entity;
    }
    

    public UserProfile update(UserProfile entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(UserProfile entity) {
        em.remove(em.merge(entity));
    }

    public UserProfile find(Object id) {
        return em.find(UserProfile.class, id);
    }

    
    public List<UserProfile> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(UserProfile.class));
        return em.createQuery(cq).getResultList();
    }

}
