package app.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CheckCardRequest {
	
	@Id
	private String id;
	private String acquirerOrderId;
	private Date acquirerTimestamp;
	private String pan;
	private int securityCode;
	private String cardHolderName;
	private Date expiryDate;
	private BigDecimal amount;
	
	public CheckCardRequest(){
		super();
	}

	public CheckCardRequest(String acquirerOrderId, Date acquirerTimestamp, String pan, int securityCode, String cardHolderName, Date expiryDate,
			BigDecimal amount) {
		super();
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.expiryDate = expiryDate;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(String acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}
	
	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString(){
		return "CheckCardRequest [id= " + id + ", acquirerOrderId= " + acquirerOrderId + ", acquirerTimestamp= " + acquirerTimestamp + ", pan= " + pan + ", cardHolderName= " + cardHolderName + ", securityCode= "
				+ securityCode + ", expiryDate= " + expiryDate + ", amount= " + amount +  "]";
	}
}
