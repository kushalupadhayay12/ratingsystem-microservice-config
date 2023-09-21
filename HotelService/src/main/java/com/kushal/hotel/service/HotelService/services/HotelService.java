package com.kushal.hotel.service.HotelService.services;

import java.util.List;

import com.kushal.hotel.service.HotelService.entities.Hotel;

public interface HotelService {

//	Create 
	Hotel  create(Hotel hotel);
	
//	Get All the Hotels
	List<Hotel> getAll();
	
//	Get Single Hotel
	Hotel get(String id);
}
