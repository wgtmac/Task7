package edu.cmu.cs.webapp.task7.controller;

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

public class TransitionDayAction extends Action {

	private 	CustomerDAO 		customerDAO;
	private 	FundDAO				fundDAO;
	private	TransactionDAO	transactionDAO;
	private 	FundPriceHistoryDAO	fundPriceHistoryDAO;

	public TransitionDayAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "transitionDay.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// If user is already logged in, redirect to todolist.do
			if (session.getAttribute("user") != null && session.getAttribute("user") instanceof EmployeeBean) {
				TransitionDayForm form = new TransitionDayForm(request);
				request.setAttribute("form", form);
				
				// read all customers into list
				FundBean[] fundList =  fundDAO.getAllFunds();
				request.setAttribute("fundList", fundList);
				
				// If no params were passed, return with no errors so that the
				// form will be presented (we assume for the first time).
				if (!form.isPresent()) {
					return "transitionDay.jsp";
				}
				
				// check date
	        	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        	dateFormat.setLenient(false);
	        	Date date = dateFormat.parse(form.getDate());
	        	Date lastDay = fundPriceHistoryDAO.getLatestTradingDay();
	        	if (lastDay != null && date.compareTo(lastDay) <= 0) {
	        		errors.add("The input date is not greater than the date of previously ended trading day");
	        	}
	        	
				if (errors.size() != 0) {
					return "transitionDay.jsp";
				}
				
				HashMap<String, String> map = new HashMap<String, String>();
				for (FundBean fb : fundList) {
					map.put("fund_" + fb.getFundId(), request.getParameter("fund_" + fb.getFundId()));
				}
				// Any validation errors?
				errors.addAll(form.getValidationErrors(map));

				if (errors.size() != 0) {
					return "transitionDay.jsp";
				}

				// update prices
				for (FundBean fb : fundList) {			
					FundPriceHistoryBean fphb = new FundPriceHistoryBean();
					fphb.setFundId(fb.getFundId());
					fphb.setPriceDate(form.getDate());
					fphb.setPrice( (long)(Double.parseDouble(request.getParameter("fund_" + fb.getFundId())) * 100) );
					
					fundPriceHistoryDAO.create(fphb);
				}
				
				// process pending transactions
				transactionDAO.executePendingTransactions();
				
				request.setAttribute("msg", "Transition day is set successfully!");

				return "transitionDay.jsp";
			} else {
				// logout and re-login
				if (session.getAttribute("user") != null)
					session.removeAttribute("user");

				return "login.do";
			}
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}

	}
}
