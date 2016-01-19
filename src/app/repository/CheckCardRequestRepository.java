package app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.model.CheckCardRequest;

public interface CheckCardRequestRepository extends MongoRepository<CheckCardRequest, String>{

}
