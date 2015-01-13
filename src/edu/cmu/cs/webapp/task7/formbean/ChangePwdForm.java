package edu.cmu.cs.webapp.task7.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ChangePwdForm extends FormBean {
	private String newPassword;
	private String confirmPassword;
	private String action;

	public void setNewPassword(String s) {
		newPassword = s.trim();
	}

	public void setConfirmPassword(String s) {
		confirmPassword = s.trim();
	}

	public void setAction(String s) {
		action = s;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getAction() {
		return action;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (newPassword == null || newPassword.length() == 0)
			errors.add("New password is required");
		if (confirmPassword == null || confirmPassword.length() == 0)
			errors.add("Confirm password is required");
		if (action == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		if (!action.equals("Change Password"))
			errors.add("Invalid button");
		if (!newPassword.equals(confirmPassword))
			errors.add("Password mismatches!");
		if (newPassword.matches(".*[<>\"].*"))
			errors.add("Password may not contain angle brackets or quotes");

		return errors;
	}

}