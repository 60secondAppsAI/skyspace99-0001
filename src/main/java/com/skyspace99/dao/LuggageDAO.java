package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Luggage;





public interface LuggageDAO extends GenericDAO<Luggage, Integer> {
  
	List<Luggage> findAll();
	






}


