package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Promotion;





public interface PromotionDAO extends GenericDAO<Promotion, Integer> {
  
	List<Promotion> findAll();
	






}


