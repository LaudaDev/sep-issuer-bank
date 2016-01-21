package app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.Transaction;
import app.repository.TransactionRepository;


@RestController
@RequestMapping("data/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Transaction getTransactionById(@PathVariable("id") String id){
		return transactionRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<Transaction> getTransactions(){
		return transactionRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Map<String, Object> deleteAllTransactions(){
		transactionRepository.deleteAll();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "All transactions deleted");
		return response;
	}

}
