package app.service;

import org.springframework.stereotype.Service;

import app.model.CheckCardErrorResponse;
import app.model.CheckCardResponse;
import app.model.IssuerInfo;
import app.model.Transaction;
import app.model.TransactionStatus;
import app.util.Config;

@Service
public class CheckCardResponseService {
	
	public CheckCardErrorResponse createErrorResponse(String statusCode){
		TransactionStatus status = new TransactionStatus();
		status.setCode(statusCode);
		status.setMessage(Config.statusCodes.get(statusCode));
		CheckCardErrorResponse response = new CheckCardErrorResponse();
		response.setTransactionStatus(status);
		return response;
	}
	
	public CheckCardResponse createResponse(String statusCode, Transaction transaction){
		CheckCardResponse response = new CheckCardResponse();
		response.setAcquirerInfo(transaction.getRequest().getAcquirerInfo());
		
		IssuerInfo issuerInfo = new IssuerInfo();
		issuerInfo.setOrderId(transaction.getId());
		issuerInfo.setTimestamp(transaction.getTimestamp());
		response.setIssuerInfo(issuerInfo);
		
		TransactionStatus status = new TransactionStatus();
		status.setCode(statusCode);
		status.setMessage(Config.statusCodes.get(statusCode));
		
		response.setTransactionStatus(status);
		
		return response;
	}


}
