
package net.paragon.repository;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import net.paragon.entity.Journal;
import net.paragon.entity.JournalItem;
import net.paragon.framework.repository.BaseDAO;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Component//@Stateless
public class JournalItemFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2316171188128195552L;

		public Journal findJournal(Object code) {
         List<Journal> journals = em.createNamedQuery("Journal.findByCode")
                .setParameter("code", code)
                .getResultList();

        if (journals != null && !journals.isEmpty()) {
            return journals.get(0);
        }

        return null;
    }


    public List<JournalItem> findJournalItems(Integer month, Journal journal) {

        DateTime date = new DateTime();
        Date monthStart = null;
        Date monthEnd = null;

        if (month != null) {
            monthStart = date.withMonthOfYear(month).dayOfMonth().withMinimumValue().toDate();
            monthEnd = date.withMonthOfYear(month).dayOfMonth().withMaximumValue().toDate();
        }
        Date yearStart = date.withMonthOfYear(1).dayOfMonth().withMinimumValue().toDate();
        Date yearEnd = date.withMonthOfYear(12).dayOfMonth().withMaximumValue().toDate();

        List<JournalItem> journalItems;

        if (month != null && journal != null) {

            journalItems = em.createNamedQuery("JournalItem.findByJournalPeriod")
                    .setParameter("monthStart", monthStart)
                    .setParameter("monthEnd", monthEnd)
                    .setParameter("journalId", journal.getId())
                    .getResultList();

            return journalItems;

        } else if (month != null && journal == null) {

            journalItems = em.createNamedQuery("JournalItem.findByPeriod")
                    .setParameter("monthStart", monthStart)
                    .setParameter("monthEnd", monthEnd)
                    .getResultList();

            return journalItems;

        } else if (month == null && journal != null) {

            journalItems = em.createNamedQuery("JournalItem.findByJournal")
                    .setParameter("yearStart", yearStart)
                    .setParameter("yearEnd", yearEnd)
                    .setParameter("journalId", journal.getId())
                    .getResultList();

            return journalItems;

        } else {

            journalItems = em.createNamedQuery("JournalItem.findAll").getResultList();
            return journalItems;
        }

    }

    public JournalItem find(Object id) {
        return em.find(JournalItem.class, id);
    }

    public List<JournalItem> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(JournalItem.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Journal> findAllJournals() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Journal.class));
        return em.createQuery(cq).getResultList();
    }

}
