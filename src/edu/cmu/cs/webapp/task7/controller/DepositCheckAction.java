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
import edu.cmu.cs.webapp.task7.formbean.DepositCheckForm;
import edu.cmu.cs.webapp.task7.model.CustomerDAO;
import edu.cmu.cs.webapp.task7.model.Model;
import edu.cmu.cs.webapp.task7.model.TransactionDAO;

public class DepositCheckAction extends Action {
	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(DepositCheckForm.class);

	private CustomerDAO customerDAO;
	private TransactionDAO transcationDAO;

	public DepositCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transcationDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "depositCheck.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// If user is already logged in, redirect to todolist.do
			if (session.getAttribute("user") != null && session.getAttribute("user") instanceof EmployeeBean) {
				DepositCheckForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);
				
				// read all customers into list
				request.setAttribute("customerList", customerDAO.getAllUserName());
				
				// If no params were passed, return with no errors so that the
				// form
				// will be presented (we assume for the first time).
				if (!form.isPresent()) {
					return "depositCheck.jsp";
				}

				// Any validation errors?
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return "depositCheck.jsp";
				}

				CustomerBean customer;
				if (customerDAO.read(form.getUserName()) == null) {
					errors.add("Customer does not exist");
					return "depositCheck.jsp";
				}

				TransactionBean tb = new TransactionBean();
				tb.setUserName(form.getUserName());
				//tb.setFundId(0);
				tb.setExecuteDate(null);
				//tb.setShares(0);
				tb.setTransactionType(TransactionBean.DPT_CHECK);
				tb.setAmount((long) (Double.parseDouble(form.getAmount()) * 100));

				transcationDAO.createAutoIncrement(tb);
				
				request.setAttribute("msg", "Customer check is deposited successfully!");

				return "depositCheck.jsp";
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
