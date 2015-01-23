package edu.cmu.cs.webapp.task7.formbean;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;


public class SellFundForm extends FormBean {
	private String action;
	private String fundId;
	private String shares;
	

	public void setShares(String s)				{ shares = s; }
	public String getAction ()			{ return action;}
	public String getShares ()			{return shares; }
	
	public void setAction(String s) 			 { action  = s;  }
	public void setFundId(String s)				{ fundId = s; }
	public String getFundId ()			{return fundId; }

	public List<String> getValidationErrors () {
		List<String> errors = new ArrayList<String>();

		if (shares == null || shares.length() == 0)
			errors.add("Shares to sell is required");
		if (action == null) errors.add("Button is required");

		if (errors.size() > 0) 	return errors;
        if (!action.equals("sell")) errors.add("Invalid button");
        
    	try {
        	double d = Double.parseDouble(shares);
        	if (d <= 0 || d > Integer.MAX_VALUE) {
        		throw new Exception();
        	}
        } catch (Exception e) {
        	 errors.add("Shares should be a positive number");
        }
    
		return errors;
	}


}
