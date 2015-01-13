package edu.cmu.cs.webapp.task7.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class AddForm extends FormBean {
	private String url;
	private String comment;
	private String action;

	public String getUrl() {
		return url;
	}

	public String getComment() {
		return comment;
	}

	public String getAction() {
		return action;
	}

	public void setUrl(String s) {
		url = s.trim();
	}

	public void setComment(String s) {
		comment = s.trim();
	}

	public void setAction(String s) {
		action = s;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (url == null || url.length() == 0)
			errors.add("URL is required");
		if (comment == null || comment.length() == 0)
			errors.add("Comment is required");
		if (action == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		if (!action.equals("Add Favorite"))
			errors.add("Invalid button");
		if (!url.matches("((https?)://)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?"))
			errors.add("Invalid url address");

		return errors;
	}
}
