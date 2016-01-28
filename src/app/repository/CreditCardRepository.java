package app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.model.CreditCard;

public interface CreditCardRepository extends MongoRepository<CreditCard, String> {
	
	@Query(value="{ '_id' : ?0}")
	public CreditCard getCardById(int id);
	
	@Query(value="{ 'pan': ?0, 'securityCode': ?1, 'expirationDate': ?2}")
	public CreditCard getCardByPanCodeExpiration(String pan, int code, String expiration);
}
