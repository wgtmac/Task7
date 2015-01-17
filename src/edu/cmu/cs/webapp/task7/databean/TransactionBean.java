package edu.cmu.cs.webapp.task7.databean;

import java.sql.Date;
import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")
public class TransactionBean {
	private int 	transactionId;
	private int	 	customerId;
	private int 	fundId;
	private Date  executeDate;
	private long 	shares;
	private int 	transcationType;
	private long 	amount;
	
	public void setTransactionId 		(int  v) 		{ transactionId = v; }
	public void setCustomerId			(int v)			{ customerId = v;	}
	public void setFundId					(int v)			{ fundId = v; 	}
	public void setExecuteDate			(Date  v) 		{ executeDate = v; 	}
	public void setShares 					(long  v)		{ shares = v;	}
	public void setTranscationType	(int  v)			{ transcationType = v; 	}
	public void setAmount				(long  v)		{ amount = v; 	}
	
	public int 		getTransactionId	() { return transactionId; 	}
	public int 		getCustomerId		() { return customerId; 	}
	public int 		getFundId				() { return fundId; 	}
	public Date 	getExecuteDate		() { return executeDate; 	}
	public long 	getShares 				() { return shares;	}
	public int 		getTranscationType(){ return transcationType; 	}
	public long	getAmount			()	{return amount; 	}
}
