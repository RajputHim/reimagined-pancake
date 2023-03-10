package com.GorgeousGlam.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), "Validation Error",
				me.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> OrderExceptionHandler(OrderException oe, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();

		err.setTimestamp(LocalDateTime.now());
		err.setMessage(oe.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AddressException.class)
	public ResponseEntity<MyErrorDetails> AddressExceptionHandler(AddressException ae, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();

		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ae.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ShipmentException.class)
	public ResponseEntity<MyErrorDetails> ShipmentExceptionHandler(ShipmentException se, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();

		err.setTimestamp(LocalDateTime.now());
		err.setMessage(se.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(SessionException.class)
	public ResponseEntity<MyErrorDetails> SessionExceptionHandler(SessionException se, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();

		err.setTimestamp(LocalDateTime.now());
		err.setMessage(se.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> AdminExceptionHandler(AdminException ae, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();

		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ae.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<MyErrorDetails> ProductNotFoundExceptionHandler(ProductNotFoundException pe, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();

		err.setTimestamp(LocalDateTime.now());
		err.setMessage(pe.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> myNoHandlerFoundException(NoHandlerFoundException ne, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();

		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ne.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> genericExceptionHandler(Exception ge, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();

		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ge.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

}
