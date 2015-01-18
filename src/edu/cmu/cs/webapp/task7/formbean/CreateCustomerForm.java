package edu.cmu.cs.webapp.task7.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCustomerForm extends FormBean {
	private String 	firstName;
	private String 	lastName;
	private String	userName;
	private String 	password;
	private String 	confirmPassword;
	private String 	address1;
	private String 	address2;
	private String 	city;
	private String 	state;
	private long		cash;
	private String 	action;

	public void setUserName(String v) 	{ userName = v; }
	public void setFirstName(String v)	{ firstName = v.trim();	}
	public void setLastName	(String v)	{ lastName = v.trim(); 	}
	public void setPassword	(String v) 	{ password = v.trim(); 	}
	public void setConfirmPassword	(String v) 	{ confirmPassword = v.trim(); 	}
	public void setAddress1	(String v) 	{ address1 = v.trim(); 	}
	public void setAddress2	(String v) 	{ address2 = v.trim(); 	}
	public void setCity	(String v) 			{ city = v.trim(); 			}
	public void setState	(String v) 			{ state = v.trim(); 			}
	public void setCash (long v)				{ cash = v;			}
	public void setAction(String s) 			 { action  = s;  }
	
	
	public String 	getUserName	() { return userName; 	}
	public String 	getFirstName		() { return firstName; 	}
	public String 	getLastName		() { return lastName; 	}
	public String 	getPassword		() { return password; 	}
	public String 	getConfirmPassword		() { return confirmPassword; 	}
	public String 	getAddress1		() { return address1; 	}
	public String 	getAddress2		() { return address2; 	}
	public String 	getCity				() { return city; 				}
	public String 	getState				() { return state; 			}
	public long	getCash				() { return cash;			}
	public String getAction		   ()	{ return action;}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0)
			errors.add("User Name is required");
		if (firstName == null || firstName.length() == 0)
			errors.add("First Name is required");
		if (lastName == null || lastName.length() == 0)
			errors.add("Last Name is required");
		if (password == null || password.length() == 0)
			errors.add("Password is required");
		if (confirmPassword == null || confirmPassword.length() == 0)
			errors.add("Confirm password is required");
		if (!password.equals(confirmPassword)) 
			errors.add("Passwords are not the same");
		if (action == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		if (userName.matches(".*[<>\"].*"))
			errors.add("User Name may not contain angle brackets or quotes");
		if (firstName.matches(".*[<>\"].*"))
			errors.add("First Name may not contain angle brackets or quotes");
		if (lastName.matches(".*[<>\"].*"))
			errors.add("Last Name may not contain angle brackets or quotes");
		if (password.matches(".*[<>\"].*"))
			errors.add("Password may not contain angle brackets or quotes");
		if ((address1 != null && address1.matches(".*[<>\"].*")) || (address2 != null && address2.matches(".*[<>\"].*"))  )
			errors.add("Address may not contain angle brackets or quotes");
		if (city != null && city.matches(".*[<>\"].*"))
			errors.add("City may not contain angle brackets or quotes");
		if (state != null && state.matches(".*[<>\"].*"))
			errors.add("State may not contain angle brackets or quotes");
		
		if (!action.equals("create"))
			errors.add("Invalid button");
		
		return errors;
	}
	private String sanitize(String s) {
    	return s.replace("&", "&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;");
	}
}
