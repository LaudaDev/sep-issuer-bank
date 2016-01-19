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
import app.repository.CreditCardRepository;
import app.util.Utils;

@RestController
@RequestMapping("data/creditcards")
public class CreditCardController {
	
	@Autowired
	private CreditCardRepository cardRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addCreditCard(@RequestBody CreditCard card){
		
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		if (card != null){
			cardRepository.save(card);
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
		cardRepository.deleteAll();
		
		List<CreditCard> cards = Utils.generateDefaultCreditCards();
		for ( CreditCard c : cards){
			cardRepository.save(c);
		}
		
		response.put("message", "Credit cards data reseted");
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{cardId}")
	public CreditCard getCardById(@PathVariable("cardId") String cardId){
		return cardRepository.findOne(cardId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<CreditCard> getAllCards(){
		return cardRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Map<String, Object> deleteAllCards(){
		cardRepository.deleteAll();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "All credit card deleted");
		return response;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{cardId}")
	public Map<String, Object> deleteCardById(@PathVariable("cardId") String cardId){
		CreditCard card = cardRepository.findOne(cardId);
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		
		if ( card != null){
			cardRepository.delete(cardId);
			response.put("message", "Credit card with id " + cardId + " deleted");
		} else {
			response.put("message", "Credit card with id " + cardId + " not found");
		}
		
		return response;
	}
	
}
