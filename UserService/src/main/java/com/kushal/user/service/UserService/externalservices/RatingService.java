package com.kushal.user.service.UserService.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kushal.user.service.UserService.entities.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

//	GET
	@GetMapping("/ratings")
	public List<Rating> getAllRatings();
	
//	POST
	@PostMapping("/ratings")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating);
	
//	PUT
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating);
	
//	DELETE
	@DeleteMapping("/ratings/ratingId")
	public void deleteRating(@PathVariable String ratingId, Rating rating);
}
