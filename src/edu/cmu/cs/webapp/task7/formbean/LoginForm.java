/*
Gang Wu
gwu1
08-600
Nov 29, 2014
 */
package edu.cmu.cs.webapp.task7.formbean;/*


gwu1
08-600
Nov 7, 2014
 */

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String email;
	private String password;
	private String action;

	public void setEmail(String s) {
		email = s.trim();
	}

	public void setPassword(String s) {
		password = s.trim();
	}

	public void setAction(String s) {
		action = s;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getAction() {
		return action;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (email == null || email.length() == 0)
			errors.add("Email is required");
		if (password == null || password.length() == 0)
			errors.add("Password is required");
		if (action == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		if (!action.equals("Login"))
			errors.add("Invalid button");
		if (!email
				.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
			errors.add("Invalid email address");

		return errors;
	}

}