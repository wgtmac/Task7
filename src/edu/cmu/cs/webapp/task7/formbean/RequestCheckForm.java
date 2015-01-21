package edu.cmu.cs.webapp.task7.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	private String cash;
	private String amount;
	private String confAmount;
	private String action;

	public void setCash(String s) {
		cash = s.trim();
	}
	
	public void setAmount(String s) {
		amount = s.trim();
	}

	public void setConfAmount(String s) {
		confAmount = s.trim();
	}

	public void setAction(String s) {
		action = s;
	}

	public String getCash() {
		return amount;
	}
	
	public String getAmount() {
		return amount;
	}

	public String getConfAmount() {
		return confAmount;
	}

	public String getAction() {
		return action;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (amount == null || amount.length() == 0)
			errors.add("Amount is required");
		if (confAmount == null || confAmount.length() == 0)
			errors.add("Confirm Amount is required");
		if (action == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		if (!action.equals("request"))
			errors.add("Invalid button");
		if (errors.size() > 0)
			return errors;

		
		try {
			double amt = Double.parseDouble(amount);
			if (amt <= 0) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			errors.add("The amount entered is not valid.");
		}
		if (errors.size() > 0)
			return errors;
		
		if (Double.parseDouble(amount) != Double.parseDouble(confAmount))
		//if (!amount.equals(confAmount))
			errors.add("Amounts must be identical");

				
		return errors;
		
	}
}