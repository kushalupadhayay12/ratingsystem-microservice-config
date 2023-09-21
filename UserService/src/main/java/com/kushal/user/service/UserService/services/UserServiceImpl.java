package com.kushal.user.service.UserService.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kushal.user.service.UserService.entities.Hotel;
import com.kushal.user.service.UserService.entities.Rating;
import com.kushal.user.service.UserService.entities.User;
import com.kushal.user.service.UserService.exceptions.ResourceNotFoundException;
import com.kushal.user.service.UserService.externalservices.HotelService;
import com.kushal.user.service.UserService.repositories.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

//	CREATE USER
	@Override
	public User saveUser(User user) {
//		For generating unique user Id
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

//	GET ALL USERS
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

//	GET SINGLE USER
	@Override
	public User getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server ! " + userId));
		
//		Fetching ratings of the user from the RATING-SERIVCE
		Rating [] userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		
//		CONVERTING ARRAY INTO LIST TO USE STREAMS
		List<Rating> ratings = Arrays.stream(userRatings).collect(Collectors.toList());
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			
//			CALL HOTEL-SERVICE API 
//			ResponseEntity<Hotel> hotelInfo = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//			Hotel hotel = hotelInfo.getBody();
//			logger.info("Response status code: {} ", hotelInfo.getStatusCode());
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
//			SET THE HOTEL INFO IN RATING 
			rating.setHotel(hotel);
			
//			RETURN RATING
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
	}

}
