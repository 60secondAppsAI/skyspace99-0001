package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.BaggageClaim;





public interface BaggageClaimDAO extends GenericDAO<BaggageClaim, Integer> {
  
	List<BaggageClaim> findAll();
	






}


