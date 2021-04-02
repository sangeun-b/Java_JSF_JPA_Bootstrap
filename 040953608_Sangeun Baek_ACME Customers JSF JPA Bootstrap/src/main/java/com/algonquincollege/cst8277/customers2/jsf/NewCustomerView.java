/*****************************************************************c******************o*******v******id********
 * File: NewCustomerView.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Sangeun Baek 040953608
 *
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

@Named(value= "newCustomer")
@ViewScoped
public class NewCustomerView implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
   
    
    // TODO - add additional required fields

    @Inject
    @ManagedProperty("#{customerController}")
    protected CustomerController employeeController;

    public NewCustomerView() {
    }

    /**
     * first name of the customer
     * @return value of the first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * save the first name
     * @param firstName  firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * last name of the customer
     * @return  value of the lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * save the last name
     * @param LastName  LastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * email of the customer
     * @return value of the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * save the eamil
     * @param email email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * phone number of the customer
     * @return value of the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * save the phone number
     * @param phoneNumber phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    /**
     * add new customer
     */
    public void addCustomer() {
        if (allNotNullOrEmpty(firstName, lastName, email, phoneNumber)) {
            CustomerPojo theNewCustomer = new CustomerPojo();
            theNewCustomer.setFirstName(getFirstName());
            theNewCustomer.setLastName(getLastName());
            theNewCustomer.setEmail(getEmail());
            theNewCustomer.setPhoneNumber(getPhoneNumber());
                      
            
            // TODO - additional fields

            // this Managed Bean does not know how to 'do' anything, ask controller
            employeeController.addNewCustomer(theNewCustomer);

            //clean up
            employeeController.toggleAdding();
            setFirstName(null);
            setLastName(null);
            setEmail(null);
            setPhoneNumber(null);
                       
            // TODO clean up additional fields
        }
    }
    
    /**
     * check the value of the customer is null or empty
     * @param values - custoemr values want to add
     * @return true - not null or empty
     */
    static boolean allNotNullOrEmpty(final Object... values) {
        if (values == null) {
            return false;
        }
        for (final Object val : values) {
            if (val == null) {
                return false;
            }
            if (val instanceof String) {
                String str = (String)val;
                if (str.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}