package com.skyspace99.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FrequentFlyerSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer frequentFlyerId;
	
	private String membershipLevel;
	
	private int milesAccumulated;
	
}