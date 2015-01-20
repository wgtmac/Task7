package edu.cmu.cs.webapp.task7.controller;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.task7.databean.*;
import edu.cmu.cs.webapp.task7.model.*;
import edu.cmu.cs.webapp.task7.formbean.*;




public class ViewPortfolioAction extends Action{	
	private CustomerDAO customerDAO;
	private FundPriceHistoryDAO historyDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	
	/*public ViewPortfolioAction(CustomerDAO customerDaoImpl,FundPriceHistoryDAO fundPriceHistoryDaoImpl, PositionDAO positionDaoImpl, TransactionDAO transactionDaoImpl) {
		this.customerDaoImpl = customerDaoImpl;
		this.fundPriceHistoryDaoImpl = fundPriceHistoryDaoImpl;
		this.positionDaoImpl = positionDaoImpl;
		this.transactionDaoImpl = transactionDaoImpl;
	}*/	
	public ViewPortfolioAction(Model model) {
		// TODO Auto-generated constructor stub
		customerDAO = model.getCustomerDAO();
		historyDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {return "viewPortfolio.do";}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		List<String> errorsCustomer = new ArrayList<String>();
        request.setAttribute("errorsCustomer", errorsCustomer);
        
        if(request.getSession().getAttribute("customer")==null){
        	errorsCustomer.add("Please log in as a customer");
        	return "login.jsp";
        }
        	try{
        		CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("customer");            
            double availableBalanceDouble = ((double)(user.getCash())/100);
			DecimalFormat df2 = new DecimalFormat("#,##0.00");			
			String availableBalanceString = df2.format(availableBalanceDouble).toString();
			//double totalBalanceDouble = ((double)(customer.getTotalBalance())/100);
			//String totalBalanceString = df2.format(totalBalanceDouble).toString();
		    CustomerDisplay customerDisplay = new CustomerDisplay (customer,availableBalanceString,totalBalanceString);
			request.setAttribute("customerDisplay", customerDisplay);
			
			List<Position> positionList = positionDaoImpl.getAllPositionByCustomer(customer);
			if(positionList != null) {
				List<PositionInfo> positionInfoList = new ArrayList<PositionInfo>();
				for(Position a: positionList) {
					double shares = ((double)(a.getShares())/1000);
					
					double price = ((double)(fundPriceHistoryDaoImpl.getLatestFundPrice(a.getTicker()).getPrice()))/100;
					double value = shares * price;

					DecimalFormat df1 = new DecimalFormat("#,##0.000");
				
					String sharesString = df1.format(shares).toString();
					String priceString = df2.format(price).toString();
					String valueString = df2.format(value).toString();
				
					PositionInfo aInfo = new PositionInfo(a.getTicker(),sharesString,priceString,valueString);
					positionInfoList.add(aInfo);
				}
				request.setAttribute("positionInfoList",positionInfoList);
				
			}
			String transDate = transactionDaoImpl.getLatestDate(customer);
			request.setAttribute("lastTradingDay",transDate);
			return "CustomerViewAccount.jsp";
        	} catch (RollbackException e) {
    			// TODO Auto-generated catch block
            	errorsCustomer.add(e.getMessage());
            	return "CustomerViewAccount.jsp";
    		}
			
	}

}
  