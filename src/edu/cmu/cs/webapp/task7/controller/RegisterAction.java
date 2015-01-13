package edu.cmu.cs.webapp.task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.task7.databean.UserBean;
import edu.cmu.cs.webapp.task7.formbean.RegisterForm;
import edu.cmu.cs.webapp.task7.model.Model;
import edu.cmu.cs.webapp.task7.model.UserDAO;

public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);

	private UserDAO userDAO;

	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() {
		return "register.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		// If user is already logged in, redirect to todolist.do
		if (session.getAttribute("user") != null) {
			return "favorite.do";
		}

		try {
			RegisterForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "register.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "register.jsp";
			}

			if (userDAO.read(form.getEmail()) != null) {
				errors.add("A user with this name already exists");
				return "register.jsp";
			}

			UserBean newUser = new UserBean();
			newUser.setEmail(form.getEmail());
			newUser.setLastName(form.getLastName());
			newUser.setFirstName(form.getFirstName());
			newUser.setPassword(form.getPassword());

			userDAO.create(newUser);
			newUser = userDAO.read(form.getEmail());
			session.setAttribute("user", newUser);

			return ("favorite.do");
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}
}
