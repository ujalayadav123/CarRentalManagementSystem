package com.example.ExceptionHandling;

public class CustomerNotFoundException extends Exception{
	public CustomerNotFoundException(String message) {
		super(message);

	}
}
