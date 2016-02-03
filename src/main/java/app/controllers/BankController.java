package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.CheckCardRequest;
import app.model.CreditCard;
import app.model.Response;
import app.model.Transaction;
import app.service.CardService;
import app.service.CheckCardRequestService;
import app.service.CheckCardResponseService;


@RestController
@RequestMapping("/api/issuer")
public class BankController {
	
	@Autowired
	private CheckCardRequestService checkCardRequestService;
	
	@Autowired
	private CheckCardResponseService checkCardResponseService;
	
	@Autowired
	private CardService cardService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/auth")
	public Response checkCreditCard(@RequestBody CheckCardRequest request){
		
		if ( !checkCardRequestService.isRequestValid(request) ){
			return checkCardResponseService.createErrorResponse("04");
		}
		
		if ( checkCardRequestService.isRequestAllreadyProcessed(request.getAcquirerInfo()) ){
			return checkCardResponseService.createErrorResponse("04");
		}
		
		CheckCardRequest savedRequest = checkCardRequestService.addCheckCardRequest(request);
		
		CreditCard card = cardService.findCreditCard(savedRequest.getCardInfo());
		
		if ( card == null ){
			return checkCardResponseService.createErrorResponse("01");
		}
				
		if ( !cardService.canPay(savedRequest.getTransactionAmount(), card) ){
			return checkCardResponseService.createErrorResponse("02");
		}
		
		Transaction transaction = cardService.doPaying(card, savedRequest);
		
		if ( transaction == null ){
			return checkCardResponseService.createErrorResponse("05");
		}
		
		return checkCardResponseService.createResponse("00", transaction);
	}
				
}
