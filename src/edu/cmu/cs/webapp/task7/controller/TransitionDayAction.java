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

import edu.cmu.cs.webapp.task7.databean.CustomerBean;
import edu.cmu.cs.webapp.task7.databean.EmployeeBean;
import edu.cmu.cs.webapp.task7.databean.FundBean;
import edu.cmu.cs.webapp.task7.databean.FundPriceHistoryBean;
import edu.cmu.cs.webapp.task7.databean.PositionBean;
import edu.cmu.cs.webapp.task7.databean.TransactionBean;
import edu.cmu.cs.webapp.task7.formbean.TransitionDayForm;
import edu.cmu.cs.webapp.task7.model.CustomerDAO;
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
	private 	PositionDAO			positionDAO;

	public TransitionDayAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
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
				
				NumberFormat formatter = new DecimalFormat("#0.00");     		
				HashMap<Integer, String> price_map = new HashMap<Integer, String>();
				String lastTradingDay = fundPriceHistoryDAO.getLatestTradingDayDateString ();
				for (FundBean fb : fundList) {
					FundPriceHistoryBean tmp = fundPriceHistoryDAO.read(fb.getFundId() ,lastTradingDay);
					if (tmp == null) {
						price_map.put(fb.getFundId(),"N/A");
					} else {
						price_map.put(fb.getFundId(),  formatter.format(tmp.getPrice() / 100.0 ));
					}
				}
				request.setAttribute("price_map", price_map);
				
				// If no params were passed, return with no errors so that the
				// form will be presented (we assume for the first time).
				if (!form.isPresent()) {
					return "transitionDay.jsp";
				}
				
				// check date
	        	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        	SimpleDateFormat inputDate = new  SimpleDateFormat("yyyy-MM-dd");
	        	dateFormat.setLenient(false);
	        	inputDate.setLenient(false);
	        	Date date = inputDate.parse(form.getDate());
	        	Date lastFundDay = fundPriceHistoryDAO.getLatestTradingDayDate();
	        	Date lastTranDay = transactionDAO.getLatestDate();
	        	Date lastDay = null;
	        	
	        	if (lastFundDay != null && lastTranDay != null) {
	        		lastDay = lastFundDay.compareTo(lastTranDay) <= 0 ? lastTranDay : lastFundDay;
	        	} else {
	        		lastDay = lastFundDay == null ? lastTranDay : lastFundDay;
	        	}
	        	
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
				String today = dateFormat.format(date);
				for (TransactionBean tb : transactionDAO.getAllPendingTrans()){
					CustomerBean cb = customerDAO.read(tb.getUserName());
					
					switch(tb.getTransactionType()) {
						case TransactionBean.SELL_FUND:
							if (positionDAO.read(tb.getUserName(), tb.getFundId()) != null) {
								PositionBean pb = positionDAO.read(tb.getUserName(), tb.getFundId());
								pb.setShares(pb.getShares() - tb.getShares());
								positionDAO.update(pb);
								
								double price = fundPriceHistoryDAO.read(tb.getFundId() ,  today).getPrice() / 100.0;
								long amount = (long) (price * tb.getShares() / 1000 * 100);
								cb.setCash(cb.getCash() +  amount);
								customerDAO.update(cb);
								
								tb.setAmount(amount);
							}
							break;
						case TransactionBean.BUY_FUND:
							if (positionDAO.read(tb.getUserName() , tb.getFundId()) == null) {
								double amount = tb.getAmount() / 100.00;
								double price = fundPriceHistoryDAO.read(tb.getFundId() , today).getPrice() / 100.0;
								long shares = (long) (amount / price * 1000);
								
								PositionBean pb = new PositionBean();
								pb.setUserName(tb.getUserName());
								pb.setFundId(tb.getFundId());
								pb.setShares(shares);
								positionDAO.create(pb);
								
								tb.setShares(shares);
							} else {
								double amount = tb.getAmount() / 100.00;
								double price = fundPriceHistoryDAO.read(tb.getFundId() , today).getPrice() / 100.00;
								long shares = (long) (amount / price * 1000);
								
								PositionBean pb = positionDAO.read(tb.getUserName(),  tb.getFundId());
								pb.setShares(shares + pb.getShares());
								positionDAO.update(pb);
							}
									
							cb.setCash(cb.getCash() -  tb.getAmount());
							customerDAO.update(cb);
							break;
						case TransactionBean.REQ_CHECK:
							cb.setCash(cb.getCash() -  tb.getAmount());
							customerDAO.update(cb);
							break;
						case TransactionBean.DPT_CHECK:
							cb.setCash(cb.getCash() +  tb.getAmount());
							customerDAO.update(cb);
							break;	
						default:
							break;
					}
					
					tb.setExecuteDate(today);
					transactionDAO.update(tb);
				}
				
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
