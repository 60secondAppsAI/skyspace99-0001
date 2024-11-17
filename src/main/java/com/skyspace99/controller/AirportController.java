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

import com.skyspace99.domain.Airport;
import com.skyspace99.dto.AirportDTO;
import com.skyspace99.dto.AirportSearchDTO;
import com.skyspace99.dto.AirportPageDTO;
import com.skyspace99.service.AirportService;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/airport")
@RestController
public class AirportController {

	private final static Logger logger = LoggerFactory.getLogger(AirportController.class);

	@Autowired
	AirportService airportService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Airport> getAll() {

		List<Airport> airports = airportService.findAll();
		
		return airports;	
	}

	@GetMapping(value = "/{airportId}")
	@ResponseBody
	public AirportDTO getAirport(@PathVariable Integer airportId) {
		
		return (airportService.getAirportDTOById(airportId));
	}

 	@RequestMapping(value = "/addAirport", method = RequestMethod.POST)
	public ResponseEntity<?> addAirport(@RequestBody AirportDTO airportDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = airportService.addAirport(airportDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/airports")
	public ResponseEntity<AirportPageDTO> getAirports(AirportSearchDTO airportSearchDTO) {
 
		return airportService.getAirports(airportSearchDTO);
	}	

	@RequestMapping(value = "/updateAirport", method = RequestMethod.POST)
	public ResponseEntity<?> updateAirport(@RequestBody AirportDTO airportDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = airportService.updateAirport(airportDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
