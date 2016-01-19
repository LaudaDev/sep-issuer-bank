package app.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import app.model.CreditCard;

public class Utils {
		
	public static List<CreditCard> generateDefaultCreditCards(){
		
		int noOfCards = 9;
		int panNumbers = 16;
		List<CreditCard> cards = new ArrayList<CreditCard>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Random random = new Random();
		
		try {		
			for ( int i = 1; i < noOfCards+1; i++ ){
				
				StringBuilder pan = new StringBuilder("");
				for ( int j = 0; j < panNumbers; j++ ){
					pan.append(i);
				}
				
				int securityCode = Integer.parseInt((pan.toString().substring(0, 3)));
				
				String cardHolderName = "Card " + i;
				Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
				calendar.add(Calendar.DAY_OF_YEAR, (random.nextInt() % 1825) - 365);
				calendar.set(Calendar.MILLISECOND, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				BigDecimal amount = new BigDecimal(random.nextDouble() * 950000 + 50000).setScale(2, RoundingMode.CEILING);
 			
				CreditCard card = new CreditCard(pan.toString(), securityCode, cardHolderName, calendar.getTime(), amount);
				cards.add(card);
			}
		
		} catch ( Exception e){
			e.printStackTrace();
		}
		
		return cards;
	}
}
