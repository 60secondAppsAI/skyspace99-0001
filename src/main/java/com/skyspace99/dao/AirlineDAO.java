package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Airline;





public interface AirlineDAO extends GenericDAO<Airline, Integer> {
  
	List<Airline> findAll();
	






}


