package edu.cmu.cs.webapp.task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import edu.cmu.cs.webapp.task7.databean.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {
	public FundPriceHistoryDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}

}
