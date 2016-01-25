package app.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class IssuerInfo {
	
	private int id;
	private int orderId;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private Date timestamp;
	private BigDecimal transactionAmount;
	
	
	public IssuerInfo() {
		super();
	}

	public IssuerInfo(int id, int orderId, Date timestamp, BigDecimal transactionAmount) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.timestamp = timestamp;
		this.transactionAmount = transactionAmount;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
}
