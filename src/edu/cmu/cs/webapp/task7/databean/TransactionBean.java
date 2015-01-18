package edu.cmu.cs.webapp.task7.databean;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")
public class TransactionBean {
	// For transcation type
	public final static int BUY_FUND = 8;
	public final static int SELL_FUND = 4;
	public final static int REQ_CHECK = 2;
	public final static int DPT_CHECK = 1;
	
	private int 		transactionId;
	private String 	userName;
	private int 		fundId;
	private Date  	executeDate;
	private long 		shares;
	private int 		transactionType;
	private long 		amount;
	
	public void setTransactionId 		(int  v) 		{ transactionId = v; }
	public void setUserName			(String v)	{ userName = v;	}
	public void setFundId					(int v)			{ fundId = v; 	}
	public void setExecuteDate			(Date  v) 		{ executeDate = v; 	}
	public void setShares 					(long  v)		{ shares = v;	}
	public void setTransactionType	(int  v)			{ transactionType = v; 	}
	public void setAmount				(long  v)		{ amount = v; 	}
	
	public int 		getTransactionId	() { return transactionId; 	}
	public String 	getUserName		() { return userName; 	}
	public int 		getFundId				() { return fundId; 	}
	public Date 	getExecuteDate		() { return executeDate; 	}
	public long 	getShares 				() { return shares;	}
	public int 		getTranscationType(){ return transactionType; 	}
	public long	getAmount			()	{return amount; 	}
}
