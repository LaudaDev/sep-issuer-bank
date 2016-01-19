package app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.model.CreditCard;

public interface CreditCardRepository extends MongoRepository<CreditCard, String> {

}
