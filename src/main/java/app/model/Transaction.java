package app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class Transaction {
	
	@Id
	private int id;
	
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone="CET")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
