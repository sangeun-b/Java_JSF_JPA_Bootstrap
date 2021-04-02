/*****************************************************************c******************o*******v******id********
 * File: CustomerPojo.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Sangeun Baek 040953608
 *
 */
package com.algonquincollege.cst8277.customers2.model;

import static com.algonquincollege.cst8277.customers2.model.CustomerPojo.ALL_CUSTOMERS_QUERY_NAME;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.view.ViewScoped;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
*
* Description: model for the Customer object
*/
@Entity(name = "Customer")
@Table(name = "customer")
@Access(AccessType.PROPERTY)
@NamedQueries(
    @NamedQuery(name=ALL_CUSTOMERS_QUERY_NAME, query = "select c from Customer c")
)
@EntityListeners({CustomerPojoListener.class})
public class CustomerPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ALL_CUSTOMERS_QUERY_NAME =
        "allCustomers";

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected int version;
    protected LocalDateTime created; 
    protected LocalDateTime updated; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id of the customer
     * @return current id
     */
    public int getId() {
        return id;
    }
    /**
     * save the value of the id
     * @param - id new value for id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * first name of the customer
     * @return current value for firstName
     */
    @Column(name = "FNAME")
    public String getFirstName() {
        return firstName;
    }
    /**
     * save the value of the first name
     * @param - firstName new value for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * last name of the customer
     * @return current value for lastName
     */
    @Column(name = "LNAME")
    public String getLastName() {
        return lastName;
    }
    /**
     * save the value of the last name
     * @param - lastName new value for lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * email of the customer
     * @return current value for email
     */
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    /**
     * save the value of the email
     * @param email - new value for email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * phone number of the customer
     * @return current value for phoun number
     */
    @Column(name = "PHONENUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * save the value of the phone number
     * @param phoneNumber - new valud for phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // these methods now used in Assignment 2
    /**
     * version of the customer
     * @return current version
     */
    @Version
    public int getVersion() {
        return version;
    }
    /**
     * save the value of the version
     * @param version - new value for version
     */
    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * created date of the customer 
     * @return current created date
     */
    @Column(name = "CREATED")
    public LocalDateTime getCreatedDate() {
        return created;
    }
    /**
     * save the creatd date
     * @param created - new value for created date
     */
    public void setCreatedDate(LocalDateTime created) {
        this.created = created;
    }
    /**
     * updated date of the customer
     * @return current updated value
     */
    @Column(name = "UPDATED")
    public LocalDateTime getUpdatedDate() {
        return updated;
    }
    /**
     * savea the updated date
     * @param updated - new value for updated date
     */
    public void setUpdatedDate(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerPojo)) {
            return false;
        }
        CustomerPojo other = (CustomerPojo) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Customer [id=")
            .append(id)
            .append(", ");
        if (firstName != null) {
            builder
                .append("firstName=")
                .append(firstName)
                .append(", ");
        }
        if (lastName != null) {
            builder
                .append("lastName=")
                .append(lastName)
                .append(", ");
        }
        if (phoneNumber != null) {
            builder
                .append("phoneNumber=")
                .append(phoneNumber)
                .append(", ");
        }
        if (email != null) {
            builder
                .append("email=")
                .append(email)
                .append(", ");
        }
        builder.append("]");
        return builder.toString();
    }
    
    boolean editable;
    /**
     * save the editable value true or false
     * @param editable - value of editable
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
        
    }
    @Transient
    /**
     * editable of the customer
     * @return current editable
     */
    public boolean getEditable() {
        return editable;
    }

    
}