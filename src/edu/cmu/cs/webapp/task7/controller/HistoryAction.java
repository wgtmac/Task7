package edu.cmu.cs.webapp.task7.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;

import edu.cmu.cs.webapp.task7.databean.CustomerBean;
import edu.cmu.cs.webapp.task7.databean.HistoryBean;
import edu.cmu.cs.webapp.task7.databean.TransactionBean;
import edu.cmu.cs.webapp.task7.model.FundDAO;
import edu.cmu.cs.webapp.task7.model.Model;
import edu.cmu.cs.webapp.task7.model.TransactionDAO;

public class HistoryAction extends Action {

	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;

	public HistoryAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
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

				TransactionBean[] tb = transactionDAO.match(MatchArg.equals("userName", customer.getUserName()));

				// List of History Beans
				HistoryBean[] hb = null;

				if (tb != null && tb.length != 0) {

					hb = new HistoryBean[tb.length];

					for (int i = 0; i < tb.length; i++) {

						hb[i] = new HistoryBean();
						// Date from Transaction:

						String date = tb[i].getExecuteDate();
						if (date == null || date.length() == 0) {
							date = "Pending";
						}

						hb[i].setDate(date);

						// Operation
						int type = tb[i].getTransactionType();
						String operation;
						if (type == 8) {
							operation = "Buy Fund";
						} else if (type == 4) {
							operation = "Sell Fund";
						} else if (type == 2) {
							operation = "Request Check";
						} else if (type == 1) {
							operation = "Deposit Check";
						} else
							operation = "Invalid Operation";
						hb[i].setOperation(operation);

						// Operation Type
						String transaction;
						if (type == 8 || type == 2) {
							transaction = "Debit";
						} else if (type == 4 || type == 1) {
							transaction = "Credit";
						} else
							transaction = "Invalid Type";
						hb[i].setType(transaction);

						// Fund Name
						int fundId;
						String fund;
						if (tb[i].getFundId() == 0) {
							fund = "";
						} else {
							fundId = tb[i].getFundId();
							fund = fundDAO.getFundNameById(fundId);
						}
						hb[i].setFund(fund);

						// Total Amount
						double amount = tb[i].getAmount() / 100.0;
						String total;
						NumberFormat formatter = new DecimalFormat("#,##0.00");
						if (amount == 0) {
							total = "";
							hb[i].setTotal(total);
						}
						else if (transaction.equals("Credit")) {
							total = "$" + formatter.format(amount)+"&nbsp;";
							hb[i].setTotal(total);
						} else if (transaction.equals("Debit")) {
							total = "<font color=\"red\">($" + formatter.format(amount)+")";
							hb[i].setTotal(total);
						}

						

						// Get Shares and Calculate Share Price
						double shares = tb[i].getShares() / 1000.0;
						String totShares;
						NumberFormat formatShare = new DecimalFormat(
								"#,##0.000");
						if (shares == 0) {
							totShares = "";
						} else
							totShares = formatShare.format(shares);
						hb[i].setTotShares(totShares);

						String price;
						if (shares != 0) {
							double sharePrice = amount / shares;
							price = "$" + formatter.format(sharePrice);
						} else
							price = "";
						hb[i].setPrice(price);
					}
					request.setAttribute("transactionList", hb);
					return "history.jsp";

				} else {
					request.setAttribute("msg",
							"You have not made any transactions yet.");
					return "history.jsp";
				}

			} else {
				// logout and re-login
				if (session.getAttribute("user") != null)
					session.removeAttribute("user");
				return "login.do";
			}
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "history.jsp";
		}
	}
}