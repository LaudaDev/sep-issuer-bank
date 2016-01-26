package app.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import app.model.CheckCardRequest;
import app.util.Config;

@Service
public class CheckCardRequestService {
	
	public boolean isRequestValid(CheckCardRequest request){
		
		if ( request == null ){
			return false;
		}
		
		// card info
		if ( request.getCardInfo().getPan() == null || !request.getCardInfo().getPan().matches(Config.panRegex) ){
			return false;
		}
		
		if ( request.getCardInfo().getSecurityCode() < 100 || request.getCardInfo().getSecurityCode() > 999 ) {
			return false;
		}
		
		if ( request.getCardInfo().getHolderName() == null || request.getCardInfo().getHolderName().equals("") ){
			return false;
		}
		
		if ( request.getCardInfo().getExpirationDate() == null ){
			return false;
		}
		
		// acquirer info
		if ( request.getAcquirerInfo().getOrderId() < 1 ){
			return false;
		}
		
		if ( request.getAcquirerInfo().getTimestamp() == null ){
			return false;
		}
		
		// transaction amount
		if  ( request.getTransactionAmount().compareTo(BigDecimal.valueOf(0)) != 1 ) {
			return false;
		}	
		
		return true;
	}
}
