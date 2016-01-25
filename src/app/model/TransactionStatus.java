package app.model;

public class TransactionStatus {
	
	private int code;
	private String message;
	
	public TransactionStatus(){
		super();
	}

	public TransactionStatus(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
