package com.skyspace99.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspace99.dao.GenericDAO;
import com.skyspace99.service.GenericService;
import com.skyspace99.service.impl.GenericServiceImpl;
import com.skyspace99.dao.BaggageClaimDAO;
import com.skyspace99.domain.BaggageClaim;
import com.skyspace99.dto.BaggageClaimDTO;
import com.skyspace99.dto.BaggageClaimSearchDTO;
import com.skyspace99.dto.BaggageClaimPageDTO;
import com.skyspace99.dto.BaggageClaimConvertCriteriaDTO;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import com.skyspace99.service.BaggageClaimService;
import com.skyspace99.util.ControllerUtils;





@Service
public class BaggageClaimServiceImpl extends GenericServiceImpl<BaggageClaim, Integer> implements BaggageClaimService {

    private final static Logger logger = LoggerFactory.getLogger(BaggageClaimServiceImpl.class);

	@Autowired
	BaggageClaimDAO baggageClaimDao;

	


	@Override
	public GenericDAO<BaggageClaim, Integer> getDAO() {
		return (GenericDAO<BaggageClaim, Integer>) baggageClaimDao;
	}
	
	public List<BaggageClaim> findAll () {
		List<BaggageClaim> baggageClaims = baggageClaimDao.findAll();
		
		return baggageClaims;	
		
	}

	public ResultDTO addBaggageClaim(BaggageClaimDTO baggageClaimDTO, RequestDTO requestDTO) {

		BaggageClaim baggageClaim = new BaggageClaim();

		baggageClaim.setBaggageClaimId(baggageClaimDTO.getBaggageClaimId());


		baggageClaim.setClaimTime(baggageClaimDTO.getClaimTime());


		baggageClaim.setStatus(baggageClaimDTO.getStatus());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		baggageClaim = baggageClaimDao.save(baggageClaim);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<BaggageClaim> getAllBaggageClaims(Pageable pageable) {
		return baggageClaimDao.findAll(pageable);
	}

	public Page<BaggageClaim> getAllBaggageClaims(Specification<BaggageClaim> spec, Pageable pageable) {
		return baggageClaimDao.findAll(spec, pageable);
	}

	public ResponseEntity<BaggageClaimPageDTO> getBaggageClaims(BaggageClaimSearchDTO baggageClaimSearchDTO) {
	
			Integer baggageClaimId = baggageClaimSearchDTO.getBaggageClaimId(); 
   			String status = baggageClaimSearchDTO.getStatus(); 
 			String sortBy = baggageClaimSearchDTO.getSortBy();
			String sortOrder = baggageClaimSearchDTO.getSortOrder();
			String searchQuery = baggageClaimSearchDTO.getSearchQuery();
			Integer page = baggageClaimSearchDTO.getPage();
			Integer size = baggageClaimSearchDTO.getSize();

	        Specification<BaggageClaim> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, baggageClaimId, "baggageClaimId"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<BaggageClaim> baggageClaims = this.getAllBaggageClaims(spec, pageable);
		
		//System.out.println(String.valueOf(baggageClaims.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(baggageClaims.getTotalPages()));
		
		List<BaggageClaim> baggageClaimsList = baggageClaims.getContent();
		
		BaggageClaimConvertCriteriaDTO convertCriteria = new BaggageClaimConvertCriteriaDTO();
		List<BaggageClaimDTO> baggageClaimDTOs = this.convertBaggageClaimsToBaggageClaimDTOs(baggageClaimsList,convertCriteria);
		
		BaggageClaimPageDTO baggageClaimPageDTO = new BaggageClaimPageDTO();
		baggageClaimPageDTO.setBaggageClaims(baggageClaimDTOs);
		baggageClaimPageDTO.setTotalElements(baggageClaims.getTotalElements());
		return ResponseEntity.ok(baggageClaimPageDTO);
	}

	public List<BaggageClaimDTO> convertBaggageClaimsToBaggageClaimDTOs(List<BaggageClaim> baggageClaims, BaggageClaimConvertCriteriaDTO convertCriteria) {
		
		List<BaggageClaimDTO> baggageClaimDTOs = new ArrayList<BaggageClaimDTO>();
		
		for (BaggageClaim baggageClaim : baggageClaims) {
			baggageClaimDTOs.add(convertBaggageClaimToBaggageClaimDTO(baggageClaim,convertCriteria));
		}
		
		return baggageClaimDTOs;

	}
	
	public BaggageClaimDTO convertBaggageClaimToBaggageClaimDTO(BaggageClaim baggageClaim, BaggageClaimConvertCriteriaDTO convertCriteria) {
		
		BaggageClaimDTO baggageClaimDTO = new BaggageClaimDTO();
		
		baggageClaimDTO.setBaggageClaimId(baggageClaim.getBaggageClaimId());

	
		baggageClaimDTO.setClaimTime(baggageClaim.getClaimTime());

	
		baggageClaimDTO.setStatus(baggageClaim.getStatus());

	

		
		return baggageClaimDTO;
	}

	public ResultDTO updateBaggageClaim(BaggageClaimDTO baggageClaimDTO, RequestDTO requestDTO) {
		
		BaggageClaim baggageClaim = baggageClaimDao.getById(baggageClaimDTO.getBaggageClaimId());

		baggageClaim.setBaggageClaimId(ControllerUtils.setValue(baggageClaim.getBaggageClaimId(), baggageClaimDTO.getBaggageClaimId()));

		baggageClaim.setClaimTime(ControllerUtils.setValue(baggageClaim.getClaimTime(), baggageClaimDTO.getClaimTime()));

		baggageClaim.setStatus(ControllerUtils.setValue(baggageClaim.getStatus(), baggageClaimDTO.getStatus()));



        baggageClaim = baggageClaimDao.save(baggageClaim);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BaggageClaimDTO getBaggageClaimDTOById(Integer baggageClaimId) {
	
		BaggageClaim baggageClaim = baggageClaimDao.getById(baggageClaimId);
			
		
		BaggageClaimConvertCriteriaDTO convertCriteria = new BaggageClaimConvertCriteriaDTO();
		return(this.convertBaggageClaimToBaggageClaimDTO(baggageClaim,convertCriteria));
	}







}