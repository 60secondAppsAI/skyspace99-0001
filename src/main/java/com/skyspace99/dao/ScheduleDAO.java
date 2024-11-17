package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Schedule;





public interface ScheduleDAO extends GenericDAO<Schedule, Integer> {
  
	List<Schedule> findAll();
	






}


