
package net.paragon.repository;


import java.util.List;

import org.springframework.stereotype.Component;

import net.paragon.entity.ProductUom;
import net.paragon.framework.repository.BaseDAO;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Component//@Stateless
public class ProductUomFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2939495381088495265L;


		public ProductUom create(ProductUom entity) {
        
        em.persist(entity);
        return entity;
    }

    public ProductUom update(ProductUom entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(ProductUom entity) {
        em.remove(em.merge(entity));
    }

    public ProductUom find(Object id) {
        return em.find(ProductUom.class, id);
    }
    

    public List<ProductUom> findActiveUoms() {
        List<ProductUom> productUoms = em.createNamedQuery("ProductUom.findByActive")
                .setParameter("active", true)
                .getResultList();

        return productUoms;
    }

    
    public List<ProductUom> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ProductUom.class));
        return em.createQuery(cq).getResultList();
    }

}
