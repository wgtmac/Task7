package edu.cmu.cs.webapp.task7.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean {
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String action;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getAction() {
		return action;
	}

	public void setEmail(String s) {
		email = s.trim();
	}

	public void setPassword(String s) {
		password = s.trim();
	}

	public void setFirstName(String s) {
		firstName = s.trim();
	}

	public void setLastName(String s) {
		lastName = s.trim();
	}

	public void setAction(String s) {
		action = s;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (email == null || email.length() == 0)
			errors.add("Email is required");
		if (firstName == null || firstName.length() == 0)
			errors.add("First Name is required");
		if (lastName == null || lastName.length() == 0)
			errors.add("Last Name is required");
		if (password == null || password.length() == 0)
			errors.add("Password is required");
		if (action == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		if (!email
				.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
			errors.add("Invalid email address");
		if (firstName.matches(".*[<>\"].*"))
			errors.add("First Name may not contain angle brackets or quotes");
		if (lastName.matches(".*[<>\"].*"))
			errors.add("Last Name may not contain angle brackets or quotes");
		if (password.matches(".*[<>\"].*"))
			errors.add("Password may not contain angle brackets or quotes");
		if (!action.equals("Register"))
			errors.add("Invalid button");

		return errors;
	}
}
