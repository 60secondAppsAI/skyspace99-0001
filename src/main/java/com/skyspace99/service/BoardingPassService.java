package com.skyspace99.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace99.domain.BoardingPass;
import com.skyspace99.dto.BoardingPassDTO;
import com.skyspace99.dto.BoardingPassSearchDTO;
import com.skyspace99.dto.BoardingPassPageDTO;
import com.skyspace99.dto.BoardingPassConvertCriteriaDTO;
import com.skyspace99.service.GenericService;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BoardingPassService extends GenericService<BoardingPass, Integer> {

	List<BoardingPass> findAll();

	ResultDTO addBoardingPass(BoardingPassDTO boardingPassDTO, RequestDTO requestDTO);

	ResultDTO updateBoardingPass(BoardingPassDTO boardingPassDTO, RequestDTO requestDTO);

    Page<BoardingPass> getAllBoardingPasss(Pageable pageable);

    Page<BoardingPass> getAllBoardingPasss(Specification<BoardingPass> spec, Pageable pageable);

	ResponseEntity<BoardingPassPageDTO> getBoardingPasss(BoardingPassSearchDTO boardingPassSearchDTO);
	
	List<BoardingPassDTO> convertBoardingPasssToBoardingPassDTOs(List<BoardingPass> boardingPasss, BoardingPassConvertCriteriaDTO convertCriteria);

	BoardingPassDTO getBoardingPassDTOById(Integer boardingPassId);







}





