package edu.cmu.cs.webapp.task7.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.task7.databean.FundBean;
import edu.cmu.cs.webapp.task7.databean.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean> {
	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(TransactionBean.class, tableName, cp);
	}
	
	public TransactionBean[] getAllPendingTrans () throws RollbackException {
		TransactionBean[] tbs = null;
		try {
			Transaction.begin();
			
			// How to execute select * from table where transactionType IS NULL
			tbs =  match(MatchArg.equals("executeDate", null));
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		return tbs;
	}
	
	public Date getLatestDate () throws RollbackException, ParseException {
		Date date = null;
		try {
			Transaction.begin();
		
			TransactionBean[] t =  match(MatchArg.notEquals("executeDate", null));
			if (t != null && t.length != 0) {
				Arrays.sort(t);
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setLenient(false);
				date = dateFormat.parse(t[t.length - 1].getExecuteDate());
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		return date;
	}

	public TransactionBean[] getTransactions(String userName) throws RollbackException {
		TransactionBean[] list = match(MatchArg.equals("userName", userName));
		// Arrays.sort(list);
		return list;
		
	}
}
