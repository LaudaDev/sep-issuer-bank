package app.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import app.util.Config;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class CardInfo {

	private String pan;
	private int securityCode;
	private String holderName;
	
	@JsonFormat(pattern = Config.cardExpirationDateFormat, timezone="CET")
	private Date expirationDate;
	
	public CardInfo(){
		super();
	}

	public CardInfo(String pan, int securityCode, String holderName, Date expirationDate) {
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

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}	
}
