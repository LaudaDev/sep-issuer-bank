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
			return checkCardResponseService.createErrorResponse("04");
		}
		
		CreditCard card = cardService.findCreditCard(request.getCardInfo());
		
		if ( card == null ){
			System.out.println("invalid card");
			return checkCardResponseService.createErrorResponse("01");
		}
		
		// TODO check if request with same timestamp already exists
		
		CheckCardRequest savedRequest = checkCardRequestService.addCheckCardRequest(request);
		
		if ( !card.canPay(savedRequest.getTransactionAmount()) ) {
			System.out.println("not enough money");
			return checkCardResponseService.createErrorResponse("02");
		}
		
		Transaction transaction = cardService.doPaying(card, savedRequest);
		
		if ( transaction == null ){
			System.out.println("error");
			return checkCardResponseService.createErrorResponse("05");
		}
		
		
		
		System.out.println("valid");
		return checkCardResponseService.createResponse("00", transaction);
	}
				
}
