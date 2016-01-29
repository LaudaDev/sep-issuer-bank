package app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.CardInfo;
import app.model.CheckCardRequest;
import app.model.CreditCard;
import app.model.Transaction;
import app.repository.CreditCardRepository;
import app.util.Config;
import app.util.Utils;

@Service
public class CardService {
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private TransactionService transactionService;
	
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
		CreditCard card = creditCardRepository.getCardById(Integer.parseInt(id));
		if ( card != null ){
			return card;
		} else {
			return null;
		}
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
		card = creditCardRepository.getCardByPanCodeHolderNameExpiration(cardInfo.getPan(), cardInfo.getSecurityCode(), cardInfo.getHolderName(), cardInfo.getExpirationDate());
		
		if ( card != null ){
			if ( isCardExpired(card) ){
				card = null;
			}
		}

		return card;
	}
	
	public boolean isCardExpired(CreditCard card){
		boolean expired = false;
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(Config.cardExpirationDateFormat);
		Date expirationDate = null;
		
		try {
			expirationDate = dateFormat.parse(card.getExpirationDate());
		} catch (ParseException e) {
			return true;
		}
				
		Calendar calendarExpiration = Calendar.getInstance();
		calendarExpiration.setTime(expirationDate);
		calendarExpiration.add(Calendar.MONTH, 1);
				
		if ( !calendarExpiration.getTime().after(now) ){
			expired = true;
		}
		
		return expired;
	}
	
	public Transaction doPaying(CreditCard card, CheckCardRequest request){
		
		try {
			Transaction transaction = new Transaction(new Date(), request);
			transaction = transactionService.addTransaction(transaction);
			card.setAmount(card.getAmount().subtract(request.getTransactionAmount()));
			creditCardRepository.save(card);
			return transaction;
		} catch (Exception e){
			return null;
		}
	}
	
}
