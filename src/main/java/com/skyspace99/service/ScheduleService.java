package com.skyspace99.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace99.domain.Schedule;
import com.skyspace99.dto.ScheduleDTO;
import com.skyspace99.dto.ScheduleSearchDTO;
import com.skyspace99.dto.SchedulePageDTO;
import com.skyspace99.dto.ScheduleConvertCriteriaDTO;
import com.skyspace99.service.GenericService;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ScheduleService extends GenericService<Schedule, Integer> {

	List<Schedule> findAll();

	ResultDTO addSchedule(ScheduleDTO scheduleDTO, RequestDTO requestDTO);

	ResultDTO updateSchedule(ScheduleDTO scheduleDTO, RequestDTO requestDTO);

    Page<Schedule> getAllSchedules(Pageable pageable);

    Page<Schedule> getAllSchedules(Specification<Schedule> spec, Pageable pageable);

	ResponseEntity<SchedulePageDTO> getSchedules(ScheduleSearchDTO scheduleSearchDTO);
	
	List<ScheduleDTO> convertSchedulesToScheduleDTOs(List<Schedule> schedules, ScheduleConvertCriteriaDTO convertCriteria);

	ScheduleDTO getScheduleDTOById(Integer scheduleId);







}





