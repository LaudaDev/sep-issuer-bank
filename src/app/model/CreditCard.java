package app.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CreditCard {
	
	@Id
	private String id;
	private String pan;
	private int securityCode;
	private String cardHolderName;
	private Date expiryDate;
	private BigDecimal amount;
	
	public CreditCard(){
		super();
	}
	
	public CreditCard(String pan, int securityCode, String cardHolderName, Date expiryDate, BigDecimal amount){
		super();
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
	
	// check available amount against paying amount
	public boolean canPay(BigDecimal payingAmount){
		boolean can = true;
		if ( this.amount.compareTo(payingAmount) == -1 ){
			can = false;
		}
		return can;
	}

	@Override
	public String toString(){
		return "CreditCard [id= " + id + ", pan= " + pan + ", cardHolderName= " + cardHolderName + ", securityCode= "
				+ securityCode + ", expiryDate= " + expiryDate.toString() + ", amount= " + amount +  "]";
	}
}
