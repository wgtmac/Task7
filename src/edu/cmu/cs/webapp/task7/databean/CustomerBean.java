package edu.cmu.cs.webapp.task7.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerId")
public class CustomerBean {
	private int 		customerId;
	private String 	firstName;
	private String 	lastName;
	private String	userName;
	private String 	password;
	private String 	address1;
	private String 	address2;
	private String 	city;
	private String 	state;
	private long		cash;

	public void setCustomerId	(int v) 		{ customerId = v; }
	public void setUserName(String v) 	{ userName = v; }
	public void setFirstName(String v)	{ firstName = v;	}
	public void setLastName	(String v)	{ lastName = v; 	}
	public void setPassword	(String v) 	{ password = v; 	}
	public void setAddress1	(String v) 	{ address1 = v; 	}
	public void setAddress2	(String v) 	{ address2 = v; 	}
	public void setCity	(String v) 			{ city = v; 			}
	public void setState	(String v) 			{ state = v; 			}
	public void setCash (long v)				{ cash = v;			}
	
	public int 		getCustomerId	() { return customerId;}
	public String 	getUserName	() { return userName; 	}
	public String 	getFirstName		() { return firstName; 	}
	public String 	getLastName		() { return lastName; 	}
	public String 	getPassword		() { return password; 	}
	public String 	getAddress1		() { return address1; 	}
	public String 	getAddress2		() { return address2; 	}
	public String 	getCity				() { return city; 				}
	public String 	getStated			() { return state; 			}
	public long	getCash				() { return cash;			}
}
