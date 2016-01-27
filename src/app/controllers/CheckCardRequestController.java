package app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.CheckCardRequest;
import app.service.CheckCardRequestService;

@RestController
@RequestMapping("api/issuer/data/checkcreditcardrequests")
public class CheckCardRequestController {
	
	@Autowired
	private CheckCardRequestService checkCardRequestService;
		
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<CheckCardRequest> getAllCheckCardRequests(){
		return checkCardRequestService.getAllCheckCardRequests();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Map<String, Object> deleteAllCheckCardRequests(){
		checkCardRequestService.deleteAllCheckCardRequests();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "All checkCreditCardRequest deleted");
		return response;
	}
}
