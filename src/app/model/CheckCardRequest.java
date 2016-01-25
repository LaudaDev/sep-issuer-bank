package app.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CheckCardRequest {
	
	@Id
	private String id;
	private CardInfo cardInfo;
	private AcquirerInfo acquirerInfo;
	private BigDecimal transactionAmount;
	
	public CheckCardRequest(){
		super();
	}

	public CheckCardRequest(CardInfo cardInfo, AcquirerInfo acquirerInfo, BigDecimal transactionAmount) {
		super();
		this.cardInfo = cardInfo;
		this.acquirerInfo = acquirerInfo;
		this.transactionAmount = transactionAmount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CardInfo getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}

	public AcquirerInfo getAcquirerInfo() {
		return acquirerInfo;
	}

	public void setAcquirerInfo(AcquirerInfo acquirerInfo) {
		this.acquirerInfo = acquirerInfo;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	@Override
	public String toString(){
		return cardInfo.getPan() + ", " + cardInfo.getSecurityCode() + ", " + cardInfo.getHolderName() + ", " + cardInfo.getExpirationDate() 
				+ " -- " + acquirerInfo.getOrderId() + ", " + acquirerInfo.getTimestamp() + " -- " + transactionAmount;
	}
	
}
