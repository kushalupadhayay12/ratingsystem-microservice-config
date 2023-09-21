package com.kushal.user.service.UserService.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kushal.user.service.UserService.entities.Hotel;

@Service
@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/hotels/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
