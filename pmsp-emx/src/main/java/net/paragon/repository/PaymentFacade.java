package net.paragon.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import net.paragon.entity.Account;
import net.paragon.entity.Journal;
import net.paragon.entity.JournalEntry;
import net.paragon.entity.Partner;
import net.paragon.entity.Payment;
import net.paragon.framework.repository.BaseDAO;
import net.paragon.utility.IdGenerator;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Component//@Stateless
public class PaymentFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4384797523311756834L;
		private IdGenerator idGeerator = new IdGenerator();

    public Payment create(Payment entity) {
        em.persist(entity);
        return entity;
    }

    public Payment update(Payment entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(Payment entity) {
        em.remove(em.merge(entity));
    }

    public Payment find(Object id) {
        return em.find(Payment.class, id);
    }

    public List<Payment> findAll() {
        List<Payment> partners = em.createNamedQuery("Payment.findAll")
                .getResultList();
        return partners;
    }

    public List<Payment> findSupplierPayment() {
        List<Payment> payments = em.createNamedQuery("Payment.findByPartnerType")
                .setParameter("partnerType", "supplier")
                .getResultList();
        return payments;
    }

    public List<Payment> findCustomerPayment() {
        List<Payment> payments = em.createNamedQuery("Payment.findByPartnerType")
                .setParameter("partnerType", "customer")
                .getResultList();
        return payments;
    }

    public List<Partner> findTopNCustomers(int n) {
        List result = em.createNamedQuery("Partner.findByCustomer")
                .setMaxResults(n)
                .getResultList();

        return result;
    }
    
    public List<Partner> findTopNSuppliers(int n) {
        List result = em.createNamedQuery("Partner.findBySupplier")
                .setMaxResults(n)
                .getResultList();

        return result;
    }

    public Payment create(Payment entity, String partnerType, String type) {
        if (entity != null) {

            em.persist(entity);
            em.flush();

            if (partnerType.equals("Customer") && type.equals("in")) {
                entity.setName(idGeerator.generateCustomerInPayment(entity.getId()));
            } else if (partnerType.equals("Customer") && type.equals("out")) {
                entity.setName(idGeerator.generateCustomerOutPayment(entity.getId()));
            } else if (partnerType.equals("Supplier") && type.equals("in")) {
                entity.setName(idGeerator.generateSupplierInPayment(entity.getId()));
            } else if (partnerType.equals("Supplier") && type.equals("out")) {
                entity.setName(idGeerator.generateSupplierOutPayment(entity.getId()));
            }
        }
        return entity;
    }
  
    
    public List<Payment> findByPartner(Integer partnerId, String partnerType) {
        List<Payment> payments = em.createNamedQuery("Payment.findByPartner")
                .setParameter("partnerId", partnerId)
                .setParameter("partnerType", partnerType)
                .getResultList();

        return payments;
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

    public JournalEntry create(JournalEntry entity, String account) {
        em.persist(entity);
        em.flush();
        if (account.equals("Cash")) {
            entity.setName(idGeerator.generatePaymentCashEntryId(entity.getId()));
        } else if (account.equals("Bank")) {
            entity.setName(idGeerator.generatePaymentBankEntryId(entity.getId()));
        }
        return entity;
    }
}
