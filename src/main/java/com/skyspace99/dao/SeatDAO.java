package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Seat;





public interface SeatDAO extends GenericDAO<Seat, Integer> {
  
	List<Seat> findAll();
	






}


