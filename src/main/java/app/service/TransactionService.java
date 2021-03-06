package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Transaction;
import app.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private CustomQueriesService customQueriesService;
	
	public Transaction addTransaction(Transaction transaction){
		transaction.setId(getNextId());
		transactionRepository.save(transaction);
		return transaction;
	}
	
	public int getNextId(){
		int id = customQueriesService.getMaxId("Transaction");
		return ++id;
	}
	
	public List<Transaction> getAllTransactions(){
		return transactionRepository.findAll();
	}
	
	public void deleteAllTransactions(){
		transactionRepository.deleteAll();
	}

}
