package app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CheckCardResponse {
	
	@Id
	private String id;
	private AcquirerInfo acquirerInfo;
	private IssuerInfo issuerInfo;
	private TransactionStatus transactionStatus;
	
	public CheckCardResponse() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AcquirerInfo getAcquirerInfo() {
		return acquirerInfo;
	}

	public void setAcquirerInfo(AcquirerInfo acquirerInfo) {
		this.acquirerInfo = acquirerInfo;
	}

	public IssuerInfo getIssuerInfo() {
		return issuerInfo;
	}

	public void setIssuerInfo(IssuerInfo issuerInfo) {
		this.issuerInfo = issuerInfo;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
}
