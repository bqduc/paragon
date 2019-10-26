package net.paragon.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import net.paragon.framework.repository.BaseDAO;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Component//@Stateless
public class DashBoardFacade extends BaseDAO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6934442447856263010L;

		public List<Object[]> getQuarters(int interval) {

        String query = "SELECT ";

                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "concat(YEAR(CURRENT_DATE - interval '" + i + " Quarter'),'-Q',QUARTER(CURRENT_DATE - interval '" + i + " Quarter')), ";
                    } else {
                        query += "concat(YEAR(CURRENT_DATE - interval '" + i + " Quarter'),'-Q',QUARTER(CURRENT_DATE - interval '" + i + " Quarter')); ";
                    }
                }

        Query q = em.createNativeQuery(query);
        return q.getResultList();
    }


    public List<Object[]> salesOrderCount(int interval, String period) {
    	List<Object[]> results = new ArrayList<>();
        String query = "SELECT ";

        switch (period) {
            case "Day":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN DATE(date) = SUBDATE(CURRENT_DATE," + i + ") THEN 1 ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN DATE(date) = SUBDATE(CURRENT_DATE," + i + ") THEN 1 ELSE 0 END) ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN 1 ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN 1 ELSE 0 END) ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN concat(YEAR(date),QUARTER(date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN 1 ELSE 0 END),";
                    } else {
                        query += "SUM(CASE WHEN concat(YEAR(date),QUARTER(date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN 1 ELSE 0 END) ";
                    }
                }
                break;
            default:
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN yearweek(date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN 1 ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN yearweek(date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN 1 ELSE 0 END) ";
                    }
                }
                break;
        }

        query += "FROM sale_order;";

        Query q = em.createNativeQuery(query);
        try {
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;
    }

    public List<Object[]> salesAmount(int interval, String period) {
    	List<Object[]> results = new ArrayList<>();
        String query = "SELECT ";

        switch (period) {
            case "Day":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN DATE(item.date) = SUBDATE(CURRENT_DATE," + i + ") THEN item.credit ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN DATE(item.date) = SUBDATE(CURRENT_DATE," + i + ") THEN item.credit ELSE 0 END) ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN item.credit ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN item.credit ELSE 0 END) ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN concat(YEAR(item.date),QUARTER(item.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN item.credit ELSE 0 END),";
                    } else {
                        query += "SUM(CASE WHEN concat(YEAR(item.date),QUARTER(item.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN item.credit ELSE 0 END) ";
                    }
                }
                break;    
            default:
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN yearweek(item.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN item.credit ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN yearweek(item.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN item.credit ELSE 0 END) ";
                    }
                }
                break;
        }

        query += "FROM journal_item item \n"
                + "join journal journal on item.journal_id = journal.id \n"
                + "WHERE journal.name = 'Customer Invoices' ;";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;
    }
    
     public List<Object[]> costOfGoodsSold(int interval, String period) {
     	List<Object[]> results = new ArrayList<>();
        String query = "SELECT ";

        switch (period) {
            case "Day":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN DATE(item.date) = SUBDATE(CURRENT_DATE," + i + ") THEN item.cost_of_goods_sold ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN DATE(item.date) = SUBDATE(CURRENT_DATE," + i + ") THEN item.cost_of_goods_sold ELSE 0 END) ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN item.cost_of_goods_sold ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN item.cost_of_goods_sold ELSE 0 END) ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN concat(YEAR(item.date),QUARTER(item.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN item.cost_of_goods_sold ELSE 0 END),";
                    } else {
                        query += "SUM(CASE WHEN concat(YEAR(item.date),QUARTER(item.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN item.cost_of_goods_sold ELSE 0 END) ";
                    }
                }
                break;    
            default:
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN yearweek(item.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN item.cost_of_goods_sold ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN yearweek(item.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN item.cost_of_goods_sold ELSE 0 END) ";
                    }
                }
                break;
        }

        query += "FROM journal_item item \n"
                + "join journal journal on item.journal_id = journal.id \n"
                + "WHERE journal.name = 'Customer Invoices' ;";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;
    }

    
     public List<Object[]> profit(int interval, String period) {
     	List<Object[]> results = new ArrayList<>();
        String query = "SELECT ";

        switch (period) {
            case "Day":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN DATE(item.date) = SUBDATE(CURRENT_DATE," + i + ") THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN DATE(item.date) = SUBDATE(CURRENT_DATE," + i + ") THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN concat(YEAR(item.date),QUARTER(item.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END),";
                    } else {
                        query += "SUM(CASE WHEN concat(YEAR(item.date),QUARTER(item.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) ";
                    }
                }
                break;    
            default:
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN yearweek(item.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN yearweek(item.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) ";
                    }
                }
                break;
        }

        query += "FROM journal_item item \n"
                + "join journal journal on item.journal_id = journal.id \n"
                + "WHERE journal.name = 'Customer Invoices' ;";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;

    }


    public List<Object[]> PurchasesAmount(int interval, String period) {
    	List<Object[]> results = new ArrayList<>();
        String query = "SELECT ";

        switch (period) {
            case "Day":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN DATE(item.date) = SUBDATE(CURRENT_DATE," + i + ") THEN item.debit ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN DATE(item.date) = SUBDATE(CURRENT_DATE," + i + ") THEN item.debit ELSE 0 END) ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN item.debit ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN item.debit ELSE 0 END) ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN concat(YEAR(item.date),QUARTER(item.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN item.debit ELSE 0 END),";
                    } else {
                        query += "SUM(CASE WHEN concat(YEAR(item.date),QUARTER(item.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN item.debit ELSE 0 END) ";
                    }
                }
                break;    
            default:
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN yearweek(item.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN item.debit ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN yearweek(item.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN item.debit ELSE 0 END) ";
                    }
                }
                break;
        }

        query += "FROM journal_item item \n"
                + "join journal journal on item.journal_id = journal.id \n"
                + "WHERE journal.name = 'Vendor Bills' ;";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;

    }

   
    public List<Object[]> invoiceCount(int interval, String period) {
    	List<Object[]> results = new ArrayList<>();
        String query = "SELECT ";

        if (period.equals("Month")) {
            for (int i = 0; i < interval; i++) {
                if (i < interval - 1) {
                    query += "SUM(CASE WHEN TO_CHAR(date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN 1 ELSE 0 END), ";
                } else {
                    query += "SUM(CASE WHEN TO_CHAR(date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN 1 ELSE 0 END) ";
                }
            }
        }

        if (period.equals("Week")) {
            for (int i = 0; i < interval; i++) {
                if (i < interval - 1) {
                    query += "SUM(CASE WHEN yearweek(date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN 1 ELSE 0 END), ";
                } else {
                    query += "SUM(CASE WHEN yearweek(date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN 1 ELSE 0 END) ";
                }
            }
        }

        query += "FROM  invoice WHERE state <> 'Cancelled' GROUP BY state ORDER BY state ASC;";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;
    }

    public List<Object[]> topSalesByProduct(int nProducts, String period, int interval) {
    	List<Object[]> results = new ArrayList<>();
        if (interval < 0) {
            interval = 0;
        }

        if (nProducts < 0) {
            nProducts = 5;
        }

        String query = "SELECT  pr.name , SUM(line.sub_total), SUM(line.quantity) "
                + "FROM sale_order_line line "
                + "join product pr on line.product_id = pr.id "
                + "join sale_order sale on line.order_id = sale.id WHERE ";

        switch (period) {
            case "Month":
                query += "TO_CHAR(sale.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + interval + " MONTH', 'YYYYMM') ";
                break;
            case "Quarter":
                query += "concat(YEAR(sale.date),QUARTER(sale.date)) = concat(YEAR(CURRENT_DATE - interval '" + interval + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + interval + " QUARTER'))";
                break;      
            case "Day":
                query += "DATE(sale.date) = SUBDATE(CURRENT_DATE," + interval + ") ";
                break;
            default:
                query += "yearweek(sale.date) = yearweek(CURRENT_DATE - interval '" + interval + " week') ";
                break;
        }

        query += "GROUP BY pr.name ORDER BY SUM(line.sub_total) DESC LIMIT " + nProducts + ";";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;
    }

    public List<Object[]> topPurchasesByProduct(int nProducts, String period, int interval) {
    	List<Object[]> results = new ArrayList<>();
        if (interval < 0) {
            interval = 0;
        }

        if (nProducts < 0) {
            nProducts = 5;
        }

        String query = "SELECT  pr.name , SUM(line.sub_total), SUM(line.quantity) "
                + "FROM purchase_order_line line "
                + "join product pr on line.product_id = pr.id "
                + "join purchase_order purchase on line.order_id = purchase.id WHERE ";

        switch (period) {
            case "Month":
                query += "TO_CHAR(purchase.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + interval + " MONTH', 'YYYYMM') ";
                break;
            case "Quarter":
                query += "concat(YEAR(purchase.date),QUARTER(purchase.date)) = concat(YEAR(CURRENT_DATE - interval '" + interval + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + interval + " QUARTER'))";
                break;    
            case "Day":
                query += "DATE(purchase.date) = SUBDATE(CURRENT_DATE," + interval + ") ";
                break;
            default:
                query += "yearweek(purchase.date) = yearweek(CURRENT_DATE - interval '" + interval + " week') ";
                break;
        }

        query += "GROUP BY pr.name ORDER BY SUM(line.sub_total) DESC LIMIT " + nProducts + ";";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;

    }

    public List<Object[]> topPurchasesByVendor(int nPartners, String period, int interval) {
    	List<Object[]> results = new ArrayList<>();
        if (interval < 0) {
            interval = 0;
        }

        if (nPartners < 0) {
            nPartners = 5;
        }

        String query = "SELECT  par.name , SUM(purchase.amount_total), count(*)\n"
                + "FROM purchase_order purchase join partner par on purchase.partner_id = par.id WHERE ";

        switch (period) {
            case "Month":
                query += "TO_CHAR(purchase.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + interval + " MONTH', 'YYYYMM') ";
                break;
            case "Quarter":
                query += "concat(YEAR(purchase.date),QUARTER(purchase.date)) = concat(YEAR(CURRENT_DATE - interval '" + interval + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + interval + " QUARTER'))";
                break;      
            case "Day":
                query += "DATE(purchase.date) = SUBDATE(CURRENT_DATE," + interval + ") ";
                break;
            default:
                query += "yearweek(purchase.date) = yearweek(CURRENT_DATE - interval '" + interval + " week') ";
                break;
        }

        query += "AND par.supplier = 1 GROUP BY par.name ORDER BY SUM(purchase.amount_total) DESC LIMIT " + nPartners + ";";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;

    }

    public List<Object[]> topSalesByCustomer(int nPartners, String period, int interval) {
    	List<Object[]> results = new ArrayList<>();
        if (interval < 0) {
            interval = 0;
        }

        if (nPartners < 0) {
            nPartners = 5;
        }
        
        String query = "SELECT  par.name , SUM(sale.amount_total), count(*)\n"
                + "FROM sale_order sale join partner par on sale.partner_id = par.id WHERE ";

        switch (period) {
            case "Month":
                query += "TO_CHAR(sale.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + interval + " MONTH', 'YYYYMM') ";
                break;
            case "Quarter":
                query += "concat(YEAR(sale.date),QUARTER(sale.date)) = concat(YEAR(CURRENT_DATE - interval '" + interval + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + interval + " QUARTER'))";
                break;    
            case "Day":
                query += "DATE(sale.date) = SUBDATE(CURRENT_DATE," + interval + ") ";
                break;
            default:
                query += "yearweek(sale.date) = yearweek(CURRENT_DATE - interval '" + interval + " week') ";
                break;
        }

        query += "AND par.customer = 1 GROUP BY par.name ORDER BY SUM(sale.amount_total) DESC LIMIT " + nPartners + ";";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;

    }

    public List<Object[]> newCustomers(int interval, String period) {
    	List<Object[]> results = new ArrayList<>();
        String query = "SELECT ";

        switch (period) {
            case "Day":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN DATE(create_date) = SUBDATE(CURRENT_DATE," + i + ") THEN 1 ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN DATE(create_date) = SUBDATE(CURRENT_DATE," + i + ") THEN 1 ELSE 0 END) ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(create_date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN 1 ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(create_date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN 1 ELSE 0 END) ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN concat(YEAR(create_date),QUARTER(create_date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN 1 ELSE 0 END),";
                    } else {
                        query += "SUM(CASE WHEN concat(YEAR(create_date),QUARTER(create_date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN 1 ELSE 0 END) ";
                    }
                }
                break;    
            default:
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN yearweek(create_date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN 1 ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN yearweek(create_date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN 1 ELSE 0 END) ";
                    }
                }
                break;
        }

        query += "FROM partner WHERE customer = 1;";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;

    }

    public List<Object[]> salesOrdersToConfirm() {

        String query = "SELECT count(*), COALESCE(SUM(amount_total),0)\n"
                + "FROM sale_order \n"
                + "WHERE state = 'Quotation' ; ";

        Query q = em.createNativeQuery(query);
        return q.getResultList();

    }

    public List<Object[]> purchaseOrdersToConfirm() {

        String query = "SELECT count(*), COALESCE(SUM(amount_total),0)\n"
                + "FROM purchase_order \n"
                + "WHERE state = 'Quotation' ; ";

        Query q = em.createNativeQuery(query);
        return q.getResultList();

    }

    public List<Object[]> invoicesToConfirm() {

        String query = "SELECT count(*), COALESCE(SUM(amount_total),0)\n"
                + "FROM invoice \n"
                + "WHERE state = 'Draft' AND type='Sale' ; ";

        Query q = em.createNativeQuery(query);
        return q.getResultList();

    }

    public List<Object[]> billsToConfirm() {

        String query = "SELECT count(*), COALESCE(SUM(amount_total),0)\n"
                + "FROM invoice \n"
                + "WHERE state = 'Draft' AND type='Purchase' ; ";

        Query q = em.createNativeQuery(query);
        return q.getResultList();

    }

    public List<Object[]> topReceivablesByCustomer() {

        String query = "SELECT  par.name , SUM(inv.residual)\n"
                + "FROM invoice inv join partner par on inv.partner_id = par.id \n"
                + "WHERE  inv.state = 'Open' AND type='Sale' AND inv.residual > 0 \n"
                + "GROUP BY par.name\n"
                + "ORDER BY SUM(inv.residual) DESC limit 5;";

        Query q = em.createNativeQuery(query);
        return q.getResultList();
    }
    
    public List<Object[]> topPayablesByVendor() {

        String query = "SELECT  par.name , SUM(inv.residual)\n"
                + "FROM invoice inv join partner par on inv.partner_id = par.id \n"
                + "WHERE  inv.state = 'Open' AND type='Purchase' AND inv.residual > 0 \n"
                + "GROUP BY par.name\n"
                + "ORDER BY SUM(inv.residual) DESC limit 5;";

        Query q = em.createNativeQuery(query);
        return q.getResultList();
    }

    public List<Object[]> receivables() {

        String query = "SELECT  SUM(inv.residual)\n"
                + "FROM invoice inv \n"
                + "WHERE  inv.state = 'Open' AND type = 'Sale';";

        Query q = em.createNativeQuery(query);
        return q.getResultList();

    }

    public List<Object[]> payables() {

        String query = "SELECT  SUM(inv.residual)\n"
                + "FROM invoice inv \n"
                + "WHERE  inv.state = 'Open' AND type = 'Purchase';";

        Query q = em.createNativeQuery(query);
        return q.getResultList();

    }

    public List<Object[]> customerPayment(int interval, String period) {
    	List<Object[]> results = new ArrayList<>();
        String query = "SELECT ";

        switch (period) {
            case "Day":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN DATE(invPay.date) = SUBDATE(CURRENT_DATE," + i + ") THEN invPay.paid_amount ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN DATE(invPay.date) = SUBDATE(CURRENT_DATE," + i + ") THEN invPay.paid_amount ELSE 0 END) ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN invPay.paid_amount ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN invPay.paid_amount ELSE 0 END) ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN concat(YEAR(invPay.date),QUARTER(invPay.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN invPay.paid_amount ELSE 0 END),";
                    } else {
                        query += "SUM(CASE WHEN concat(YEAR(invPay.date),QUARTER(invPay.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN invPay.paid_amount ELSE 0 END) ";
                    }
                }
                break;     
            default:
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN yearweek(invPay.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN invPay.paid_amount ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN yearweek(invPay.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN invPay.paid_amount ELSE 0 END) ";
                    }
                }
                break;
        }

        query += "FROM invoice_payment invPay \n"
                + "join journal_entry entry on invPay.journal_entry_id = entry.id \n"
                + "join payment pay on pay.entry_id = entry.id  \n"
                + "join journal journal on pay.journal_id = journal.id    \n"
                + "where pay.type = 'in' AND pay.partner_type = 'customer' AND invPay.name <> 'Write-Off' \n"
                + "GROUP BY journal.name ORDER BY journal.name ASC;";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;

    }

    public List<Object[]> vendorPayment(int interval, String period) {
    	List<Object[]> results = new ArrayList<>();
        String query = "SELECT ";

        switch (period) {
            case "Day":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN DATE(invPay.date) = SUBDATE(CURRENT_DATE," + i + ") THEN invPay.paid_amount ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN DATE(invPay.date) = SUBDATE(CURRENT_DATE," + i + ") THEN invPay.paid_amount ELSE 0 END) ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN invPay.paid_amount ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'YYYYMM') = TO_CHAR(CURRENT_DATE - interval '" + i + " MONTH', 'YYYYMM') THEN invPay.paid_amount ELSE 0 END) ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN concat(YEAR(invPay.date),QUARTER(invPay.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN invPay.paid_amount ELSE 0 END),";
                    } else {
                        query += "SUM(CASE WHEN concat(YEAR(invPay.date),QUARTER(invPay.date)) = concat(YEAR(CURRENT_DATE - interval '" + i + " QUARTER'), QUARTER(CURRENT_DATE - interval '" + i + " QUARTER')) THEN invPay.paid_amount ELSE 0 END) ";
                    }
                }
                break;    
            default:
                for (int i = 0; i < interval; i++) {
                    if (i < interval - 1) {
                        query += "SUM(CASE WHEN yearweek(invPay.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN invPay.paid_amount ELSE 0 END), ";
                    } else {
                        query += "SUM(CASE WHEN yearweek(invPay.date) = yearweek(CURRENT_DATE - interval '" + i + " week') THEN invPay.paid_amount ELSE 0 END) ";
                    }
                }
                break;
        }

        query += "FROM invoice_payment invPay \n"
                + "join journal_entry entry on invPay.journal_entry_id = entry.id \n"
                + "join payment pay on pay.entry_id = entry.id  \n"
                + "join journal journal on pay.journal_id = journal.id    \n"
                + "where pay.type = 'out' AND pay.partner_type = 'supplier' AND invPay.name <> 'Write-Off' \n"
                + "GROUP BY journal.name ORDER BY journal.name ASC;";

        try {
            Query q = em.createNativeQuery(query);
        	results = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return results;
    }

}
