package app.errorhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import app.model.CheckCardErrorResponse;
import app.service.CheckCardResponseService;

@ControllerAdvice
public class BadRequestHandler {
	
	@Autowired
	private CheckCardResponseService checkCardResponseService;
	
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public CheckCardErrorResponse httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e){
		return checkCardResponseService.createErrorResponse("04");
	}
}
