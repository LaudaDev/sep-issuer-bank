package app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.model.CreditCard;

public interface CreditCardRepository extends MongoRepository<CreditCard, String> {
	
	@Query(value="{ '_id' : ?0}")
	public CreditCard getCardById(int id);
	
	@Query(value="{ 'pan': ?0, 'securityCode': ?1, 'holderName': ?2, 'expirationDate': ?3}")
	public CreditCard getCardByPanCodeHolderNameExpiration(String pan, int code, String holderName, String expiration);
	
}
