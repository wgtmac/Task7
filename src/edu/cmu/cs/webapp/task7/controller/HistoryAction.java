package edu.cmu.cs.webapp.task7.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.task7.databean.CustomerBean;
import edu.cmu.cs.webapp.task7.databean.EmployeeBean;
import edu.cmu.cs.webapp.task7.databean.FundBean;
import edu.cmu.cs.webapp.task7.databean.FundPriceHistoryBean;
import edu.cmu.cs.webapp.task7.databean.TransactionBean;
import edu.cmu.cs.webapp.task7.formbean.DepositCheckForm;
import edu.cmu.cs.webapp.task7.formbean.TransitionDayForm;
import edu.cmu.cs.webapp.task7.model.CustomerDAO;
import edu.cmu.cs.webapp.task7.model.EmployeeDAO;
import edu.cmu.cs.webapp.task7.model.FundDAO;
import edu.cmu.cs.webapp.task7.model.FundPriceHistoryDAO;
import edu.cmu.cs.webapp.task7.model.Model;
import edu.cmu.cs.webapp.task7.model.PositionDAO;
import edu.cmu.cs.webapp.task7.model.TransactionDAO;

public class HistoryAction extends Action {

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public HistoryAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "history.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// If user is already logged in, redirect to todolist.do
			if (session.getAttribute("user") != null
					&& session.getAttribute("user") instanceof CustomerBean) {

				CustomerBean customer = (CustomerBean) session
						.getAttribute("user");

				TransactionBean tb = new TransactionBean();
				tb.setUserName(((CustomerBean) session.getAttribute("user"))
						.getUserName());

				int type = tb.getTransactionType();
				String operation;
				
				if (type == 8) {
					operation = "D | Buy Fund";
				} else if (type == 4) {
					operation = "C | Sell Fund";
				} else if (type == 2) {
					operation = "D | Request Check";
				} else if (type == 1) {
					operation = "C | Deposit Check";
				} else
					operation = "Invalid Operation";

				String date = tb.getExecuteDate();
				
			
				tb.getAmount();
				tb.getTransactionType();

				return "history.jsp";

			} else {
				// logout and re-login
				if (session.getAttribute("user") != null)
					session.removeAttribute("user");
				return "login.do";
			}
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}