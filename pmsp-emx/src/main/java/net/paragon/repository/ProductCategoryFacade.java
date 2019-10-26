package net.paragon.repository;


import java.util.List;

import org.springframework.stereotype.Component;

import net.paragon.entity.ProductCategory;
import net.paragon.framework.repository.BaseDAO;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Component//@Stateless
public class ProductCategoryFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4052390386859804013L;

		public ProductCategory create(ProductCategory entity) {
        em.persist(entity);
        return entity;
    }

    public ProductCategory update(ProductCategory entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(ProductCategory entity) {
        em.remove(em.merge(entity));
    }

    public ProductCategory find(Object id) {
        return em.find(ProductCategory.class, id);
    }
    

		public List<ProductCategory> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ProductCategory.class));
        return em.createQuery(cq).getResultList();
    }

    public List<ProductCategory> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ProductCategory.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<ProductCategory> rt = cq.from(ProductCategory.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
