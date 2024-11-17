package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Flight;





public interface FlightDAO extends GenericDAO<Flight, Integer> {
  
	List<Flight> findAll();
	






}


