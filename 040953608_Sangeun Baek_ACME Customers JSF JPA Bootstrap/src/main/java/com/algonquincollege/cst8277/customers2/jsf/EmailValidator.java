/*****************************************************************c******************o*******v******id********
 * File: EmailValidator.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Sangeun Baek 040953608
 *
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("email")
public class EmailValidator implements Validator<String>{

    // really really (!) simple email pattern: at-least-1-letter, '@', at-least-1-letter
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");
    
    /**
     * Description: validate the email format
     */
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        
        if (value == null) {
            FacesMessage msg = new FacesMessage("Email should not be empty", "Invalid Email format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
        }
		// TODO - use Matcher and Pattern to figure out if the value is a valid email
        else {
            Matcher matcher = EMAIL_PATTERN.matcher(value);
            boolean matches = matcher.matches();
            if(matches == false) {
            FacesMessage msg = new FacesMessage("Enter valid email");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            }
           
            
            }
    }

}