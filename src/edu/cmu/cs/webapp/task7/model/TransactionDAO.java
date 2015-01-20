package edu.cmu.cs.webapp.task7.model;




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
	
	public void executePendingTransactions () throws RollbackException {
		try {
			Transaction.begin();
			
			// How to execute select * from table where transactionType IS NULL
			TransactionBean[] tbeans = match(MatchArg.equals("transactionType", null));
			
			for (TransactionBean tb : tbeans) {
				switch(tb.getTransactionType()) {
				case TransactionBean.SELL_FUND:
					break;
				case TransactionBean.BUY_FUND:
					break;
				case TransactionBean.REQ_CHECK:
					break;
				case TransactionBean.DPT_CHECK:
					break;	
				default:
					break;
				}
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public TransactionBean[] getTransactions(String userName) throws RollbackException {
		TransactionBean[] list = match(MatchArg.equals("userName", userName));
		// Arrays.sort(list);
		return list;
		
	}
}
