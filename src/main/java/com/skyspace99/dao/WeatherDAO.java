package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Weather;





public interface WeatherDAO extends GenericDAO<Weather, Integer> {
  
	List<Weather> findAll();
	






}


