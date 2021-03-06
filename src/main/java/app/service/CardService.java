package app.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
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
	
	@Autowired
	private CustomQueriesService customQueriesService;
	
	private static final Logger logger = Logger.getLogger(CardService.class);
	
	public CreditCard addCard(CreditCard card){
		card.setId(getNextId());
		creditCardRepository.save(card);
		return card;
	}
	
	public int getNextId(){
		int id = customQueriesService.getMaxId("CreditCard");
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
				logger.error("Card (" + cardInfo.getPan() + ") expired");
				card = null;
			}
		} else {
			logger.error("Card not found based on information in cardInfo of request");
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
			logger.info("Transaction with id=" + transaction.getId() + " saved, based on request with id=" + request.getId());
			return transaction;
		} catch (Exception e){
			return null;
		}
	}
	
	public boolean canPay(BigDecimal payingAmount, CreditCard card){
			boolean can = true;
			if ( card.getAmount().compareTo(payingAmount) == -1 ){
				logger.error("Card (" + card.getPan() + ") has no enough money");
				can = false;
			}
			return can;
		}
	
}
