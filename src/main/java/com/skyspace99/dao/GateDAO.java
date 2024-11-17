package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Gate;





public interface GateDAO extends GenericDAO<Gate, Integer> {
  
	List<Gate> findAll();
	






}


