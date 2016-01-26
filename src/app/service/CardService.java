package app.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.CardInfo;
import app.model.CreditCard;
import app.repository.CreditCardRepository;
import app.util.Utils;

@Service
public class CardService {
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	public CreditCard addCard(CreditCard card){
		card.setId(getNextId());
		creditCardRepository.save(card);
		return card;
	}
	
	public int getNextId(){
		List<CreditCard> cards = creditCardRepository.findAll();
		int id = 0;
		for ( CreditCard c : cards ){
			if ( c.getId() > id ){
				id = c.getId();
			}
		}
		return ++id;
	}
	
	public List<CreditCard> getAllCreditCards(){
		return creditCardRepository.findAll();
	}
	
	public CreditCard getCardById(String id){
		List<CreditCard> cards = creditCardRepository.findAll();
		int idd = Integer.parseInt(id);
		for ( CreditCard c : cards ){
			if ( c.getId() == idd ){
				return c;
			}
		}
		return null;
	}
	
	public void generateDefaultCards(){		
		creditCardRepository.deleteAll();
		List<CreditCard> cards = Utils.generateDefaultCreditCards();
		for ( CreditCard c : cards){
			creditCardRepository.save(c);
		}
	}
	
	public CreditCard findCreditCard(CardInfo cardInfo){
		CreditCard card = null;
		List<CreditCard> cards = creditCardRepository.findAll();
		
		for ( CreditCard c : cards){
			if ( c.getPan().equals(cardInfo.getPan()) && c.getSecurityCode() == cardInfo.getSecurityCode() ){
				card = c;
				break;
			}
		}
		
		if ( card != null ){
			if ( checkCardExpiration(card) ){
				card = null;
			}
		}

		return card;
	}
	
	public boolean checkCardExpiration(CreditCard card){
		boolean expired = false;
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		calendar.setTime(card.getExpirationDate());
		calendar.add(Calendar.MONTH, 1);
		
		if ( !calendar.getTime().after(now) ){
			expired = true;
		}
		
		return expired;
	}
	
}
