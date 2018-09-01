package com.test.controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import com.test.dao.UserRegistrationDAO;
import com.test.model.UserRegistrationDataModel;
import com.test.validator.UserRegistrationValidator;

import org.json.*;
/**
 * Controller class for User Registration
 */
public class UserRegistrationController {
	private ServletContext context = null;
	
	private final static String FIRST_NAME_ATTR = "firstName";
	private final static String LAST_NAME_ATTR = "lastName";
	private final static String ADDRESS_1_ATTR = "address1";
	private final static String ADDRESS_2_ATTR = "address2";
	private final static String CITY_ATTR = "city";
	private final static String STATE_ATTR = "state";
	private final static String ZIP_CODE_ATTR = "zipCode";
	private final static String COUNTRY_ATTR = "country";
	
	private final static String MESSAGE_ATTR = "message";
	private final static String STATUS_ATTR = "status";
	private final static String ERRORS_ATTR = "errors";
	private final static String REDIRECT_ATTR = "redirectPage";
	
	private final static String REGISTRATION_FORM_JSP = "/UserRegistrationForm.jsp";
	private final static String REGISTRATION_SUCCESS_JSP = "/UserRegistrationSuccess.jsp";
	
	
	/**
	 * Constructor
	 * @param contextIn
	 */
	public UserRegistrationController(ServletContext contextIn) {
		context = contextIn;
	}
	public UserRegistrationController() throws Exception {

	}
	/**
	 * Registers a new user
	 * @return
	 * @throws Exception 
	 */
	public JSONObject registerNewUser() throws Exception {
		try {
				String validationErrors = "";
				
				//Get attributes from context
				String firstName = (String)context.getAttribute(FIRST_NAME_ATTR);
				String lastName = (String)context.getAttribute(LAST_NAME_ATTR);
				String address1 = (String)context.getAttribute(ADDRESS_1_ATTR);
				String address2 = (String)context.getAttribute(ADDRESS_2_ATTR);
				String city = (String)context.getAttribute(CITY_ATTR);
				String state = (String)context.getAttribute(STATE_ATTR);
				String zipCode = (String)context.getAttribute(ZIP_CODE_ATTR);
				String country = (String)context.getAttribute(COUNTRY_ATTR);
				
				JSONObject jsonObject = createInitialJSONObject(firstName, lastName, address1, address2, city, state, zipCode, country);
				
				UserRegistrationDataModel registrationModel = new UserRegistrationDataModel(firstName, lastName, address1, address2, city, state, zipCode, country);
				UserRegistrationValidator validator = new UserRegistrationValidator();
				  
				validationErrors = validator.validate(registrationModel);
					
				if(validationErrors== null || validationErrors.length() == 0) {
						
					UserRegistrationDAO userRegistrationDAO = new UserRegistrationDAO();
					userRegistrationDAO.insertRecord(registrationModel);
					
					jsonObject.put(MESSAGE_ATTR, "SUCCESS"); 
					jsonObject.put(STATUS_ATTR, "OK");
					 
					removeAttributesFromContext();
					context.setAttribute(REDIRECT_ATTR, REGISTRATION_SUCCESS_JSP);
					 
				} else {
					//validation errors occurred. 
					 jsonObject.put(MESSAGE_ATTR, "FAILED"); 
					 jsonObject.put(STATUS_ATTR, "FAILED");
					 jsonObject.put(ERRORS_ATTR, validationErrors);
					 context.setAttribute(REDIRECT_ATTR, REGISTRATION_FORM_JSP);
				}
				
				return jsonObject;
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unexpected Exception occurred in getRegistrationList: " + e.toString());
		}
	}
	/**
	 * Gets list of registered users
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<UserRegistrationDataModel> getRegistrationList() throws Exception {
		
		try {
			UserRegistrationDAO userRegistrationDAO = new UserRegistrationDAO();
			ArrayList<UserRegistrationDataModel> registrations = userRegistrationDAO.getRegistrationList();
		
			return registrations;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unexpected Exception occurred in getRegistrationList: "+ e.toString());
		}
	}
	/**
	 * Creates and populates JSON object
	 * @param firstName
	 * @param lastName
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param country
	 * @return
	 */
	private JSONObject createInitialJSONObject(String firstName, String lastName, String address1, String address2, 
			                                   String city, String state, String zipCode, String country) {
		JSONObject jsonObject=new JSONObject();  
			jsonObject.put("firstName",firstName);    
			jsonObject.put("lastName",lastName);    
			jsonObject.put("address1", address1);
			jsonObject.put("address2", address2);
			jsonObject.put("cuty", city);
			jsonObject.put("state", state);
			jsonObject.put("zipCode", zipCode);
			jsonObject.put("country", country);
		  
		  return jsonObject;
	}
	/**
	 * Removes attributes from the context
	 */
	private void removeAttributesFromContext() {
		try {
			if(context != null) {
				context.removeAttribute("firstName");
				context.removeAttribute("lastName");
				context.removeAttribute("address1");
				context.removeAttribute("address2");
				context.removeAttribute("city");
				context.removeAttribute("state");
				context.removeAttribute("zipCode");
				context.removeAttribute("country");
			}
		} catch (Exception e) {
			System.out.println("Unexpected Exception occurred in createInitialJSONObject");
			e.printStackTrace();
		}
	}


}
