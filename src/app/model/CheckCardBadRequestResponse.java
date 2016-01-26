package app.model;

public class CheckCardBadRequestResponse extends Response {
	
	private TransactionStatus transactionStatus;
	
	public CheckCardBadRequestResponse() {
		super();
	}
	
	public CheckCardBadRequestResponse(TransactionStatus transactionStatus) {
		super();
		this.transactionStatus = transactionStatus;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
		
}
