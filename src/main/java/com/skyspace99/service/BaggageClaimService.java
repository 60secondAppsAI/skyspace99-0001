package com.skyspace99.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace99.domain.BaggageClaim;
import com.skyspace99.dto.BaggageClaimDTO;
import com.skyspace99.dto.BaggageClaimSearchDTO;
import com.skyspace99.dto.BaggageClaimPageDTO;
import com.skyspace99.dto.BaggageClaimConvertCriteriaDTO;
import com.skyspace99.service.GenericService;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BaggageClaimService extends GenericService<BaggageClaim, Integer> {

	List<BaggageClaim> findAll();

	ResultDTO addBaggageClaim(BaggageClaimDTO baggageClaimDTO, RequestDTO requestDTO);

	ResultDTO updateBaggageClaim(BaggageClaimDTO baggageClaimDTO, RequestDTO requestDTO);

    Page<BaggageClaim> getAllBaggageClaims(Pageable pageable);

    Page<BaggageClaim> getAllBaggageClaims(Specification<BaggageClaim> spec, Pageable pageable);

	ResponseEntity<BaggageClaimPageDTO> getBaggageClaims(BaggageClaimSearchDTO baggageClaimSearchDTO);
	
	List<BaggageClaimDTO> convertBaggageClaimsToBaggageClaimDTOs(List<BaggageClaim> baggageClaims, BaggageClaimConvertCriteriaDTO convertCriteria);

	BaggageClaimDTO getBaggageClaimDTOById(Integer baggageClaimId);







}





