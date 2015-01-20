package edu.cmu.cs.webapp.task7.controller;

import java.util.ArrayList;
import java.util.List;
import java.text.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.task7.databean.CustomerBean;
import edu.cmu.cs.webapp.task7.databean.EmployeeBean;
import edu.cmu.cs.webapp.task7.databean.TransactionBean;
import edu.cmu.cs.webapp.task7.formbean.DepositCheckForm;
import edu.cmu.cs.webapp.task7.formbean.RequestCheckForm;
import edu.cmu.cs.webapp.task7.model.CustomerDAO;
import edu.cmu.cs.webapp.task7.model.Model;
import edu.cmu.cs.webapp.task7.model.TransactionDAO;

public class RequestCheckAction extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public RequestCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "requestCheck.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// If user is already logged in, redirect to todolist.do
			if (session.getAttribute("user") != null && session.getAttribute("user") instanceof CustomerBean) {
				RequestCheckForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);
					
				// If no params were passed, return with no errors so that the
				// form will be presented (we assume for the first time).
				if (!form.isPresent()) {
					return "requestCheck.jsp";
				}

				// Any validation errors?
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return "requestCheck.jsp";
				}
				// Check if cash available is enough to cover check request customer.getCash();
				if (Double.parseDouble(form.getCash()) > 500) {
					errors.add("Amount requested is higher than cash available");
					return "requestCheck.jsp";
				}

				TransactionBean tb = new TransactionBean();
				//tb.setFundId(0);
				tb.setExecuteDate(null);
				//tb.setAmount(0);
				tb.setTransactionType(TransactionBean.REQ_CHECK);
				tb.setAmount((long) (Double.parseDouble(form.getAmount()) * -100));

				transactionDAO.createAutoIncrement(tb);
				
				//Set new cash amount
				
				NumberFormat formatter = new DecimalFormat("#0.00");     			
				request.setAttribute("msg", "A check in the amount of $"+ formatter.format(Double.parseDouble(form.getAmount()))+ " has been requested.");

				return "requestCheck.jsp";
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