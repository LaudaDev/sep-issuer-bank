package app.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class IssuerInfo {
	
	private int orderId;
	
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone="CET")
	private Date timestamp;	
	
	public IssuerInfo() {
		super();
	}

	public IssuerInfo(int orderId, Date timestamp) {
		super();
		this.orderId = orderId;
		this.timestamp = timestamp;
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
