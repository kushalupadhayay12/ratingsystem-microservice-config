package com.kushal.user.service.UserService.services;

import java.util.List;

import com.kushal.user.service.UserService.entities.User;

public interface UserService {

//	create new user
	User saveUser(User user);
	
//	get all users
	List<User> getAllUser();
	
//	get single user of given userId
	User getUser(String userId);
}
