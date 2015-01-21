package edu.cmu.cs.webapp.task7.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import org.mybeans.form.FormBeanException;

import edu.cmu.cs.webapp.task7.databean.*;
import edu.cmu.cs.webapp.task7.model.*;
import edu.cmu.cs.webapp.task7.formbean.*;

public class ViewPortfolioAction extends Action {
	private CustomerDAO customerDAO;
	private FundPriceHistoryDAO historyDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;

	public ViewPortfolioAction(Model model) {
		// TODO Auto-generated constructor stub
		customerDAO = model.getCustomerDAO();
		historyDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "viewPortfolio.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {
			if (session.getAttribute("user") != null
					&& session.getAttribute("user") instanceof CustomerBean) {

				CustomerBean customer = (CustomerBean) request
						.getSession(false).getAttribute("user");

				DecimalFormat df2 = new DecimalFormat("#,##0.00");
				DecimalFormat df3 = new DecimalFormat("#,##0.000");
				String cash = df2.format(customer.getCash() / 100.0);
				String balance = df2.format(transactionDAO.getValidBalance(
						customer.getUserName(), customer.getCash() / 100.0));

				PositionBean[] positionList = positionDAO.getFunds(customer
						.getUserName());

				FundDisplay[] fundList = null;
				if (positionList != null && positionList.length > 0) {
					fundList = new FundDisplay[positionList.length];

					for (int i = 0; i < positionList.length; i++) {
						fundList[i].setFundName(fundDAO.read(
								positionList[i].getFundId()).getName());
						fundList[i].setTicker(fundDAO.read(
								positionList[i].getFundId()).getSymbol());
						fundList[i].setShares(df3.format(positionList[i]
								.getShares()));
						fundList[i].setTotal(df2.format((positionList[i]
								.getShares() / 1000.0)
								* (historyDAO.getLatestPrice(positionList[i]
										.getFundId()) / 100.0)));
					}

				}

				request.setAttribute("cash", cash);
				request.setAttribute("balance", balance);
				request.setAttribute("fundList", fundList);

				request.setAttribute("lastTradingDay",
						transactionDAO.getLastDate(customer) == null ? "N/A"
								: transactionDAO.getLastDate(customer));
				
				request.setAttribute("customer", customer);

				return "viewAccount.jsp";
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
