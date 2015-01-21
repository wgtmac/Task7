package edu.cmu.cs.webapp.task7.model;

import java.util.ArrayList;
import java.util.List;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;


import edu.cmu.cs.webapp.task7.databean.CustomerBean;
import edu.cmu.cs.webapp.task7.databean.FundBean;
import edu.cmu.cs.webapp.task7.databean.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean> {
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PositionBean.class, tableName, cp);
	}
	
	public PositionBean[] getFunds(String userName) throws RollbackException {
		return match(MatchArg.equals("userName", userName));
	}
	
	public List<PositionBean> getAllPositionByCustomer(CustomerBean c) throws RollbackException{
		// TODO Auto-generated method stub
		PositionBean[] list = match(MatchArg.equals("userName", c.getUserName()));
		if(list.length == 0) return null;
		List<PositionBean> positionList = new ArrayList<PositionBean>();
		for(Object o : list) {
			positionList.add((PositionBean) o);
		}
		return positionList;
	}

	
	public long getShares(int fundId, String userName){
		long shares=0;
		try{

			Transaction.begin();
			PositionBean p = read(fundId,userName);



			if (p == null) {
				throw new RollbackException("Fund does not exist: id="+fundId);
			}

			shares= p.getShares();
			Transaction.commit();
		}		
		catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}	return shares;
	}
	
	public int getFundIdByCustomerId(String userName){
		int id=0;
		try{

			Transaction.begin();
			PositionBean p = read(userName);



			if (p == null) {
				throw new RollbackException("Fund does not exist for="+userName);
			}

			id= p.getFundId();
			Transaction.commit();
		}		
		catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}	return id;
	}
}
