package edu.cmu.cs.webapp.task7.model;

import java.util.ArrayList;
import java.util.List;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;


import org.genericdao.RollbackException;


import org.genericdao.Transaction;


import edu.cmu.cs.webapp.task7.databean.FundBean;
import edu.cmu.cs.webapp.task7.databean.PositionBean;
import edu.cmu.cs.webapp.task7.databean.FundBean;

public class FundDAO extends GenericDAO<FundBean> {
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundBean.class, tableName, cp);
	}
	
	public List<FundBean> getAllFundsList() throws RollbackException{
		FundBean[] fund = match();
		if(fund.length == 0) return null;
		List<FundBean> fundList= new ArrayList<FundBean>();
		for(FundBean o : fund) {
			fundList.add((FundBean) o);
		}
		return fundList;

	}

	public int getFundIdByName(String fundName) throws RollbackException{
		int id=0;
		try{

			Transaction.begin();
			FundBean[] p = match(MatchArg.equals("name", fundName));



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

	public FundBean[] getfunds(String userName) throws RollbackException {
		FundBean[] list = match(MatchArg.equals("userName", userName));
		// Arrays.sort(list);
		return list;
	}

	public FundBean[] getAllFunds() throws RollbackException {
		return match();
	}

}
