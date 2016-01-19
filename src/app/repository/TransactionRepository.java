package app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
