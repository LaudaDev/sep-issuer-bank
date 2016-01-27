package app.model;

public class CheckCardErrorResponse extends Response {
	
	private TransactionStatus transactionStatus;
	
	public CheckCardErrorResponse() {
		super();
	}
	
	public CheckCardErrorResponse(TransactionStatus transactionStatus) {
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
