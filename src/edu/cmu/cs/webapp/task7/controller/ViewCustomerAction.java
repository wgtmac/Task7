
package edu.cmu.cs.webapp.task7.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.task7.databean.CustomerBean;
import edu.cmu.cs.webapp.task7.databean.EmployeeBean;
import edu.cmu.cs.webapp.task7.databean.TransactionBean;
import edu.cmu.cs.webapp.task7.formbean.CreateFundForm;
import edu.cmu.cs.webapp.task7.formbean.DepositCheckForm;
import edu.cmu.cs.webapp.task7.formbean.ViewCustomerForm;
import edu.cmu.cs.webapp.task7.model.CustomerDAO;
import edu.cmu.cs.webapp.task7.model.EmployeeDAO;
import edu.cmu.cs.webapp.task7.model.Model;


public class ViewCustomerAction extends Action {
	
	private FormBeanFactory<ViewCustomerForm> formBeanFactory = FormBeanFactory
			.getInstance(ViewCustomerForm.class);
	

	private CustomerDAO customerDAO;

	public ViewCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "viewCustomer.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// If user is already logged in, redirect to todolist.do
			if (session.getAttribute("user") != null && session.getAttribute("user") instanceof EmployeeBean) {
				ViewCustomerForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);
				
				// read all customers into list
				request.setAttribute("customerList", customerDAO.getAllUserName());
				
				// If no params were passed, return with no errors so that the
				// form
				// will be presented (we assume for the first time).
				if (!form.isPresent()) {
					return "viewCustomer.jsp";
				}

				// Any validation errors?
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return "viewCustomer.jsp";
				}

				if (customerDAO.read(form.getUserName1()) == null) {
					errors.add("Customer does not exist");
					return "viewCustomer.jsp";
				}
				
				CustomerBean customer=customerDAO.read(form.getUserName1());

				request.setAttribute("customerName",customer.getFirstName()+" "+customer.getLastName() );
				request.setAttribute("userName",customer.getUserName());
				request.setAttribute("address1",customer.getAddress1());
				request.setAttribute("address2",customer.getAddress2());
				request.setAttribute("state",customer.getState());
				request.setAttribute("city",customer.getCity());
				request.setAttribute("avai_cash",customer.getCash());
				
				
				return "viewCustomerAccount.jsp";
			} else {
				// logout and re-login
				if (session.getAttribute("user") != null)
					session.removeAttribute("user");

				return "login.do";
			}
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}

	}
}