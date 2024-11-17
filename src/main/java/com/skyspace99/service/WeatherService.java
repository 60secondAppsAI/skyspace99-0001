package com.skyspace99.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace99.domain.Weather;
import com.skyspace99.dto.WeatherDTO;
import com.skyspace99.dto.WeatherSearchDTO;
import com.skyspace99.dto.WeatherPageDTO;
import com.skyspace99.dto.WeatherConvertCriteriaDTO;
import com.skyspace99.service.GenericService;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WeatherService extends GenericService<Weather, Integer> {

	List<Weather> findAll();

	ResultDTO addWeather(WeatherDTO weatherDTO, RequestDTO requestDTO);

	ResultDTO updateWeather(WeatherDTO weatherDTO, RequestDTO requestDTO);

    Page<Weather> getAllWeathers(Pageable pageable);

    Page<Weather> getAllWeathers(Specification<Weather> spec, Pageable pageable);

	ResponseEntity<WeatherPageDTO> getWeathers(WeatherSearchDTO weatherSearchDTO);
	
	List<WeatherDTO> convertWeathersToWeatherDTOs(List<Weather> weathers, WeatherConvertCriteriaDTO convertCriteria);

	WeatherDTO getWeatherDTOById(Integer weatherId);







}





