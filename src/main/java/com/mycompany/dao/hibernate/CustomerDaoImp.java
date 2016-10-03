package com.mycompany.dao.hibernate;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.CustomerDao;
import com.mycompany.entity.Customer;


/**
 *
 * @author abdelkafi_s
 */
@Repository("customerDao")
public class CustomerDaoImp implements CustomerDao{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImp.class);
	
	@PersistenceContext
	private EntityManager em;

    public Collection<Customer> getAll() {
    	Query query = em.createQuery("from Customer");
        return query.getResultList();
    }
    
    public Collection<Customer> findByName(String name) {
    	Query query = em.createQuery("from Customer as c where c.name like :name");
    	query.setParameter("name", name);
        return query.getResultList();
    }

    public Customer getById(Long id) {
        return em.find(Customer.class, id);
    }

    
    @Transactional
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public void save(Customer customer) {
    	logger.debug("start save customer, name: {}", customer.getName());
        em.persist(customer);
        logger.debug("customer saved, name: {}, Id: {}", customer.getName(), customer.getCustomerId());
    }

    @Transactional
    public void delete(Long id) {
    	Customer customer = em.find(Customer.class, id);  
    	em.remove(customer);
    }
	   
}
