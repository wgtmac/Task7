package edu.cmu.cs.webapp.task7.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewCustomerForm extends FormBean {
	private String userName1;
	private String action;
	
	public void setUserName1(String s)	 	{ userName1 = s.trim(); }
	public void setAction(String s)			 {	action = s;	}
	
	public String getUserName1()		{	return userName1;	}
	public String getAction()		 			{	return action;	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if ((userName1 == null || userName1.length() == 0))
			errors.add("Username is required");
		
		if (action == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;
		
        if (!action.equals("select")) 
        	errors.add("Invalid button");
        if (userName1.matches(".*[<>\"].*") ) 
        	errors.add("User Name may not contain angle brackets or quotes");
		
		return errors;
	}

}
