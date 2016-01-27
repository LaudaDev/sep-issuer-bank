package app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.CreditCard;
import app.service.CardService;

@RestController
@RequestMapping("api/issuer/data/creditcards")
public class CreditCardController {
	
	@Autowired
	private CardService cardService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addCreditCard(@RequestBody CreditCard card){
		
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		if (card != null){
			cardService.addCard(card);
			response.put("message", "Credit card added");
			response.put("creditCard", card);
		} else {
			response.put("message", "Credit card not added. Invalid data");
		}
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/reset")
	public Map<String, Object> resetData(){
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		cardService.generateDefaultCards();		
		response.put("message", "Credit cards data reseted");
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{cardId}")
	public CreditCard getCardById(@PathVariable("cardId") String cardId){
		return cardService.getCardById(cardId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<CreditCard> getAllCards(){
		return cardService.getAllCreditCards();
	}	
}