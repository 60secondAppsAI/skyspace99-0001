package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.BoardingPass;





public interface BoardingPassDAO extends GenericDAO<BoardingPass, Integer> {
  
	List<BoardingPass> findAll();
	






}


