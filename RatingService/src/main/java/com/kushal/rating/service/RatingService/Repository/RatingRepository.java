package com.kushal.rating.service.RatingService.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kushal.rating.service.RatingService.entities.Rating;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String>{

//	Custom Query Methods
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
}
