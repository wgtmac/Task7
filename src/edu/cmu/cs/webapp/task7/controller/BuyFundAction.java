package edu.cmu.cs.webapp.task7.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

//import model.PhotoDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.task7.databean.CustomerBean;
import edu.cmu.cs.webapp.task7.databean.EmployeeBean;
import edu.cmu.cs.webapp.task7.databean.FundBean;
import edu.cmu.cs.webapp.task7.databean.TransactionBean;
import edu.cmu.cs.webapp.task7.databean.PositionBean;
import edu.cmu.cs.webapp.task7.model.*;
import edu.cmu.cs.webapp.task7.formbean.*;

public class BuyFundAction extends Action {
	private FormBeanFactory<BuyForm> formBeanFactory = FormBeanFactory
			.getInstance(BuyForm.class);

	private FundDAO fundDAO;
	private CustomerDAO customerDAO;
	private PositionDAO posDAO;
	private TransactionDAO transactionDAO;

	public BuyFundAction(Model model) {
		fundDAO = model.getFundDAO();
		customerDAO = model.getCustomerDAO();
		posDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "buyFund.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			if (request.getSession().getAttribute("user") == null || 
					request.getSession().getAttribute("user") instanceof EmployeeBean) {
				errors.add("Please log in as a customer");
				return "login.jsp";
			}

			// Set up user list for nav barS
			request.setAttribute("customerList", customerDAO.getUsers());

			CustomerBean user = (CustomerBean) request.getSession(false)
					.getAttribute("user");

			FundBean[] fundList = fundDAO.getAllFunds();
			request.setAttribute("fundList", fundList);

			BuyForm form = formBeanFactory.create(request);

			if (!form.isPresent()) {

				return "buyFund.jsp";
			}
			errors.addAll(form.getValidationErrors());
			request.setAttribute("errors", errors);

			// System.out.println(errors);

			if (errors.size() > 0)
				return "buyFund.jsp";

			String fund = form.getFund1();
			

			//long amount = Long.parseLong(form.getAmount());
			double amount = Double.parseDouble(form.getAmount());
			
			int id = fundDAO.getFundIdByName(fund);

			double availableBalance = transactionDAO.getValidBalance(
					user.getUserName(), user.getCash() / 100.0);
			DecimalFormat df2 = new DecimalFormat("#,##0.00");
			String availableBalanceString = df2.format(availableBalance)
					.toString();
			// System.out.println("balance string is"+availableBalanceString);

			if ((availableBalance - amount) < 0.0) {
				errors.add("You do not have enough cash balance in your account. You have only $"
						+ availableBalanceString + " left");
				request.setAttribute("errors", errors);
				return "buyFund.jsp";
			} else {
				// write a method in posDAO to increase the number of shares.
				// posDAO.increaseShares(id, shares,user.getUserName());
			}
			amount = Math.round(amount * 100);

			// ALSO ADD TO THE TRANSACTIONS TABLE
			TransactionBean transbean = new TransactionBean();
			transbean.setUserName(user.getUserName());
			transbean.setFundId(id);
			// transbean.setShares(shares);
			transbean.setTransactionType(transbean.BUY_FUND);
			transbean.setAmount((long) (amount));
			transbean.setExecuteDate(null);
			transactionDAO.create(transbean);

			// Update favoriteList (there's now one more on the list)
			// TransactionBean[] newTransactionList =
			// transactionDAO.getTransactions(user.getUserName());
			request.setAttribute("msg", "$"+form.getAmount()+ " of fund purchased successfully.");
			return "buyFund.jsp";
		} catch (RollbackException e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}
