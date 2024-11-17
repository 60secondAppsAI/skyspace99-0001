package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Crew;





public interface CrewDAO extends GenericDAO<Crew, Integer> {
  
	List<Crew> findAll();
	






}


