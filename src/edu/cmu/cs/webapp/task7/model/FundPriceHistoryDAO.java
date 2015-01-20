package edu.cmu.cs.webapp.task7.model;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.task7.databean.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {
	public FundPriceHistoryDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}

	public void create (FundPriceHistoryBean fphb) throws RollbackException {
		try {
			Transaction.begin();

			if (read(fphb) != null)
				throw new RollbackException("Price of fund has alreadly been updated today");
			
			create(fphb);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
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
			if (Transaction.isActive()) Transaction.rollback();
		}
		return date;
	}
}
