package com.kushal.rating.service.RatingService.controllers;

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

import com.kushal.rating.service.RatingService.entities.Rating;
import com.kushal.rating.service.RatingService.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
//	CREATE RATING
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
	
//	GET ALL RATINGS
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings() {
		return ResponseEntity.ok(ratingService.getAllRatings());
	}
	
//	GET RATINGS BY USER ID
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(ratingService.getatingByUserId(userId));
	}
	
//	GET RATINGS BY HOTEL ID
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
		return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
	}
}
