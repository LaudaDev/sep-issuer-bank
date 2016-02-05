package app.service;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import app.model.CheckCardRequest;
import app.model.CreditCard;
import app.model.Transaction;
import app.util.SpringMongoConfig;

@Service
public class CustomQueriesService {
	
	private ApplicationContext ctx = null;
	private MongoOperations mongoOperacations = null;
	
	private void openMongo(){
		
		if ( ctx == null ){
			ctx =  new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			mongoOperacations = (MongoOperations) ctx.getBean("mongoTemplate");
		}	
	}
	
	public int getMaxId(String className){
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "_id"));
		query.limit(1);
		query.fields().include("_id");
		
		openMongo();
		int id = 0;
		
		switch ( className ){
			case "CreditCard": {
				List<CreditCard> cards = mongoOperacations.find(query, CreditCard.class);
				if ( cards.size() == 1 ){
					id = cards.get(0).getId();
				}
				break;
			}
			case "CheckCardRequest": {
				List<CheckCardRequest> requests = mongoOperacations.find(query, CheckCardRequest.class);
				if ( requests.size() == 1 ){
					id = requests.get(0).getId();
				}
				break;
			}
			case "Transaction": {
				List<Transaction> transactions = mongoOperacations.find(query, Transaction.class);
				if ( transactions.size() == 1 ){
					id = transactions.get(0).getId();
				}
				break;
			}
		}
		
		return id;
	}
	
	
}
