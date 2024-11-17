package com.skyspace99.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skyspace99.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skyspace99.domain.Weather;
import com.skyspace99.dto.WeatherDTO;
import com.skyspace99.dto.WeatherSearchDTO;
import com.skyspace99.dto.WeatherPageDTO;
import com.skyspace99.service.WeatherService;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/weather")
@RestController
public class WeatherController {

	private final static Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@Autowired
	WeatherService weatherService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Weather> getAll() {

		List<Weather> weathers = weatherService.findAll();
		
		return weathers;	
	}

	@GetMapping(value = "/{weatherId}")
	@ResponseBody
	public WeatherDTO getWeather(@PathVariable Integer weatherId) {
		
		return (weatherService.getWeatherDTOById(weatherId));
	}

 	@RequestMapping(value = "/addWeather", method = RequestMethod.POST)
	public ResponseEntity<?> addWeather(@RequestBody WeatherDTO weatherDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = weatherService.addWeather(weatherDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/weathers")
	public ResponseEntity<WeatherPageDTO> getWeathers(WeatherSearchDTO weatherSearchDTO) {
 
		return weatherService.getWeathers(weatherSearchDTO);
	}	

	@RequestMapping(value = "/updateWeather", method = RequestMethod.POST)
	public ResponseEntity<?> updateWeather(@RequestBody WeatherDTO weatherDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = weatherService.updateWeather(weatherDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
