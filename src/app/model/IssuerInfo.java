package app.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class IssuerInfo {
	
	private int id;
	private int orderId;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private Date timestamp;
	
	
	public IssuerInfo() {
		super();
	}

	public IssuerInfo(int id, int orderId, Date timestamp) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.timestamp = timestamp;
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
	
}
