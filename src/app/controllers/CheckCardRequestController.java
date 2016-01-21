package app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.CheckCardRequest;
import app.repository.CheckCardRequestRepository;

@RestController
@RequestMapping("data/checkcreditcardrequests")
public class CheckCardRequestController {
	
	@Autowired
	private CheckCardRequestRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CheckCardRequest getCheckCardRequestById(@PathVariable("id") String id){
		return repository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<CheckCardRequest> getAllCheckCardRequests(){
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Map<String, Object> deleteAllCheckCardRequests(){
		repository.deleteAll();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "All checkCreditCardRequest deleted");
		return response;
	}
}
