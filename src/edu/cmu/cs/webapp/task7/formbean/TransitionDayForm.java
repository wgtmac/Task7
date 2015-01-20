
package edu.cmu.cs.webapp.task7.formbean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

//import org.mybeans.form.FormBean;

public class TransitionDayForm /* extends FormBean*/ {
	private String date;
	private String action;

	public TransitionDayForm(HttpServletRequest request) {
		action = request.getParameter("action");
		date	= request.getParameter("date");
	}

	public String getDate ()					{	return date;		}
	public String getAction()		 			{	return action;	}
	
	public boolean isPresent() {	return action != null; }
	
	// HashMap<String, Long> map: 
	// String:	    name in the jsp, related to fundid
	// String:		price
	public List<String> getValidationErrors (HashMap<String, String> map) {
		List<String> errors = new ArrayList<String>();

		if (date == null || date.length() == 0 || date.equals("mm/dd/yyyy"))
			errors.add("Date is required");
		if (action == null) errors.add("Button is required");

		if (errors.size() > 0) 	return errors;
        if (!action.equals("create")) errors.add("Invalid button");
        
        try {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	dateFormat.setLenient(false);
        	dateFormat.parse(date);
        } catch (Exception e) {
        	errors.add("Invalid date input");
        }
        
        for (String price : map.values()) {
        	try {
        		if (price == null || price.length() == 0)
        			throw new Exception();
            	double d = Double.parseDouble(price);
            	if (d <= 0 || d > Integer.MAX_VALUE) {
            		throw new Exception();
            	}
            } catch (Exception e) {
            	 errors.add("Price should be a positive number");
            	 break;
            }
        }
    
		return errors;
	}
}