package app.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document	
public class AcquirerInfo {
	
	private int orderId;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
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
