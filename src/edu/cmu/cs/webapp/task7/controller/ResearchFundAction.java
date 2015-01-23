package edu.cmu.cs.webapp.task7.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.task7.databean.*;
import edu.cmu.cs.webapp.task7.formbean.*;
import edu.cmu.cs.webapp.task7.model.*;


public class ResearchFundAction extends Action {
	//private static Logger logger = Logger.getLogger(ResearchFundAction.class);
	private FormBeanFactory<ResearchFundForm> formBean = FormBeanFactory
			.getInstance(ResearchFundForm.class);
	
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private CustomerDAO customerDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public ResearchFundAction(Model model) {
		this.fundDAO = model.getFundDAO();
		this.transactionDAO= model.getTransactionDAO();
		this.customerDAO = model.getCustomerDAO();
		this.positionDAO = model.getPositionDAO();
		this.fundPriceHistoryDAO =model.getFundPriceHistoryDAO();

	}
	
	@Override
	public String getName() {
		return "researchFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		 
		
		try {
			if (request.getSession().getAttribute("user") == null || 
					request.getSession().getAttribute("user") instanceof EmployeeBean) {
				errors.add("Please log in as a customer");
				return "login.jsp";
			}
			
			ResearchFundForm form = formBean.create(request);
			request.setAttribute("form", form);
			request.setAttribute("description", "");
			
			List<FundBean> funds = fundDAO.getAllFundsList();
			
			request.setAttribute("funds", funds);
			errors.addAll(form.getValidationErrors());
			
			if (errors.size() != 0) {
				return "researchFunds.jsp";
			}
			
			String fund = form.getFund1();
			
			
			int fndId=fundDAO.getFundIdByName(fund);
			
			
			
			if (form.getButton()!=null &&form.getButton().equals("Fund History")) {
				
				//System.out.println("id inside button is"+fndId);
				
				List<Map<String,String>> fundPriceHistory = getFundPriceHistory(fndId); 
				if (fundPriceHistory.isEmpty()) 
					errors.add("There is no history for the " + fundDAO.getFundNameById(fndId) + " fund");
				request.setAttribute("fundPriceHistory", fundPriceHistory);
				request.setAttribute("description", "Lorem Ipsum");
				request.setAttribute("fundTitle", fundDAO.getFundNameById(fndId));
				request.setAttribute("chartData", chartData(fndId));
			}
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "researchFunds.jsp";
		} /*catch (RollbackException e) {
			errors.add("Error in database. Please try again later.");
			return "researchFunds.jsp";
		}*/catch (Exception e) {
			errors.add("");
			//logger.error(e.getMessage());
			return "researchFunds.jsp";
		}
		return "researchFunds.jsp";
	}

	
	
	private List<Map<String, String>> getFundPriceHistory(
			int fundId) {
		try {
		List<Map<String,String>> fundPriceHistory = new ArrayList<Map<String,String>>();
		List<FundPriceHistoryBean> fundPriceHistoryBean;
		
			fundPriceHistoryBean = fundPriceHistoryDAO.getFundPriceHistoryList(fundId);
		if(fundPriceHistoryBean != null){
			for(FundPriceHistoryBean hBean: fundPriceHistoryBean){
				
				String id=Integer.toString(hBean.getFundId());
				Map<String,String> tmp = new HashMap<String,String>();
				tmp.put("fundId",id);
				tmp.put("price",Long.toString(hBean.getPrice()));
				tmp.put("date",hBean.getPriceDate());
				tmp.put("fundName", fundDAO.getFundNameById(hBean.getFundId()));
				
				fundPriceHistory.add(tmp);
			}
			return fundPriceHistory;
		}
		else{
			return fundPriceHistory;
		}
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private String chartData(
			int fundId) {
		try {
		List<FundPriceHistoryBean> fundPriceHistoryBean = fundPriceHistoryDAO.getFundPriceHistoryList(fundId);
		StringBuilder data = new StringBuilder();
		if(fundPriceHistoryBean != null){
			for(FundPriceHistoryBean hBean: fundPriceHistoryBean){
				data.append(hBean.getPriceDate());
				data.append(",");
				data.append(((double)hBean.getPrice())/100);
				data.append(",");
			}
		}
			
		/*System.out.println(data.toString());*/
		return data.toString();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
