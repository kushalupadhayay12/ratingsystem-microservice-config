package com.kushal.user.service.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kushal.user.service.UserService.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
	
}
