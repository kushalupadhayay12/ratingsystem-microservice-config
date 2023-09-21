package com.kushal.rating.service.RatingService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kushal.rating.service.RatingService.Repository.RatingRepository;
import com.kushal.rating.service.RatingService.entities.Rating;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating create(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}
}
