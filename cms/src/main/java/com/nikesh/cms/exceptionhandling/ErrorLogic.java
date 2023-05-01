package com.nikesh.cms.exceptionhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ValidationException;

@ControllerAdvice
public class ErrorLogic extends ResponseEntityExceptionHandler {

	
	/*this is used to handle customer not found exception
	 * the returned messag ewill be in format
	 *"timestamp": 
      "message":
      "details":
	*/
	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> handleCustomerNotFoundException(Exception e,WebRequest request) throws Exception{
		
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(err,HttpStatus.NOT_FOUND);
		
	}
	
	
	/*this can be used to return message in  format
	 * "timestamp":
	 * "message":
	 * "details":
	@ExceptionHandler(ValidationException.class)
	 public ResponseEntity<Object> handleValidationException(ValidationException ex,WebRequest request) {
		ErrorDetails err = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(err,HttpStatus.BAD_REQUEST);
    }
	*/
	
	//this is used to send just the Validation Failed Message
	@ExceptionHandler(ValidationException.class)
	 public ResponseEntity<Object> handleValidationException(ValidationException e) {
		ErrorDetails err = new ErrorDetails(e.getMessage());
		return new ResponseEntity<Object>(err.getMessage(),HttpStatus.BAD_REQUEST);
   }
	

}
