
package edu.cmu.cs.webapp.task7.controller;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.task7.databean.CustomerBean;
import edu.cmu.cs.webapp.task7.databean.EmployeeBean;
import edu.cmu.cs.webapp.task7.databean.PositionBean;
import edu.cmu.cs.webapp.task7.databean.PositionInfo;
import edu.cmu.cs.webapp.task7.databean.TransactionBean;
import edu.cmu.cs.webapp.task7.formbean.CreateFundForm;
import edu.cmu.cs.webapp.task7.formbean.DepositCheckForm;
import edu.cmu.cs.webapp.task7.formbean.ViewCustomerForm;
import edu.cmu.cs.webapp.task7.model.CustomerDAO;
import edu.cmu.cs.webapp.task7.model.EmployeeDAO;
import edu.cmu.cs.webapp.task7.model.FundDAO;
import edu.cmu.cs.webapp.task7.model.FundPriceHistoryDAO;
import edu.cmu.cs.webapp.task7.model.Model;
import edu.cmu.cs.webapp.task7.model.PositionDAO;
import edu.cmu.cs.webapp.task7.model.TransactionDAO;


public class ViewCustomerAction extends Action {
	
	private FormBeanFactory<ViewCustomerForm> formBeanFactory = FormBeanFactory
			.getInstance(ViewCustomerForm.class);
	

	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO historyDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;

	public ViewCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		fundDAO=model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		historyDAO = model.getFundPriceHistoryDAO();
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
				
				DecimalFormat df3 = new DecimalFormat("#,##0.000");
				DecimalFormat df2 = new DecimalFormat(	"###,###.00");
				
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
				
				String lastDay = transactionDAO.getLastDate(customer);
				request.setAttribute("lastDay", lastDay == null ? "No recent transaction" : lastDay);
				
				request.setAttribute("cash",df2.format(customer.getCash() / 100.0));
				request.setAttribute("avai_cash",df2.format(transactionDAO.getValidBalance(customer.getUserName(), customer.getCash() / 100.0)));
				
				PositionBean[] fundList = positionDAO.getFunds(form.getUserName1());
		        request.setAttribute("fundList",fundList);
		        
		    	List<PositionBean> positionList = positionDAO.getAllPositionByCustomer(customer);
				if(positionList != null) {
					List<PositionInfo> positionInfoList = new ArrayList<PositionInfo>();
					for(PositionBean a: positionList) {
						double shares = ((double)(a.getShares())/1000.0);
						
						double price = ((double)(historyDAO.getLatestFundPrice(a.getFundId()).getPrice() / 100.0));
						double value = shares * price;
						String ticker=fundDAO.getFundNameById(a.getFundId());
					
						String sharesString = df3.format(shares);
						String priceString = df2.format(price);
						String valueString = df2.format(value);
					
						PositionInfo aInfo = new PositionInfo(ticker,sharesString,priceString,valueString);
						positionInfoList.add(aInfo);
					}
					request.setAttribute("positionInfoList",positionInfoList);
					
				}
				
				
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
