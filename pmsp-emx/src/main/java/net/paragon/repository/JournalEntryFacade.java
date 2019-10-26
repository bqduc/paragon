
package net.paragon.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import net.paragon.entity.JournalEntry;
import net.paragon.framework.repository.BaseDAO;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Component//@Stateless
public class JournalEntryFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 502156519652915032L;

		public JournalEntry find(Object id) {
        return em.find(JournalEntry.class, id);
    }
    

    public List<JournalEntry> findByPartner(Integer partnerId) {
        List<JournalEntry> JournalEntriesByPartner = em.createNamedQuery("JournalEntry.findByPartner")
                .setParameter("partnerId", partnerId)
                .getResultList();
        
        return JournalEntriesByPartner;  
    }
 
    public List<JournalEntry> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(JournalEntry.class));     
        return em.createQuery(cq).getResultList();
    }

}
