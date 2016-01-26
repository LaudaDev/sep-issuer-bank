package app.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import app.util.Config;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document	
public class AcquirerInfo {
	
	private int orderId;
	
	@JsonFormat(pattern = Config.timestampFormat, timezone="CET")
	private Date timestamp;
	
	public AcquirerInfo(){
		super();
	}

	public AcquirerInfo(int orderId, Date timestamp) {
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
