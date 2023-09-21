package com.kushal.hotel.service.HotelService.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException() {
		super("Resource Not Found !!");
	}
}
