package app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Transaction {
	
	@Id
	private String id;
	private Date timestamp;
	private CheckCardRequest request;
	
	public Transaction(){
		super();
	}

	public Transaction(Date timestamp, CheckCardRequest request) {
		super();
		this.timestamp = timestamp;
		this.request = request;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public CheckCardRequest getRequest() {
		return request;
	}

	public void setRequest(CheckCardRequest request) {
		this.request = request;
	}
}
