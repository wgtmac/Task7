package edu.cmu.cs.webapp.task7.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerId,fundId")
public class PositionBean {
	private int	 	customerId;
	private int 	fundId;
	private long 	shares;
	
	public void setCustomerId			(int v)			{ customerId = v;	}
	public void setFundId					(int v)			{ fundId = v; 	}
	public void setShares 					(long  v)		{ shares = v;	}

	public int 		getCustomerId		() { return customerId; 	}
	public int 		getFundId				() { return fundId; 	}
	public long 	getShares 				() { return shares;	}
}
