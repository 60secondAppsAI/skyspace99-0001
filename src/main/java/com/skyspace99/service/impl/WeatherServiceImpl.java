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
import com.skyspace99.dao.WeatherDAO;
import com.skyspace99.domain.Weather;
import com.skyspace99.dto.WeatherDTO;
import com.skyspace99.dto.WeatherSearchDTO;
import com.skyspace99.dto.WeatherPageDTO;
import com.skyspace99.dto.WeatherConvertCriteriaDTO;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import com.skyspace99.service.WeatherService;
import com.skyspace99.util.ControllerUtils;





@Service
public class WeatherServiceImpl extends GenericServiceImpl<Weather, Integer> implements WeatherService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Autowired
	WeatherDAO weatherDao;

	


	@Override
	public GenericDAO<Weather, Integer> getDAO() {
		return (GenericDAO<Weather, Integer>) weatherDao;
	}
	
	public List<Weather> findAll () {
		List<Weather> weathers = weatherDao.findAll();
		
		return weathers;	
		
	}

	public ResultDTO addWeather(WeatherDTO weatherDTO, RequestDTO requestDTO) {

		Weather weather = new Weather();

		weather.setWeatherId(weatherDTO.getWeatherId());


		weather.setCity(weatherDTO.getCity());


		weather.setCondition(weatherDTO.getCondition());


		weather.setTemperature(weatherDTO.getTemperature());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		weather = weatherDao.save(weather);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Weather> getAllWeathers(Pageable pageable) {
		return weatherDao.findAll(pageable);
	}

	public Page<Weather> getAllWeathers(Specification<Weather> spec, Pageable pageable) {
		return weatherDao.findAll(spec, pageable);
	}

	public ResponseEntity<WeatherPageDTO> getWeathers(WeatherSearchDTO weatherSearchDTO) {
	
			Integer weatherId = weatherSearchDTO.getWeatherId(); 
 			String city = weatherSearchDTO.getCity(); 
 			String condition = weatherSearchDTO.getCondition(); 
  			String sortBy = weatherSearchDTO.getSortBy();
			String sortOrder = weatherSearchDTO.getSortOrder();
			String searchQuery = weatherSearchDTO.getSearchQuery();
			Integer page = weatherSearchDTO.getPage();
			Integer size = weatherSearchDTO.getSize();

	        Specification<Weather> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, weatherId, "weatherId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, city, "city"); 
			
			spec = ControllerUtils.andIfNecessary(spec, condition, "condition"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("city")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("condition")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Weather> weathers = this.getAllWeathers(spec, pageable);
		
		//System.out.println(String.valueOf(weathers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(weathers.getTotalPages()));
		
		List<Weather> weathersList = weathers.getContent();
		
		WeatherConvertCriteriaDTO convertCriteria = new WeatherConvertCriteriaDTO();
		List<WeatherDTO> weatherDTOs = this.convertWeathersToWeatherDTOs(weathersList,convertCriteria);
		
		WeatherPageDTO weatherPageDTO = new WeatherPageDTO();
		weatherPageDTO.setWeathers(weatherDTOs);
		weatherPageDTO.setTotalElements(weathers.getTotalElements());
		return ResponseEntity.ok(weatherPageDTO);
	}

	public List<WeatherDTO> convertWeathersToWeatherDTOs(List<Weather> weathers, WeatherConvertCriteriaDTO convertCriteria) {
		
		List<WeatherDTO> weatherDTOs = new ArrayList<WeatherDTO>();
		
		for (Weather weather : weathers) {
			weatherDTOs.add(convertWeatherToWeatherDTO(weather,convertCriteria));
		}
		
		return weatherDTOs;

	}
	
	public WeatherDTO convertWeatherToWeatherDTO(Weather weather, WeatherConvertCriteriaDTO convertCriteria) {
		
		WeatherDTO weatherDTO = new WeatherDTO();
		
		weatherDTO.setWeatherId(weather.getWeatherId());

	
		weatherDTO.setCity(weather.getCity());

	
		weatherDTO.setCondition(weather.getCondition());

	
		weatherDTO.setTemperature(weather.getTemperature());

	

		
		return weatherDTO;
	}

	public ResultDTO updateWeather(WeatherDTO weatherDTO, RequestDTO requestDTO) {
		
		Weather weather = weatherDao.getById(weatherDTO.getWeatherId());

		weather.setWeatherId(ControllerUtils.setValue(weather.getWeatherId(), weatherDTO.getWeatherId()));

		weather.setCity(ControllerUtils.setValue(weather.getCity(), weatherDTO.getCity()));

		weather.setCondition(ControllerUtils.setValue(weather.getCondition(), weatherDTO.getCondition()));

		weather.setTemperature(ControllerUtils.setValue(weather.getTemperature(), weatherDTO.getTemperature()));



        weather = weatherDao.save(weather);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public WeatherDTO getWeatherDTOById(Integer weatherId) {
	
		Weather weather = weatherDao.getById(weatherId);
			
		
		WeatherConvertCriteriaDTO convertCriteria = new WeatherConvertCriteriaDTO();
		return(this.convertWeatherToWeatherDTO(weather,convertCriteria));
	}







}
