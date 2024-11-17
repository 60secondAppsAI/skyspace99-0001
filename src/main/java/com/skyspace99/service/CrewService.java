package com.skyspace99.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace99.domain.Crew;
import com.skyspace99.dto.CrewDTO;
import com.skyspace99.dto.CrewSearchDTO;
import com.skyspace99.dto.CrewPageDTO;
import com.skyspace99.dto.CrewConvertCriteriaDTO;
import com.skyspace99.service.GenericService;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CrewService extends GenericService<Crew, Integer> {

	List<Crew> findAll();

	ResultDTO addCrew(CrewDTO crewDTO, RequestDTO requestDTO);

	ResultDTO updateCrew(CrewDTO crewDTO, RequestDTO requestDTO);

    Page<Crew> getAllCrews(Pageable pageable);

    Page<Crew> getAllCrews(Specification<Crew> spec, Pageable pageable);

	ResponseEntity<CrewPageDTO> getCrews(CrewSearchDTO crewSearchDTO);
	
	List<CrewDTO> convertCrewsToCrewDTOs(List<Crew> crews, CrewConvertCriteriaDTO convertCriteria);

	CrewDTO getCrewDTOById(Integer crewId);







}





