package com.kivimango.draftsman.backend.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.kivimango.draftsman.backend.exception.DraftNotFoundException;

/**
 * Handling errors thrown by the controllers
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@ControllerAdvice
public final class ErrorController extends ResponseEntityExceptionHandler{
	
	/**
	 * Shows a json response with an errorMessage property
	 * @param ex
	 * @return
	 */
	
	@ExceptionHandler({DraftNotFoundException.class, FileNotFoundException.class})
	ResponseEntity<Map<String, String>> show404(Exception ex) {
		Map<String, String> errors = new HashMap<String, String>();
		errors.put("errorMessage", ex.getMessage());
		return new ResponseEntity<Map<String,String>>(errors, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({IllegalArgumentException.class})
	ResponseEntity<Map<String, String>> showParamTooShortMsg(Exception ex) {
		Map<String, String> errors = new HashMap<String, String>();
		errors.put("errorMessage", ex.getMessage());
		return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);
	}
	
}
