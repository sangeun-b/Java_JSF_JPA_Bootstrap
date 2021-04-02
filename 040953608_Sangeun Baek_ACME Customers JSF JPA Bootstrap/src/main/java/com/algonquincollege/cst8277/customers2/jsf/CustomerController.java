/*****************************************************************c******************o*******v******id********
 * File: CustomerController.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Sangeun Baek 040953608
 *
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.algonquincollege.cst8277.customers2.dao.CustomerDao;
import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * Description: Responsible for collection of Customer Pojo's in XHTML (list) <h:dataTable> </br>
 * Delegates all C-R-U-D behaviour to DAO
 */
@Named("customerController")
@SessionScoped
public class CustomerController implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UICONSTS_BUNDLE_EXPR = "#{uiconsts}";
    public static final String CUSTOMER_MISSING_REFRESH_BUNDLE_MSG = "refresh";
    public static final String CUSTOMER_OUTOFDATE_REFRESH_BUNDLE_MSG = "outOfDate";

    @Inject
    protected FacesContext facesContext;

    @Inject
    protected ServletContext sc;

    @Inject
    protected CustomerDao customerDao;

    @Inject
    @ManagedProperty(UICONSTS_BUNDLE_EXPR)
    protected ResourceBundle uiconsts;
    
    protected List<CustomerPojo> customers;
    protected boolean adding = false;
    
    /**
     * retrive customers
     */
    public void loadCustomers() {
        logMsg("loadCustomers");
        customers = customerDao.readAllCustomers();
    }
    
    /**
     *retrive customer list
     * @return current List<CustomerPojo> customers
     */
    public List<CustomerPojo> getCustomers() {
        return this.customers;
    }
    /**
     * save the value of customer list
     * @param customers - value of the customer
     */
    public void setCustomers(List<CustomerPojo> customers) {
        this.customers = customers;
    }
    /**
     * retrive the value of the adding
     * @return current adding
     */
    public boolean isAdding() {
        return adding;
    }
    /**
     * save the value of adding
     * @param adding - value for adding
     */
    public void setAdding(boolean adding) {
        this.adding = adding;
    }

    /**
     * Toggles the add customer mode which determines whether the addCustomer form is rendered
     */
    public void toggleAdding() {
        this.adding = !this.adding;
    }
    /**
     * customer is editable
     * @param cust - is editable
     * @return current page
     */
    public String editCustomer(CustomerPojo cust) {
        logMsg("editCustomer");
        cust.setEditable(true);
        return null; //current page
    }
    
    /**
     * update existing customer
     * @param customerWithEdits - will be updated
     * @return current page
     */
    public String updateCustomer(CustomerPojo customerWithEdits) {
        logMsg("updateCustomer");
        CustomerPojo customerToBeUpdated = customerDao.readCustomerById(customerWithEdits.getId());
        if (customerToBeUpdated == null) {
            // someone else deleted it
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                uiconsts.getString(CUSTOMER_MISSING_REFRESH_BUNDLE_MSG), null));
        }
        else {
            customerToBeUpdated = customerDao.updateCustomer(customerWithEdits);
            if (customerToBeUpdated == null) {
                //OptimisticLockException 
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    uiconsts.getString(CUSTOMER_OUTOFDATE_REFRESH_BUNDLE_MSG), null));
            }
            else {
                customerToBeUpdated.setEditable(false);
                int idx = customers.indexOf(customerWithEdits);
                customers.remove(idx);
                customers.add(idx, customerToBeUpdated);
            }
        }
        return null; //current page
    }
    /**
     * cancle update
     * @param cust - will be cancled
     * @return current page
     */
    public String cancelUpdate(CustomerPojo cust) {
        logMsg("cancelUpdate");
        cust.setEditable(false);
        return null; //current page
    }
    
    /**
     * delete existing customer
     * @param custId - want to delete
     */
    public void deleteCustomer(int custId) {
        logMsg("deleteCustomer: " + custId);
        CustomerPojo customerToBeRemoved = customerDao.readCustomerById(custId);
        if (customerToBeRemoved != null) {
            customerDao.deleteCustomerById(custId);
            customers.remove(customerToBeRemoved);
        }
    }
    
    /**
     * add new cusotmer
     * @param theNewCustomer - will be added
     */
    public void addNewCustomer(CustomerPojo theNewCustomer) {
        logMsg("adding new Customer");
        CustomerPojo newCust = customerDao.createCustomer(theNewCustomer);
        customers.add(newCust);
    }
    
    /**
     * refresh the custoemr form
     * @return refresh page
     */
    public String refreshCustomerForm() {
        logMsg("refreshCustomerForm");
        Iterator<FacesMessage> facesMessageIterator = facesContext.getMessages();
        while (facesMessageIterator.hasNext()) {
            facesMessageIterator.remove();
        }
        return "index.xhtml?faces-redirect=true";
    }

    protected void logMsg(String msg) {
        sc.log(msg);
    }
}