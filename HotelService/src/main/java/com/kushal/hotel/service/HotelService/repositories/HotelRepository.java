package com.kushal.hotel.service.HotelService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kushal.hotel.service.HotelService.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String>{
}
