package edu.cmu.cs.webapp.task7.formbean;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import edu.cmu.cs.webapp.task7.databean.*;
import edu.cmu.cs.webapp.task7.model.*;



public class BuyForm extends FormBean {
	

	private String fund;
	private int shares;
	private PositionBean pb;
	
	public String getFund() { return fund; }
	public int getShares()     { return shares;     }
	
	public void setFund(String s) { fund = s.trim(); }
	public void setShares(int s)     { shares     = s; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fund == null || shares == 0) {
			errors.add("Select a fund and Enter the number of Shares");
		}
		
		//get the number of shares for a particular funds and see if the customer has enough to sell
		/*if(shares<pb.getShares()){
			errors.add("You do not have enough shares");
		}*/
		
		
		if (errors.size() > 0) {
			return errors;
		}
		
		

		return errors;
	}


}
