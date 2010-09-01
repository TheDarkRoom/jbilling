package com.veitch.tutorial.integration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import com.sapienter.jbilling.server.entity.CreditCardDTO;
import com.sapienter.jbilling.server.order.OrderLineWS;
import com.sapienter.jbilling.server.order.OrderWS;
import com.sapienter.jbilling.server.util.api.JbillingAPI;
import com.sapienter.jbilling.server.util.api.JbillingAPIException;
import com.sapienter.jbilling.server.util.api.JbillingAPIFactory;
import com.sapienter.jbilling.server.user.ContactDTOEx;
import com.sapienter.jbilling.server.user.ContactWS;
import com.sapienter.jbilling.server.user.UserWS;
import com.sapienter.jbilling.server.user.contact.db.ContactDTO;
import com.sapienter.jbilling.server.user.contact.db.ContactFieldDTO;

public class IntegrationTutorial {

	
	public void getUserStatusFromUsername(String username){
		
		/*
		* We assume the String variable “username” contains the username
		* string as entered by the user in the login form.
		*/
		Integer userId = null;
		// Create and initialize the jBilling API.
		JbillingAPI api = null;
		try {
			api = JbillingAPIFactory.getAPI();
		} catch (JbillingAPIException e1) {
			System.out.println("JbillingAPIException - Getting API. Exception:"+e1.getMessage());
		} catch (IOException e1) {
			System.out.println("IOException - Getting API. Exception:"+e1.getMessage());
			e1.printStackTrace();
		}
		boolean canLogin = false;
		
		try {
			userId = api.getUserId(username);
			if (userId == null) {
				/*
				* This shouldn't happen as the API should issue an exception if the
				* user does not exist, but test it anyway. In this case, we simply
				* generate an exception that is catched later in this block
				* of code.
				*/
			}
			
			// With the user id just retrieved, we can query the user's data.
			UserWS userData = api.getUserWS(userId);
			
			/*
			 * The user data contains many information about the user, but in
			 * this case we're mostly interested in the statusId field of the
			 * UserWS class.
			 * This field values are: 1=active, 2=Overdue 1, 3=Overdue 2,
			 * 4=Overdue 3, 5=Suspended 1, 6=Suspended 2, 7=Suspended 3,
			 * 8=Deleted. Status Ids from 1 through 4 indicate the user is able
			 * to login, all other codes cannot login.
			 */
			int statusId = userData.getStatusId().intValue();
			
			if (statusId > 0 && statusId <= 4) {
				/*
				 * The user can login to the system, as his current status is ok.
				 * Just print a notice in case he's late with his last payment.
				 */
				canLogin = true;
				System.out.println("User can log in");
				if (statusId != 0) {
					System.out.println("The user's payment is overdue!");
				}
			}
		} 
		catch (Exception e) {
			/*
			 * The user does not exist in Jbilling's records. The login request
			 * should be denied, and perhaps an error message issued back to the
			 * caller. Here, we just print an error message to stdout.
			 */
			canLogin = false;
			System.out.println("Invalid username: the user does not exist!");
		}
		
		if (canLogin) {
		// Here you can grant access to the reserved area.
			System.out.println("can login:");
		} 
		else {
		// Here you can deny entrance to the reserved area.
			System.out.println("deny login:");			
		}		
	}
	
	public void authenticate(String username, String password){
	
		// Create and initialize the jBilling API.
		JbillingAPI api;
		Integer result = null;
		try {
			api = JbillingAPIFactory.getAPI();
			result = api.authenticate(username, password);	
			System.out.println("authenticate result:"+result);				
		} catch (JbillingAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result.intValue() == 0) {
			// 	The user is able to loging.
		} else {
			// The user cannot login. The return value indicates why.
		}
	}

	
	public void getCustomerContacts(int userId){
		
		// Create and initialize the jBilling API.
		JbillingAPI api = null;
		try {
			api = JbillingAPIFactory.getAPI();
		} catch (JbillingAPIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			ContactWS[] userContacts = api.getUserContactsWS(userId);
			String referenceNumber = null;
			for (ContactWS currentContactWS : userContacts){
				String firstName = currentContactWS.getFirstName();
				String lastName = currentContactWS.getLastName();
				String organizationName = currentContactWS.getOrganizationName();				
				String[] fieldNames = currentContactWS.getFieldNames();				
				String[] fieldValues = currentContactWS.getFieldValues();
				//HERE WORKS AS NAME IS 1
				

				System.out.println("Contact "+firstName+" "+lastName+" "+organizationName+" fieldNames size:"+fieldNames.length+" fieldValues size:"+fieldValues.length);
				
				for (int i=0; i<fieldNames.length; i++){
					String currentFieldName = fieldNames[i];
					String currentFieldValue = fieldValues[i];
					System.out.println("userFields["+i+"] currentFieldName:"+currentFieldName+" currentFieldValue:"+currentFieldValue);					
				}
				
			}
		}
		catch (Exception e) {
			/*
			* There was an error during the user or order creation. Just print an
			* error message.
			*/
			System.out.println("Error in user or order creation Exception:"+e.getMessage());
		}		
	}

	public void trialUser(String username, String password){
		/*
		* We assume the String variables “username” and “password” contain the
		* new user's login data as entered by the user in the trial registration form.
		*/
		Integer userId = null;
		
		// Create and initialize the jBilling API.
		JbillingAPI api = null;
		try {
			api = JbillingAPIFactory.getAPI();
		} catch (JbillingAPIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			// Create the user's record.
			UserWS newUser = new UserWS();
			
			
			// Fill in the new user's data.
			newUser.setMainRoleId(new Integer(5)); // Role “5” = “Customer”.
			newUser.setStatusId(new Integer(1)); // Status “1” = “Active”.
			newUser.setUserName(username);
			newUser.setPassword(password);
			// 	Refer to Appendix A for language codes.
			newUser.setLanguageId(new Integer(1));
			// Refer to Appendix A for currency codes.
			newUser.setCurrencyId(new Integer(1));
			newUser.setCreateDatetime(new java.util.Date()); // = now
			
			
			
			// 	If you're entering credit card information, you'll need to create
			// a CreditCardDTO object and fill it with data, and assign the data
			// to this user via the setCreditCard() method of UserWS. Same goes for
			// Contact info, a ContactDTO object can be assigned via setContact().
			
			//CreditCard
			CreditCardDTO creditCard = new CreditCardDTO();
			creditCard.setName("MR T VEITCH");
			creditCard.setNumber("4408041234567893");
	        DateFormat df = new SimpleDateFormat("MM/yyyy");
	        Date expiryDate = (Date)df.parse("12/2015");            			
			creditCard.setExpiry(expiryDate);
			
			newUser.setCreditCard(creditCard);
			
			//Contact
			ContactWS contact = new ContactWS();
			contact.setCity("Big City");
			// Put some data into it
			contact.setPostalCode("12345");
			contact.setFaxNumber("555-123456");
			contact.setEmail("foo@bar.com");			
			
			
			// Now we create two string arrays for the Ids and values.
			String customContactIds[] = new String[1];
			String customContactValues[] = new String[1];
			// Put the values into the arrays.
			// this holds the unique ID for the field.
			customContactIds[0] = "Contact website";
			// this holds the actual value.
			customContactValues[0] = "www.fifa.com";
			// Set the custom fields in the ContactWS structure.
			// We assume contactData is an already created and filled ContactWS
			//structure.
			contact.setFieldNames(customContactIds);
			contact.setFieldValues(customContactValues);	
			
			newUser.setContact(contact);	
			
			// Call the “create” method on the api to create the user.
			Integer newUserId = api.createUser(newUser);
			
			// Now let's create a new order line.
			OrderLineWS line = new OrderLineWS();
			line.setPrice(new BigDecimal(10)); // Order price = 10.
			line.setTypeId(new Integer(1)); // Type 1 = “Item”.
			line.setQuantity(new Integer(1)); // Quantity = 1
			line.setAmount(new BigDecimal(10)); // Total amount = 10
			line.setDescription("Banner on Front Page");
			line.setItemId(new Integer(1));
			
			// Now create an order that contains the order line just created.
			OrderWS newOrder = new OrderWS();
			newOrder.setUserId(newUserId);
			newOrder.setPeriod(new Integer(1));
			// Now add the order line created above to this order.
			newOrder.setOrderLines(new OrderLineWS[] { line });
			newOrder.setBillingTypeId(new Integer(1)); // Prepaid order.
			GregorianCalendar activeSinceDate = new GregorianCalendar();
			activeSinceDate.add(Calendar.DATE, 15);
			
			//Set currencyId
			newOrder.setCurrencyId(1);			
			newOrder.setActiveSince(activeSinceDate.getTime());
			
			// 	Now create the order.
			Integer newOrderId = api.createOrder(newOrder);
			} 
		catch (Exception e) {
			/*
			* There was an error during the user or order creation. Just print an
			* error message.
			*/
			System.out.println("Error in user or order creation Exception:"+e.getMessage());
		}		
	}	
	
	
	public static void main (String[] args){
		IntegrationTutorial tutorial = new IntegrationTutorial();
		
		//tutorial.getUserStatusFromUsername("admin");		
		//tutorial.authenticate("admin", "password");
		//tutorial.trialUser("newusername", "newpassword");
		//tutorial.getMainOrder(); //HERE
		tutorial.getCustomerContacts(60);
	}
}
