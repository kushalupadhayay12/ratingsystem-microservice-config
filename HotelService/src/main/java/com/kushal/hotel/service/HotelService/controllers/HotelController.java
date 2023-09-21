package com.kushal.hotel.service.HotelService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kushal.hotel.service.HotelService.entities.Hotel;
import com.kushal.hotel.service.HotelService.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;

//	Create 
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}
	
//	Get Single Hotel
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
	}
	
//	Get All Hotels
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels() {
		return ResponseEntity.ok(hotelService.getAll());
	}
}
