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
import com.skyspace99.dao.AircraftDAO;
import com.skyspace99.domain.Aircraft;
import com.skyspace99.dto.AircraftDTO;
import com.skyspace99.dto.AircraftSearchDTO;
import com.skyspace99.dto.AircraftPageDTO;
import com.skyspace99.dto.AircraftConvertCriteriaDTO;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import com.skyspace99.service.AircraftService;
import com.skyspace99.util.ControllerUtils;





@Service
public class AircraftServiceImpl extends GenericServiceImpl<Aircraft, Integer> implements AircraftService {

    private final static Logger logger = LoggerFactory.getLogger(AircraftServiceImpl.class);

	@Autowired
	AircraftDAO aircraftDao;

	


	@Override
	public GenericDAO<Aircraft, Integer> getDAO() {
		return (GenericDAO<Aircraft, Integer>) aircraftDao;
	}
	
	public List<Aircraft> findAll () {
		List<Aircraft> aircrafts = aircraftDao.findAll();
		
		return aircrafts;	
		
	}

	public ResultDTO addAircraft(AircraftDTO aircraftDTO, RequestDTO requestDTO) {

		Aircraft aircraft = new Aircraft();

		aircraft.setAircraftId(aircraftDTO.getAircraftId());


		aircraft.setModel(aircraftDTO.getModel());


		aircraft.setManufacturingDate(aircraftDTO.getManufacturingDate());


		aircraft.setTotalSeats(aircraftDTO.getTotalSeats());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		aircraft = aircraftDao.save(aircraft);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Aircraft> getAllAircrafts(Pageable pageable) {
		return aircraftDao.findAll(pageable);
	}

	public Page<Aircraft> getAllAircrafts(Specification<Aircraft> spec, Pageable pageable) {
		return aircraftDao.findAll(spec, pageable);
	}

	public ResponseEntity<AircraftPageDTO> getAircrafts(AircraftSearchDTO aircraftSearchDTO) {
	
			Integer aircraftId = aircraftSearchDTO.getAircraftId(); 
 			String model = aircraftSearchDTO.getModel(); 
    			String sortBy = aircraftSearchDTO.getSortBy();
			String sortOrder = aircraftSearchDTO.getSortOrder();
			String searchQuery = aircraftSearchDTO.getSearchQuery();
			Integer page = aircraftSearchDTO.getPage();
			Integer size = aircraftSearchDTO.getSize();

	        Specification<Aircraft> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, aircraftId, "aircraftId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Aircraft> aircrafts = this.getAllAircrafts(spec, pageable);
		
		//System.out.println(String.valueOf(aircrafts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(aircrafts.getTotalPages()));
		
		List<Aircraft> aircraftsList = aircrafts.getContent();
		
		AircraftConvertCriteriaDTO convertCriteria = new AircraftConvertCriteriaDTO();
		List<AircraftDTO> aircraftDTOs = this.convertAircraftsToAircraftDTOs(aircraftsList,convertCriteria);
		
		AircraftPageDTO aircraftPageDTO = new AircraftPageDTO();
		aircraftPageDTO.setAircrafts(aircraftDTOs);
		aircraftPageDTO.setTotalElements(aircrafts.getTotalElements());
		return ResponseEntity.ok(aircraftPageDTO);
	}

	public List<AircraftDTO> convertAircraftsToAircraftDTOs(List<Aircraft> aircrafts, AircraftConvertCriteriaDTO convertCriteria) {
		
		List<AircraftDTO> aircraftDTOs = new ArrayList<AircraftDTO>();
		
		for (Aircraft aircraft : aircrafts) {
			aircraftDTOs.add(convertAircraftToAircraftDTO(aircraft,convertCriteria));
		}
		
		return aircraftDTOs;

	}
	
	public AircraftDTO convertAircraftToAircraftDTO(Aircraft aircraft, AircraftConvertCriteriaDTO convertCriteria) {
		
		AircraftDTO aircraftDTO = new AircraftDTO();
		
		aircraftDTO.setAircraftId(aircraft.getAircraftId());

	
		aircraftDTO.setModel(aircraft.getModel());

	
		aircraftDTO.setManufacturingDate(aircraft.getManufacturingDate());

	
		aircraftDTO.setTotalSeats(aircraft.getTotalSeats());

	

		
		return aircraftDTO;
	}

	public ResultDTO updateAircraft(AircraftDTO aircraftDTO, RequestDTO requestDTO) {
		
		Aircraft aircraft = aircraftDao.getById(aircraftDTO.getAircraftId());

		aircraft.setAircraftId(ControllerUtils.setValue(aircraft.getAircraftId(), aircraftDTO.getAircraftId()));

		aircraft.setModel(ControllerUtils.setValue(aircraft.getModel(), aircraftDTO.getModel()));

		aircraft.setManufacturingDate(ControllerUtils.setValue(aircraft.getManufacturingDate(), aircraftDTO.getManufacturingDate()));

		aircraft.setTotalSeats(ControllerUtils.setValue(aircraft.getTotalSeats(), aircraftDTO.getTotalSeats()));



        aircraft = aircraftDao.save(aircraft);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AircraftDTO getAircraftDTOById(Integer aircraftId) {
	
		Aircraft aircraft = aircraftDao.getById(aircraftId);
			
		
		AircraftConvertCriteriaDTO convertCriteria = new AircraftConvertCriteriaDTO();
		return(this.convertAircraftToAircraftDTO(aircraft,convertCriteria));
	}







}
