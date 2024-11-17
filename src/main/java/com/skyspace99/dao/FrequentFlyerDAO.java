package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.FrequentFlyer;





public interface FrequentFlyerDAO extends GenericDAO<FrequentFlyer, Integer> {
  
	List<FrequentFlyer> findAll();
	






}


