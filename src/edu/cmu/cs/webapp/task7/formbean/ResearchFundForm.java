package edu.cmu.cs.webapp.task7.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResearchFundForm extends FormBean {
	private String fundName;
	
	private String button; // can be Search or Research Fund
	private String fromTime; // will not implement in first version
	private String toTime; // will not implement in first version
	private String fund2;

	public String getFundName() {
		return fundName;
	}
	public String getFund1() {
		
		if (fundName != null && fundName.length() > 0)
			return fundName;
		else
			return fund2;
	}
	

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		// this is the first time entering this page
		if (button == null) {
			return errors;
		} else if (button.equals("Fund History")) {
			if (fundName == null || fundName.length() == 0 ) {
				errors.add("Fund has not been specified.");
				return errors;
			}
			
		} else {
			errors.add("This operation(" + button + ") is not allowed.");

		}
		return errors;
	}

	public String getButton() {
		
		return button;
	}

	public void setButton(String button) {
		this.button = button;
		
	}


	public String getFund2() {
		return fund2;
	}


	public void setFund2(String fund2) {
		this.fund2 = fund2;
	}
	
	
}

