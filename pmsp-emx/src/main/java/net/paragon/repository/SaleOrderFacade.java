
package net.paragon.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import net.paragon.entity.Account;
import net.paragon.entity.DeliveryOrder;
import net.paragon.entity.Invoice;
import net.paragon.entity.Journal;
import net.paragon.entity.Partner;
import net.paragon.entity.Product;
import net.paragon.entity.SaleOrder;
import net.paragon.entity.SaleOrderLine;
import net.paragon.framework.repository.BaseDAO;
import net.paragon.utility.IdGenerator;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Component//@Stateless
public class SaleOrderFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1426016172500253678L;
		private IdGenerator idGeerator = new IdGenerator();

    public SaleOrder create(SaleOrder entity) {
        em.persist(entity);
        em.flush();
        entity.setName(idGeerator.generateSaleId(entity.getId()));
        return entity;
    }

    public void create(DeliveryOrder entity) {
        em.persist(entity);
        em.flush();
        entity.setName(idGeerator.generateDeliveryOutId(entity.getId()));

    }

    public Invoice create(Invoice entity) {
        em.persist(entity);
        em.flush();
        entity.setName(idGeerator.generateInvoiceOutId(entity.getId()));
        return entity;

    }

    public SaleOrder update(SaleOrder entity) {
        em.merge(entity);
        return entity;
    }

    public Invoice update(Invoice entity) {
        em.merge(entity);
        return entity;
    }

    public DeliveryOrder update(DeliveryOrder entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(SaleOrder entity) {
        em.remove(em.merge(entity));
    }

    public SaleOrder find(Object id) {
        return em.find(SaleOrder.class, id);
    }

    public SaleOrderLine findOrderLine(Object id) {
        return em.find(SaleOrderLine.class, id);
    }

    public Account findAccount(Object name) {

        List<Account> accounts = em.createNamedQuery("Account.findByName")
                .setParameter("name", name)
                .getResultList();

        if (accounts != null && !accounts.isEmpty()) {
            return accounts.get(0);
        }

        return null;
    }

    public Journal findJournal(Object code) {
        List<Journal> journals = em.createNamedQuery("Journal.findByCode")
                .setParameter("code", code)
                .getResultList();

        if (journals != null && !journals.isEmpty()) {
            return journals.get(0);
        }

        return null;
    }

    public List<SaleOrder> findDrafts() {
        List<SaleOrder> draftOrders = em.createNamedQuery("SaleOrder.findDrafts")
                .setParameter("draft", "Draft Quotation")
                .setParameter("cancelled", "Cancelled")
                .getResultList();

        return draftOrders;
    }

    public List<SaleOrder> findConfirmed() {
        List<SaleOrder> confirmedOrders = em.createNamedQuery("SaleOrder.findConfirmed")
                .setParameter("draft", "Draft Quotation")
                .setParameter("cancelled", "Cancelled")
                .getResultList();

        return confirmedOrders;
    }

    public List<SaleOrder> findByPartner(Integer partnerId) {
        List<SaleOrder> OrdersByPartner = em.createNamedQuery("SaleOrder.findByPartner")
                .setParameter("partnerId", partnerId)
                .getResultList();

        return OrdersByPartner;
    }

    public List<SaleOrderLine> findByProduct(Integer productId) {
        List<SaleOrderLine> OrderLinesByProduct = em.createNamedQuery("SaleOrderLine.findByProduct")
                .setParameter("productId", productId)
                .getResultList();

        return OrderLinesByProduct;
    }

    public List<Partner> findTopNCustomers(int n) {
        List result = em.createNamedQuery("Partner.findByCustomer")
                .setMaxResults(n)
                .getResultList();

        return result;
    }

    public List<Product> findTopNSoldProducts(int n) {
        List result = em.createNamedQuery("Product.findBySaleOk")
                .setMaxResults(n)
                .getResultList();

        return result;
    }

    public List<SaleOrder> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(SaleOrder.class));
        return em.createQuery(cq).getResultList();
    }

}
