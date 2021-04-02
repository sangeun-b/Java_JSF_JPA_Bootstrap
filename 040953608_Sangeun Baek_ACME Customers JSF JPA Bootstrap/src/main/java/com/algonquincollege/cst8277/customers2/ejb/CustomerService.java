/**
 * File : CustomerService.java
 * Course materials (20F) CST 8277
 * 
 * @author (original) Mike Norman
 * 
 * updated by : I. am. Sangeun Baek. Student 040953608
 */
package com.algonquincollege.cst8277.customers2.ejb;

import static com.algonquincollege.cst8277.customers2.model.CustomerPojo.ALL_CUSTOMERS_QUERY_NAME;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * Stateless Singleton Session Bean - CustomerService
 */

@Singleton
public class CustomerService {
    private static final long serialVersionUID = 1L;
    
    public static final String CUSTOMER_PU = "acmeCustomers-PU";
    
    @PersistenceContext(name = CUSTOMER_PU)
    protected EntityManager em;
    
    /**
     * retrive all customer
     * @return all customer
     */
    public List<CustomerPojo> getAllCustomers() {
        TypedQuery<CustomerPojo> allCustomersQuery = em.createNamedQuery(ALL_CUSTOMERS_QUERY_NAME, CustomerPojo.class);
        return allCustomersQuery.getResultList();
    }
    @Transactional
    /**
     * add new customer
     * @param customerToBeCreated - will be added
     * @return added customer
     */
    public CustomerPojo insertNewCustomer(CustomerPojo customerToBeCreated) {
        em.persist(customerToBeCreated);
        return customerToBeCreated;
    }
    /**
     * select the customer using id
     * @param customerId - want to find
     * @return find customer
     */
    public CustomerPojo findCustomerById(int customerId) {
        return em.find(CustomerPojo.class, customerId);
    }
    @Transactional
    /**
     * update customer
     * @param customerWithUpdates - will be updated
     */
    public void mergeCustomer(CustomerPojo customerWithUpdates) {
        CustomerPojo customerToBeUpdated = findCustomerById(customerWithUpdates.getId());
        if(customerToBeUpdated != null) {
            em.merge(customerWithUpdates);
        }
    }
    @Transactional
    /**
     * delete customer
     * @param customerId - id of the customer will be deleted
     */
    public void removeCustomer(int customerId) {
        CustomerPojo customer = findCustomerById(customerId);
        if (customer != null) {
            em.refresh(customer);
            em.remove(customer);
    }
    
    }
}
