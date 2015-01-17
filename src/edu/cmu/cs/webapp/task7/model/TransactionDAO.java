package edu.cmu.cs.webapp.task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import edu.cmu.cs.webapp.task7.databean.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean> {
	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(TransactionBean.class, tableName, cp);
	}
}
