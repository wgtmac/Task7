package edu.cmu.cs.webapp.task7.formbean;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import edu.cmu.cs.webapp.task7.databean.*;
import edu.cmu.cs.webapp.task7.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class BuyForm extends FormBean{
	private String amount;
	private String fund;
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if(amount == null || amount.length() == 0) {
			errors.add("Please enter an amount.");
		}
		if(fund == null || fund.length() == 0) {
			errors.add("Please choose a fund");
		}
		if (amount != null && amount.matches(".*[<>\"].*") ) 
			errors.add("Buy amount format error!");
		if(!checkDecimal(amount)){
			errors.add("Only numbers with a maximum of 2 decimals places are allowed for amount.");
		}
        if (errors.size() > 0) return errors;
		try { 
			double amt = Double.parseDouble(amount);
			amt = Math.round(amt*100);
			amt=amt/100;
			if(amt< 10) {
				errors.add("Please enter an amount that is greater than $10");
			} else if(amt>1000000000) {
				errors.add("Please enter an amount that is lesser than $1000000000");
			}
		} catch(NumberFormatException nfe) {
			errors.add("Please enter amount in digits. Do not use letters");
		}
		
        return errors;
    }
	public boolean checkDecimal(String input) {
		Pattern p = Pattern.compile("[+-]?[0-9]+.{0,1}[0-9]{0,2}");
		return p.matcher(input).matches();
	}
}
