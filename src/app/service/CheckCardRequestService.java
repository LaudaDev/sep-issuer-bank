package app.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.CheckCardRequest;
import app.repository.CheckCardRequestRepository;
import app.util.Config;

@Service
public class CheckCardRequestService {
	
	@Autowired
	private CheckCardRequestRepository checkCardRequestRepository;
	
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
		
		if ( request.getCardInfo().getExpirationDate() == null || !isExpirationDateValid(request.getCardInfo().getExpirationDate()) ){
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
		if  ( request.getTransactionAmount() == null || request.getTransactionAmount().compareTo(BigDecimal.valueOf(0)) != 1 ) {
			return false;
		}	
		
		return true;
	}
	
	public boolean isExpirationDateValid(String expirationDate){
		
		if ( !expirationDate.matches(Config.cardExpirationDateRegex) ) {
			return false;
		}
		
		String[] split = expirationDate.split("/");
		int month = Integer.parseInt(split[0]);
		int year = Integer.parseInt(split[1]);
		
		if ( month < 1 || month > 12 || year < 0 || year > 99 ) {
			return false;
		}
		
		return true;
	}
	
	public CheckCardRequest addCheckCardRequest(CheckCardRequest request){
		request.setId(getNextId());
		checkCardRequestRepository.save(request);
		return request;
	}
	
	public int getNextId(){
		List<CheckCardRequest> requests = checkCardRequestRepository.findAll();
		int id = 0;
		for ( CheckCardRequest r : requests ){
			if ( r.getId() > id ){
				id = r.getId();
			}
		}
		return ++id;
	}
		
	public List<CheckCardRequest> getAllCheckCardRequests(){
		return checkCardRequestRepository.findAll();
	}
	
	public void deleteAllCheckCardRequests(){
		checkCardRequestRepository.deleteAll();
	}
	
}
