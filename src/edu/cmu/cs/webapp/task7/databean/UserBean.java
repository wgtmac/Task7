package edu.cmu.cs.webapp.task7.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("userID")
public class UserBean {
	private int 		userID;
	private String 	email;
	private String 	firstName;
	private String 	lastName;
	private String 	password;

	public void setUserID		(int id) 				{ userID = id; 				}
	public void setEmail			(String addr) 		{ email = addr; 			}
	public void setFirstName(String name)	{ firstName = name;	}
	public void setLastName	(String name)	{ lastName = name; 	}
	public void setPassword	(String pwd) 		{ password = pwd; 	}

	public int 		getUserID		() { return userID; 		}
	public String 	getEmail			() { return email; 			}
	public String 	getFirstName	() { return firstName; 	}
	public String 	getLastName	() { return lastName; 	}
	public String 	getPassword	() { return password; 	}
}