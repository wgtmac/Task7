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
	//private String camount;
	private String amount;
	private String fund;
	private String fund2;
	
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
	public String getFund1() {
		if (fund != null && fund.length() > 0) return fund;
		else return fund2;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if(amount == null || amount.length() == 0) {
			errors.add("Please enter an amount.");
		}
		/*if(amount!=camount){
			errors.add("Amounts do not match");
		}*/
		if((fund == null || fund.length() == 0) && (fund2==null||fund2.length()==0)) {
			errors.add("Please choose a fund");
		}
		if (amount != null && amount.matches(".*[<>\"].*") ) 
			errors.add("Buy amount format error!");
		if(!checkDecimal(amount)){
			errors.add("Only numbers with a maximum of 2 decimals places are allowed for amount.");
		}
		
        if (errors.size() > 0) return errors;
		try { 
			System.out.println("amount is"+amount);
			long amt = Long.parseLong(amount);
			amt = Math.round(amt*100);
			amt=amt/100;
			System.out.println("amt is"+amt);
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

	/*public String getCamount() {
		return camount;
	}

	public void setCamount(String camount) {
		this.camount = camount;
	}*/

	public String getFund2() {
		return fund2;
	}

	public void setFund2(String fund2) {
		this.fund2 = fund2;
	}
}
