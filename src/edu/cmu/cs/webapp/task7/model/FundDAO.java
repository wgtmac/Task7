package edu.cmu.cs.webapp.task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;


import edu.cmu.cs.webapp.task7.databean.FundBean;

public class FundDAO extends GenericDAO<FundBean> {
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundBean.class, tableName, cp);
	}
	
	public FundBean[] getfunds(String username) throws RollbackException {
		FundBean[] list = match(MatchArg.equals("username",username));
		//Arrays.sort(list);
		return list;
	}

}
