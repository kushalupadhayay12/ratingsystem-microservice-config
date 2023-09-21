package com.kushal.rating.service.RatingService.services;

import java.util.List;

import com.kushal.rating.service.RatingService.entities.Rating;

public interface RatingService {
	
//	Create Rating 
	Rating create(Rating rating);

//	Get all Ratings
	List<Rating> getAllRatings();
	
//	Get all Ratings by UserId
	List<Rating> getatingByUserId(String userId);
	
//	Get All Ratings by HotelId
	List<Rating> getRatingByHotelId(String hotelId);
}
