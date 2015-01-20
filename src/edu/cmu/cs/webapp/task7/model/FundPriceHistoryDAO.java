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
import edu.cmu.cs.webapp.task7.databean.PositionBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {
	public FundPriceHistoryDAO(ConnectionPool cp, String tableName)
			throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}

	public void create(FundPriceHistoryBean fphb) throws RollbackException {
		try {
			Transaction.begin();

			if (read(fphb) != null)
				throw new RollbackException(
						"Price of fund has alreadly been updated today");

			create(fphb);

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public long getPriceByFundId(int fundId, String priceDate) {

		long price = 0;
		try {

			Transaction.begin();
			FundPriceHistoryBean p = read(fundId, priceDate);

			if (p == null) {
				throw new RollbackException("Fund does not exist: id=" + fundId);
			}

			price = p.getPrice();
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

		return price;

	}

	public Date getLatestTradingDay() throws RollbackException, ParseException {
		Date date = null;
		try {
			Transaction.begin();

			FundPriceHistoryBean[] fb = match();
			Arrays.sort(fb);

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			dateFormat.setLenient(false);
			date = dateFormat.parse(fb[fb.length - 1].getPriceDate());

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return date;
	}
	
	public String getCurrentDate() throws RollbackException, ParseException {
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
	}
}
