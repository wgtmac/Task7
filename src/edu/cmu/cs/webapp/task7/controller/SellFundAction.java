package edu.cmu.cs.webapp.task7.controller;




import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;





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

public class SellFundAction  extends Action {
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory.getInstance(SellFundForm.class);

	private FundDAO fundDAO;
	private CustomerDAO  customerDAO;
	private PositionDAO posDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO historyDAO;
	
	public SellFundAction(Model model) {
		fundDAO = model.getFundDAO();
    	customerDAO  = model.getCustomerDAO();
    	posDAO=model.getPositionDAO();
    	transactionDAO=model.getTransactionDAO();
    	historyDAO= model.getFundPriceHistoryDAO();
	}

	public String getName() { return "sellFund.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav barS
			//request.setAttribute("customerList",customerDAO.getUsers());
			
			//CHECK FOR THE NUMBER OF SHARES
			CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
			System.out.println("The user is"+user.getUserName());
			
			PositionBean[] fundList = posDAO.getfunds(user.getUserName());
			
	        request.setAttribute("fundList",fundList);

			SellFundForm form = formBeanFactory.create(request);
			String fund=form.getFund();
			System.out.println();
			long shares=form.getShares();
			int id=fundDAO.getFundIdByName(fund);
			//posDAO.reduceShares(id, shares,user.getUserName());
			
			//ALSO ADD TO THE TRANSACTIONS TABLE
			TransactionBean transbean= new TransactionBean();
			transbean.setUserName(user.getUserName());
			transbean.setFundId(id);
			transbean.setShares(shares);
			transbean.setTransactionType(transbean.SELL_FUND);
			//transbean.setAmount(historyDAO.getPriceByFundId(id, historyDAO.getCurrentDate()));
			transbean.setExecuteDate(null);
			transactionDAO.create(transbean);
			
			errors.addAll(form.getValidationErrors());
	      
	        if (errors.size() > 0) return "error.jsp";
	        
	      

			// Update transactionList (there's now one more on the list)
        	TransactionBean[] newTransactionList = transactionDAO.getTransactions(user.getUserName());
	        request.setAttribute("transactionList",newTransactionList);
	        return "history.jsp";
	 	}
		catch (RollbackException e) {
	 		e.printStackTrace();
			errors.add(e.getMessage());
			return "viewAccount.jsp";
	 	} catch (FormBeanException e) {
	 		e.printStackTrace();
			errors.add(e.getMessage());
			return "viewAccount.jsp";
		}
    }
    
    
}
