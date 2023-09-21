package com.kushal.hotel.service.HotelService.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class HotelStaffController {

	@GetMapping
	public ResponseEntity<List<String>> getStaffs () {
		List<String> list = Arrays.asList("John", "Taylor", "Aman", "Brad");
		return ResponseEntity.ok(list);
	}
}
