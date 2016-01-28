package app.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.model.CheckCardRequest;

public interface CheckCardRequestRepository extends MongoRepository<CheckCardRequest, String>{
	
	@Query(value="{ 'acquirerInfo.orderId' : ?0, 'acquirerInfo.timestamp': ?1}")
	public CheckCardRequest findRequestByAcquirerInfo(int orderId, Date timestamp);
}
