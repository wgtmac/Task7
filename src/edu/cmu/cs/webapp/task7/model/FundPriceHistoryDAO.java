package edu.cmu.cs.webapp.task7.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.task7.databean.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {
	public FundPriceHistoryDAO(ConnectionPool cp, String tableName)
			throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}
	
	public List<FundPriceHistoryBean> getFundPriceHistoryList(int fundId) throws RollbackException{
		FundPriceHistoryBean[] fundPriceHistory = match(MatchArg.equals("fundId", fundId));
		if(fundPriceHistory.length == 0) return null;
		List<FundPriceHistoryBean> historyList = new ArrayList<FundPriceHistoryBean>();
		for(Object o : fundPriceHistory) {
			historyList.add((FundPriceHistoryBean) o);
		}
		return historyList;
	}
	
	public FundPriceHistoryBean getLatestFundPrice(int fundId) throws RollbackException{
		FundPriceHistoryBean[] fundPriceHistory = match(MatchArg.equals("fundId", fundId));
		if(fundPriceHistory.length == 0) return null;
		return fundPriceHistory[fundPriceHistory.length-1];
	}
	
	public long getLatestPrice (int id) throws RollbackException, ParseException {
		long price = 0;
		try {
			Transaction.begin();
			
			String date = null;
			FundPriceHistoryBean[] fb = match();
			if (fb != null && fb.length != 0) {
				Arrays.sort(fb);
				date = fb[fb.length - 1].getPriceDate();
			}
			price = read(id, date).getPrice();

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return price;
	}
	
	public Date getLatestTradingDayDate () throws RollbackException, ParseException {

		Date date = null;
		try {
			Transaction.begin();

			FundPriceHistoryBean[] fb = match();


			if (fb != null && fb.length != 0) {
				Arrays.sort(fb);
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setLenient(false);
				date = dateFormat.parse(fb[fb.length - 1].getPriceDate());
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		return date;
	}
	
	public String getLatestTradingDayDateString () throws RollbackException, ParseException {
		String date = null;
		try {
			Transaction.begin();
			
			FundPriceHistoryBean[] fb = match();
			if (fb != null && fb.length != 0) {
				Arrays.sort(fb);
				
				date = fb[fb.length - 1].getPriceDate();
			}
			

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return date;
	}
	
	public String getLatestTradingDayByCustomer (String userName) throws RollbackException, ParseException {
		String date = null;
		try {
			Transaction.begin();
			
			FundPriceHistoryBean[] fb = match(MatchArg.equals("userName", userName));
			if (fb != null && fb.length != 0) {
				Arrays.sort(fb);
				
				date = fb[fb.length - 1].getPriceDate();
			}
			

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return date;
	}
	
	/*public String getCurrentDate() throws RollbackException, ParseException {
		Date date = null;
		String currentDate=null;
		try {
			Transaction.begin();

			FundPriceHistoryBean[] fb = match();
			Arrays.sort(fb);

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			dateFormat.setLenient(false);
			date = dateFormat.parse(fb[fb.length].getPriceDate());
			currentDate= dateFormat.format(date);

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return currentDate;
	}*/
}
