package com.skyspace99.dao;

import java.util.List;

import com.skyspace99.dao.GenericDAO;
import com.skyspace99.domain.Terminal;





public interface TerminalDAO extends GenericDAO<Terminal, Integer> {
  
	List<Terminal> findAll();
	






}


