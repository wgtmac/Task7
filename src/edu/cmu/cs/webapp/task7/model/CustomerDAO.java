package edu.cmu.cs.webapp.task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;


import edu.cmu.cs.webapp.task7.databean.CustomerBean;

public class CustomerDAO extends GenericDAO<CustomerBean> {
	public CustomerDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerBean.class, tableName, cp);
	}
	
	public CustomerBean readByUserName(String username) throws RollbackException {
		CustomerBean[] cb = match(MatchArg.equals("userName", username));
		if (cb == null || cb.length == 0) return null;
		return cb[0];
	}
	
//<<<<<<< HEAD
	public CustomerBean[] getUsers() throws RollbackException {
		CustomerBean[] users = match();
		//Arrays.sort(users);  // We want them sorted by last and first names (as per User.compareTo());
		//createAutoIncrement(users);
		return users;
	}
//=======
	public CustomerBean[] getAllUserName () throws RollbackException {
		return match();
//>>>>>>> 5bc8b3596bf5c4afdad54711deed090e1919b5c8
	}
}
