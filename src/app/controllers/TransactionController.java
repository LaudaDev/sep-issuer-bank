package app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.Transaction;
import app.service.TransactionService;


@RestController
@RequestMapping("data/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<Transaction> getTransactions(){
		return transactionService.getAllTransactions();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Map<String, Object> deleteAllTransactions(){
		transactionService.deleteAllTransactions();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "All transactions deleted");
		return response;
	}

}
