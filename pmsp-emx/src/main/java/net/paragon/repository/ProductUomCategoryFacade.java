
package net.paragon.repository;


import java.util.List;

import org.springframework.stereotype.Component;

import net.paragon.entity.ProductUomCategory;
import net.paragon.framework.repository.BaseDAO;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Component//@Stateless
public class ProductUomCategoryFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4888900886182454977L;


		public ProductUomCategory  create(ProductUomCategory  entity) {
        em.persist(entity);
        return entity;
    }
    

    public ProductUomCategory  update(ProductUomCategory  entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(ProductUomCategory  entity) {
        em.remove(em.merge(entity));
    }

    public ProductUomCategory  find(Object id) {
        return em.find(ProductUomCategory .class, id);
    }

    
    public List<ProductUomCategory > findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ProductUomCategory.class));
        return em.createQuery(cq).getResultList();
    }

}
