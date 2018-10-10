/**
 * 
 *//*
package com.alt.shop.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alt.shop.exception.UserInfoException;

*//**
 * @author Syedyasiraswath
 *
 *//*
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ UserInfoException.class })
	protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, "Error in the UserInfo!", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ConstraintViolationException.class,
			DataIntegrityViolationException.class })
	public ModelAndView handleBadRequest(Exception ex, WebRequest request) {
		ModelAndView model = new ModelAndView();
		if(((ServletWebRequest)request).getRequest().getRequestURL().toString().contains("register")) {
			model.setViewName("/register");
			model.addObject("errorMessage", ex.getMessage());
		}
		return model;
	}
}
*/