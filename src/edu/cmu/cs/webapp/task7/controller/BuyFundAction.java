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


public class BuyFundAction  extends Action {
	private FormBeanFactory<BuyForm> formBeanFactory = FormBeanFactory.getInstance(BuyForm.class);

	private FundDAO fundDAO;
	private CustomerDAO  customerDAO;
	private PositionDAO posDAO;
	private TransactionDAO transactionDAO;
	
	public BuyFundAction(Model model) {
		fundDAO = model.getFundDAO();
    	customerDAO  = model.getCustomerDAO();
    	posDAO=model.getPositionDAO();
    	transactionDAO=model.getTransactionDAO();
	}

	public String getName() { return "addFav.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			if(request.getSession().getAttribute("customer")==null){
	            errors.add("Please log in as a customer");
	            return "login.jsp";
	        }
			
            // Set up user list for nav barS
			request.setAttribute("customerList",customerDAO.getUsers());

			CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("customer");
			
			
			
			
        	FundBean[] fundList = fundDAO.getAllFunds();
	        request.setAttribute("fundList",fundList);

			BuyForm form = formBeanFactory.create(request);
	        errors.addAll(form.getValidationErrors());
	        
	        //System.out.println(errors);
	        
	        
	        if (errors.size() > 0) return "error.jsp";
	        
	        String fund=form.getFund();
	        
	        
			long amount=Long.parseLong(form.getAmount());
			int id=fundDAO.getFundByName(fund);
			
			long availableBalance= (long) transactionDAO.getValidBalance(user.getUserName(), user.getCash()/100);
			DecimalFormat df2 = new DecimalFormat("#,##0.00");			
			String availableBalanceString = df2.format(availableBalance).toString();
			
			if ((availableBalance - amount) < 0.0) {
				errors.add("You do not have enough cash balance in your account. You have only $"
						+ availableBalanceString + " left");
				return "buyFund.jsp";
			}
			else{
				// write a method in posDAO to increase the number of shares.
				//posDAO.increaseShares(id, shares,user.getUserName());
			}
			amount = Math.round(amount*100);
			
			
			
			//ALSO ADD TO THE TRANSACTIONS TABLE
			TransactionBean transbean= new TransactionBean();
			transbean.setUserName(user.getUserName());
			transbean.setFundId(id);
			//transbean.setShares(shares);
			transbean.setTransactionType(transbean.BUY_FUND);
			transbean.setAmount((long) (amount));
			transbean.setExecuteDate(null);
			transactionDAO.create(transbean);

			// Update favoriteList (there's now one more on the list)
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
