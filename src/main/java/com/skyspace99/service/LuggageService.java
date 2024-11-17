package com.skyspace99.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace99.domain.Luggage;
import com.skyspace99.dto.LuggageDTO;
import com.skyspace99.dto.LuggageSearchDTO;
import com.skyspace99.dto.LuggagePageDTO;
import com.skyspace99.dto.LuggageConvertCriteriaDTO;
import com.skyspace99.service.GenericService;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LuggageService extends GenericService<Luggage, Integer> {

	List<Luggage> findAll();

	ResultDTO addLuggage(LuggageDTO luggageDTO, RequestDTO requestDTO);

	ResultDTO updateLuggage(LuggageDTO luggageDTO, RequestDTO requestDTO);

    Page<Luggage> getAllLuggages(Pageable pageable);

    Page<Luggage> getAllLuggages(Specification<Luggage> spec, Pageable pageable);

	ResponseEntity<LuggagePageDTO> getLuggages(LuggageSearchDTO luggageSearchDTO);
	
	List<LuggageDTO> convertLuggagesToLuggageDTOs(List<Luggage> luggages, LuggageConvertCriteriaDTO convertCriteria);

	LuggageDTO getLuggageDTOById(Integer luggageId);







}





