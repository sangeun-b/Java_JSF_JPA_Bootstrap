/*****************************************************************c******************o*******v******id********
 * File: CustomerDaoImpl.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Sangeun Baek 040953608
 *
 */
package com.algonquincollege.cst8277.customers2.dao;

import static com.algonquincollege.cst8277.customers2.model.CustomerPojo.ALL_CUSTOMERS_QUERY_NAME;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.customers2.ejb.CustomerService;
import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
* Description: Implements the C-R-U-D API for the database
*/
@Named
@ApplicationScoped
public class CustomerDaoImpl implements CustomerDao, Serializable {
    /** explicitly set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    @EJB
    protected CustomerService customerService;
    
    
    protected ServletContext sc;

    @Inject
    public CustomerDaoImpl(ServletContext sc) {
        super();
        this.sc = sc;
    }
    
    protected void logMsg(String msg) {
        sc.log(msg);
    }
    

    // delegate all C-R-U-D operations to EntityManager

    @Override
    public List<CustomerPojo> readAllCustomers() {
        logMsg("reading all customers");
        return customerService.getAllCustomers();
    }

    @Override
    
    public CustomerPojo createCustomer(CustomerPojo customerToBeCreated) {
        logMsg("creating an customer");
        return customerService.insertNewCustomer(customerToBeCreated);
        
    }

    @Override
    public CustomerPojo readCustomerById(int customerId) {
        logMsg("read a specific customer");
        return customerService.findCustomerById(customerId);
    }

    @Override
    public CustomerPojo updateCustomer(CustomerPojo customerWithUpdates) {
        logMsg("updating a specific customer");
        customerService.mergeCustomer(customerWithUpdates);
        return customerWithUpdates;
        
    }

    @Override
    
    public void deleteCustomerById(int customerId) {
        logMsg("deleting a specific customer");
        customerService.removeCustomer(customerId);
        }
    }
