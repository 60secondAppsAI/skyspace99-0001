package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Aircraft;





public interface AircraftDAO extends GenericDAO<Aircraft, Integer> {
  
	List<Aircraft> findAll();
	






}


