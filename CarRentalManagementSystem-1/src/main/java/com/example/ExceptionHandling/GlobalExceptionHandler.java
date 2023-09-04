package com.example.ExceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpStatus status,
			HttpHeaders headers, WebRequest request) {
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldname, message);

		});

		return new ResponseEntity<Object>(map, status.BAD_REQUEST);

	}

	@ExceptionHandler(UsernameNotFoundException.class)

	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get User");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());

	}

	@ExceptionHandler(CarNotFoundException.class)

	public ResponseEntity<Object> handleEmployeeNotFound(CarNotFoundException ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get Car");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());

	}

	@ExceptionHandler(CustomerNotFoundException.class)

	public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get Customer");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());
	}
	@ExceptionHandler(RentalBookingNotFoundException.class)
	public ResponseEntity<Object> handleRentalBookingNotFound(RentalBookingNotFoundException ex){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get RentalBooking");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());

	}
}
