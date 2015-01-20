package edu.cmu.cs.webapp.task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.task7.databean.FundBean;
import edu.cmu.cs.webapp.task7.databean.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean> {
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PositionBean.class, tableName, cp);
	}
	
	

	public void reduceShares(int id, int shares, String userName) {
		// Method to reduce the number of shares when a customer sells them
		try {
			Transaction.begin();
    		PositionBean p = read(id);
    		 

    		if (p == null) {
				throw new RollbackException("Fund does not exist: id="+id);
    		}

    		if (userName !=p.getUserName()) {
				throw new RollbackException("Favorite not owned by "+userName);        // make sth for displaying the name
    		}

			delete(id);
			Transaction.commit();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	public int getShares(int fundId){
		int id=0;
		try{

			Transaction.begin();
			PositionBean p = read(fundId);



			if (p == null) {
				throw new RollbackException("Fund does not exist: id="+fundId);
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
