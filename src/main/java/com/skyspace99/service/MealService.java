package com.skyspace99.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace99.domain.Meal;
import com.skyspace99.dto.MealDTO;
import com.skyspace99.dto.MealSearchDTO;
import com.skyspace99.dto.MealPageDTO;
import com.skyspace99.dto.MealConvertCriteriaDTO;
import com.skyspace99.service.GenericService;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MealService extends GenericService<Meal, Integer> {

	List<Meal> findAll();

	ResultDTO addMeal(MealDTO mealDTO, RequestDTO requestDTO);

	ResultDTO updateMeal(MealDTO mealDTO, RequestDTO requestDTO);

    Page<Meal> getAllMeals(Pageable pageable);

    Page<Meal> getAllMeals(Specification<Meal> spec, Pageable pageable);

	ResponseEntity<MealPageDTO> getMeals(MealSearchDTO mealSearchDTO);
	
	List<MealDTO> convertMealsToMealDTOs(List<Meal> meals, MealConvertCriteriaDTO convertCriteria);

	MealDTO getMealDTOById(Integer mealId);







}





