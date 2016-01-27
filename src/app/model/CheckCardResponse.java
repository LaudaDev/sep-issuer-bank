package app.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CheckCardResponse extends Response {
	
	private AcquirerInfo acquirerInfo;
	private IssuerInfo issuerInfo;
	private TransactionStatus transactionStatus;
	
	public CheckCardResponse() {
		super();
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
