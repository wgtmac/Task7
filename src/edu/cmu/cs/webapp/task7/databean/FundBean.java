package edu.cmu.cs.webapp.task7.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId")
public class FundBean {
	private int 	fundId;
	private String 	fundName;
	private String 	ticker;
	private long iniPrice;
	
	public void setFundId			(int v)			{ fundId = v; 	}
	public void setFundName 			(String  v)	{ fundName = v;	}
	public void setTicker			(String  v)	{ ticker = v;	}
	public void setIniPrice			(long  v)	{ iniPrice = v;	}
	
	public int 		getFundId		()		{ return fundId; 	}
	public String 	getFundName 		()	 	{ return fundName;	}
	public String 	getTicker 	()	 	{ return ticker;	}
	public long 	getIniPrice 	()	 	{ return iniPrice;	}
}
