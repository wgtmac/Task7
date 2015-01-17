package edu.cmu.cs.webapp.task7.databean;

import java.sql.Date;
import org.genericdao.PrimaryKey;

@PrimaryKey("fundId,priceDate")
public class FundPriceHistoryBean {
	private int 		fundId;
	private Date	 	priceDate;
	private long	 	price;
	
	public void setFundId			(int v)			{ fundId = v; 	}
	public void setPriceDate 		(Date  v)		{ priceDate = v;	}
	public void setPrice				(long  v)		{ price = v;	}
	
	public int 		getFundId		()		{ return fundId; 	}
	public Date 	getPriceDate 	()	 	{ return priceDate;	}
	public long 	getPrice		 	()	 	{ return price;	}
}
