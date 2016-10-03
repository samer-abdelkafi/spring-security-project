package com.mycompany.dao.hibernate;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.CustomerOrderDao;
import com.mycompany.entity.CustomerOrder;


/**
 *
 * @author abdelkafi_s
 */
@Repository
public class CustomerOrderDaoImp  implements CustomerOrderDao{

	@PersistenceContext
	private EntityManager em;

   public Collection<CustomerOrder> getAll() {
	   Query query = em.createQuery("from CustomerOrder");
       return query.getResultList();
    }

    public CustomerOrder getById(Long id) {
    	return em.find(CustomerOrder.class, id);
    }

    @Transactional
    public void save(CustomerOrder customerOrder) {
    	em.persist(customerOrder);
    }

    @Transactional
    public void delete(Long id) {
    	CustomerOrder customerOrder = em.find(CustomerOrder.class, id);  
    	em.remove(customerOrder);
    }

}
