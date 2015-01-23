package edu.cmu.cs.webapp.task7.model;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import org.genericdao.Transaction;


import edu.cmu.cs.webapp.task7.databean.FundBean;


public class FundDAO extends GenericDAO<FundBean> {
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundBean.class, tableName, cp);
	}
	


	public int getFundIdByName(String fundName) throws RollbackException{
		int id=0;
		try{

			Transaction.begin();
			FundBean[] p = match(MatchArg.equalsIgnoreCase("name", fundName));



			if (p == null) {
				throw new RollbackException("Fund "+fundName +" does not exist");
			}

			id= p[0].getFundId();
			Transaction.commit();
		}		
		catch(ArrayIndexOutOfBoundsException e){
			throw new RollbackException("Fund "+fundName +" does not exist");
		}
		
		catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if (Transaction.isActive()) Transaction.rollback();
		}	return id;
	}
	
	public String getFundNameById(int fundId){
		String fundName=null;
		try{

			Transaction.begin();
			FundBean p = read(fundId);



			if (p == null) {
				throw new RollbackException("Fund does not exist: id="+fundId);
			}

			fundName= p.getName();
			Transaction.commit();
		}		
		catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}	return fundName;
	}


}
