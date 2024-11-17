package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Meal;





public interface MealDAO extends GenericDAO<Meal, Integer> {
  
	List<Meal> findAll();
	






}


