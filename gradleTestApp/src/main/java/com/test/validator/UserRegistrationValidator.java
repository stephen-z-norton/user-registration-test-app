package com.test.validator;

import com.test.dao.UserRegistrationDAO;
import com.test.model.UserRegistrationDataModel;
import java.util.regex.*;
/**
 * Validation class for user registration
 */
public class UserRegistrationValidator {
	private final static String UNITED_STATES = "US";
	private final static Pattern zipRegex = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$");
	/**
	 * Validates a user registration record
	 * @param modelIn
	 * @return
	 */
	public String validate(UserRegistrationDataModel modelIn) {
		StringBuffer validationErrors = new StringBuffer();
		
		try {
				UserRegistrationDAO userRegistrationDAO = new UserRegistrationDAO();
				
				if(modelIn.getCountry() != null && modelIn.getCountry().length() > 2) {
					validationErrors.append("<strong>Error </strong>Country is too long!<br/> ");
				} else if (!modelIn.getCountry().equals(UNITED_STATES)) {
					validationErrors.append("<strong>Error </strong>Countries other than US are not allowed! <br/> ");
				}
				
				if(!zipRegex.matcher(modelIn.getZipCode()).matches()) {
					validationErrors.append("<strong>Error </strong>Zip Code format invalid <br/> ");
				}
				
				if(modelIn.getState() != null && modelIn.getState().length() != 2) {
					validationErrors.append("<strong>Error </strong>State is invalid <br/> ");
				}	
				
				if(userRegistrationDAO.doesUserExist(modelIn)) {
					validationErrors.append("<strong>Error </strong>This user is already registered! <br/> ");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return validationErrors.toString();
	}
}
