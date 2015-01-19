package edu.cmu.cs.webapp.task7.controller;




import java.util.ArrayList;
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
	
	public SellFundAction(Model model) {
		fundDAO = model.getFundDAO();
    	customerDAO  = model.getCustomerDAO();
    	posDAO=model.getPositionDAO();
	}

	public String getName() { return "sellFund.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav barS
			//request.setAttribute("customerList",customerDAO.getUsers());

			CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
			
        	FundBean[] fundList = fundDAO.getfunds(user.getUserName());
	        request.setAttribute("fundList",fundList);

			ItemForm form = formBeanFactory.create(request);
	        errors.addAll(form.getValidationErrors());
	        System.out.println(errors);
	        if (errors.size() > 0) return "error.jsp";
	        FavoriteBean fav=new FavoriteBean();
	        //System.out.println(fav.getEmail());
	        //System.out.println("user id before"+user.getId());
	        fav.setEmail(user.getId());
	        //System.out.println("fav email id after adding"+fav.getEmail());
	        fav.setComment(form.getComment());
	        fav.setCount(fav.getCount());
	        fav.setFavorite(form.getFavorite());
	        System.out.println(form.getFavorite());

	        favoriteDAO.create(fav);

			// Update favoriteList (there's now one more on the list)
        	FavoriteBean[] newFavoriteList = favoriteDAO.getfavorites(user.getId());
	        request.setAttribute("favoriteList",newFavoriteList);
	        return "manage.jsp";
	 	}
		catch (RollbackException e) {
	 		e.printStackTrace();
			errors.add(e.getMessage());
			return "manage.jsp";
	 	} catch (FormBeanException e) {
	 		e.printStackTrace();
			errors.add(e.getMessage());
			return "manage.jsp";
		}
    }
    
    
}
