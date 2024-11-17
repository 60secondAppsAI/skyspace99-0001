package com.skyspace99.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MealPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<MealDTO> meals;
}





