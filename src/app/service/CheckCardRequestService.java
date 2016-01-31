package app.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.AcquirerInfo;
import app.model.CheckCardRequest;
import app.repository.CheckCardRequestRepository;
import app.util.Config;

@Service
public class CheckCardRequestService {
	
	@Autowired
	private CheckCardRequestRepository checkCardRequestRepository;
	
	@Autowired
	private CustomQueriesService customQueriesService;
	
	private static final Logger logger = Logger.getLogger(CheckCardRequestService.class);
	
	public boolean isRequestValid(CheckCardRequest request){
		
		logger.info("Processing new request");
		if ( request == null ){
			logger.error("Request is null");
			return false;
		}
		
		// card info
		if ( request.getCardInfo().getPan() == null || !request.getCardInfo().getPan().matches(Config.panRegex) ){
			logger.error("Pan is invalid");
			return false;
		}
		
		if ( !request.getCardInfo().getPan().startsWith(Config.issuerBankCode) ) {
			logger.error("Pan is not from issuer bank");
			return false;
		}
		
		if ( request.getCardInfo().getSecurityCode() < 100 || request.getCardInfo().getSecurityCode() > 999 ) {
			logger.error("Security code is invalid");
			return false;
		}
		
		if ( request.getCardInfo().getHolderName() == null || request.getCardInfo().getHolderName().equals("") ){
			logger.error("Card holder name is invalid");
			return false;
		}
		
		if ( request.getCardInfo().getExpirationDate() == null || !isExpirationDateValid(request.getCardInfo().getExpirationDate()) ){
			logger.error("Expiration date is invalid");
			return false;
		}
		
		// acquirer info
		if ( request.getAcquirerInfo().getOrderId() < 1 ){
			logger.error("Acquirer's orderId is invalid");
			return false;
		}
		
		if ( request.getAcquirerInfo().getTimestamp() == null ){
			logger.error("Acquirer's timestamp is invalid");
			return false;
		}
		
		// transaction amount
		if  ( request.getTransactionAmount() == null || request.getTransactionAmount().compareTo(BigDecimal.valueOf(0)) != 1 ) {
			logger.error("Transaction amount is invalid");
			return false;
		}
		
		logger.info("Request is valid");		
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
	
	public boolean isRequestAllreadyProcessed(AcquirerInfo acquirerInfo){
		CheckCardRequest request = checkCardRequestRepository.findRequestByAcquirerInfo(acquirerInfo.getOrderId(), acquirerInfo.getTimestamp());
		if ( request != null ){
			
			SimpleDateFormat format = new SimpleDateFormat(Config.timestampFormat);
			format.setTimeZone(TimeZone.getTimeZone("CET"));
			logger.error("Request with acquirer's orderdId=" + acquirerInfo.getOrderId() + " and timestamp=" +
						format.format(acquirerInfo.getTimestamp()) + " has already been processed");
			return true;
		} else {
			return false;
		}
	}
	
	public CheckCardRequest addCheckCardRequest(CheckCardRequest request){
		request.setId(getNextId());
		checkCardRequestRepository.save(request);
		logger.info("Request with id=" + request.getId() + " saved");
		return request;
	}
	
	public int getNextId(){
		int id = customQueriesService.getMaxId("CheckCardRequest");
		return ++id;
	}
		
	public List<CheckCardRequest> getAllCheckCardRequests(){
		return checkCardRequestRepository.findAll();
	}
	
	public void deleteAllCheckCardRequests(){
		checkCardRequestRepository.deleteAll();
	}
	
}
