package app.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class CardInfo {
	
	private String pan;
	private int securityCode;
	private String holderName;
	
	@DateTimeFormat(pattern = "MM/yy")
	private String expirationDate;
	
	public CardInfo(){
		super();
	}

	public CardInfo(String pan, int securityCode, String holderName, String expirationDate) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.holderName = holderName;
		this.expirationDate = expirationDate;
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

	public String getHolderName() {
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
}
