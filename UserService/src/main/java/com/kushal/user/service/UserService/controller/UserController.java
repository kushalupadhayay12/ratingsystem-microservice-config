package com.kushal.user.service.UserService.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kushal.user.service.UserService.entities.User;
import com.kushal.user.service.UserService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);

//	Create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
//	Get Single User
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		User user = userService.getUser(userId);
		return ResponseEntity.ok().body(user);
	}
	
//	FallBack Method for Circuit Breaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception exception) {
		exception.printStackTrace();
		logger.info("Fallback method is called because some service is down", exception.getMessage());
		User user = User.builder()
						.email("dummy@gmail.com")
						.name("Dave")
						.about("This user is created dummy because some service is down")
						.userId("141234")
						.build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
//	Get All Users
	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUsers = userService.getAllUser();
		return ResponseEntity.ok().body(allUsers);
	}
}
