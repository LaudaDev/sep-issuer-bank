package app.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CreditCard {
	
	@Id
	private int id;
	private String pan;
	private int securityCode;
	private String holderName;
	private String expirationDate;
	private BigDecimal amount;
	
	public CreditCard(){
		super();
	}
	
	public CreditCard(String pan, int securityCode, String holderName, String expirationDate, BigDecimal amount){
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.holderName = holderName;
		this.expirationDate = expirationDate;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getholderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
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
		return "CreditCard [id= " + id + ", pan= " + pan + ", cardHolderName= " + holderName + ", securityCode= "
				+ securityCode + ", expiryDate= " + expirationDate.toString() + ", amount= " + amount +  "]";
	}
}
