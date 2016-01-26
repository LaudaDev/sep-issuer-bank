package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.CheckCardRequest;
import app.model.CreditCard;
import app.model.Response;
import app.service.CardService;
import app.service.CheckCardRequestService;
import app.service.CheckCardResponseService;


@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	private CheckCardRequestService checkCardRequestService;
	
	@Autowired
	private CheckCardResponseService checkCardResponseService;
	
	@Autowired
	private CardService cardService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/checkcard")
	public Response checkCreditCard(@RequestBody CheckCardRequest request){
		
		System.out.println(request.toString());
		
		if ( !checkCardRequestService.isRequestValid(request) ){
			System.out.println("invalid");
			return checkCardResponseService.createBadRequestResponse("04");
		}
		
		CreditCard card = cardService.findCreditCard(request.getCardInfo());
		
		if ( card == null ){
			System.out.println("invalid card");
			return checkCardResponseService.createBadRequestResponse("01");
		}
		
		// TODO check if request with same timestamp already exists
		/*if (request != null){ 
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
		}*/
		System.out.println("valid");
		return checkCardResponseService.createResponse("00", request);
	}
	
	/*
	
	
	
	public Transaction doPaying(CreditCard card, CheckCardRequest request){
	
		Transaction transaction = new Transaction(new Date(), request);
		transactionRepository.save(transaction);
		card.setAmount(card.getAmount().subtract(request.getAmount()));
		creditCardRepository.save(card);		
		return transaction;
	}*/
			
}
