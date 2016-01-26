package app.service;

import org.springframework.stereotype.Service;

import app.model.CheckCardBadRequestResponse;
import app.model.CheckCardRequest;
import app.model.CheckCardResponse;
import app.model.TransactionStatus;
import app.util.Config;

@Service
public class CheckCardResponseService {
	
	public CheckCardBadRequestResponse createBadRequestResponse(String statusCode){
		TransactionStatus status = new TransactionStatus();
		status.setCode(statusCode);
		status.setMessage(Config.statusCodes.get(statusCode));
		CheckCardBadRequestResponse response = new CheckCardBadRequestResponse();
		response.setTransactionStatus(status);
		return response;
	}
	
	public CheckCardResponse createResponse(String statusCode, CheckCardRequest request){
		CheckCardResponse response = new CheckCardResponse();
		return response;
	}


}
