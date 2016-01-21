package app.controllers;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.CheckCardRequest;
import app.model.CreditCard;
import app.model.Transaction;
import app.repository.CheckCardRequestRepository;
import app.repository.CreditCardRepository;
import app.repository.TransactionRepository;


@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	private CheckCardRequestRepository checkCardRequestRepository;
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/checkcard")
	public Map<String, Object> checkCreditCard(@RequestBody CheckCardRequest request){
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		
		// TODO check if request with same timestamp already exists
		if (request != null){ 
			checkCardRequestRepository.save(request);
			CreditCard card = findCreditCard(request);
			
			if ( card != null && !checkCardExpiration(card) ){
				if ( card.canPay(request.getAmount()) ){
					Transaction transaction = doPaying(card, request);
					response.put("message", "Credit card is valid. Paying done");
					response.put("acquirerOrderId", request.getAcquirerOrderId());
					response.put("acquirerTimestamp", request.getAcquirerTimestamp());
					response.put("issuerOrderId", transaction.getId());
					response.put("issuerTimestamp", transaction.getTimestamp());
				} else {
					response.put("message", "Not enough money for paying");
				}
			} else {
				response.put("message", "Credit card not found or expired");
			}
			
		} else {
			response.put("message", "Invalid data for checking credit card");
		}
		return response;
	}
	
	public CreditCard findCreditCard(CheckCardRequest request){
		CreditCard card = null;
		List<CreditCard> cards = creditCardRepository.findAll();
		
		for ( CreditCard c : cards){
			if ( c.getPan().equals(request.getPan()) && c.getSecurityCode() == request.getSecurityCode() &&
					c.getCardHolderName().equals(request.getCardHolderName()) && c.getExpiryDate().equals(request.getExpiryDate()) ){
				card = c;
				break;
			}
		}
		return card;
	}
	
	public boolean checkCardExpiration(CreditCard card){
		boolean expired = false;
		Date now = new Date();	
		
		if ( !card.getExpiryDate().after(now) ){
			expired = true;
		}
		
		return expired;
	}
	
	public Transaction doPaying(CreditCard card, CheckCardRequest request){
	
		Transaction transaction = new Transaction(new Date(), request);
		transactionRepository.save(transaction);
		card.setAmount(card.getAmount().subtract(request.getAmount()));
		creditCardRepository.save(card);		
		return transaction;
	}
	
	

	
}
