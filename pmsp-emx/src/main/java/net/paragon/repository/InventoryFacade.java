
package net.paragon.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import net.paragon.entity.Inventory;
import net.paragon.framework.repository.BaseDAO;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Component//@Stateless
public class InventoryFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7574890157662248050L;


		public Inventory create(Inventory entity) {
        em.persist(entity);
        return entity;
    }

    public Inventory update(Inventory entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(Inventory entity) {
        em.remove(em.merge(entity));
    }

    public Inventory find(Object id) {
        return em.find(Inventory.class, id);
    }


    public List<Inventory> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Inventory.class));
        return em.createQuery(cq).getResultList();
    }
}
