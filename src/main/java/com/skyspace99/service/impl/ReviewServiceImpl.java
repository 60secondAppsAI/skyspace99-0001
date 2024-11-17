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
import com.skyspace99.dao.ReviewDAO;
import com.skyspace99.domain.Review;
import com.skyspace99.dto.ReviewDTO;
import com.skyspace99.dto.ReviewSearchDTO;
import com.skyspace99.dto.ReviewPageDTO;
import com.skyspace99.dto.ReviewConvertCriteriaDTO;
import com.skyspace99.dto.common.RequestDTO;
import com.skyspace99.dto.common.ResultDTO;
import com.skyspace99.service.ReviewService;
import com.skyspace99.util.ControllerUtils;





@Service
public class ReviewServiceImpl extends GenericServiceImpl<Review, Integer> implements ReviewService {

    private final static Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

	@Autowired
	ReviewDAO reviewDao;

	


	@Override
	public GenericDAO<Review, Integer> getDAO() {
		return (GenericDAO<Review, Integer>) reviewDao;
	}
	
	public List<Review> findAll () {
		List<Review> reviews = reviewDao.findAll();
		
		return reviews;	
		
	}

	public ResultDTO addReview(ReviewDTO reviewDTO, RequestDTO requestDTO) {

		Review review = new Review();

		review.setReviewId(reviewDTO.getReviewId());


		review.setRating(reviewDTO.getRating());


		review.setComments(reviewDTO.getComments());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		review = reviewDao.save(review);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Review> getAllReviews(Pageable pageable) {
		return reviewDao.findAll(pageable);
	}

	public Page<Review> getAllReviews(Specification<Review> spec, Pageable pageable) {
		return reviewDao.findAll(spec, pageable);
	}

	public ResponseEntity<ReviewPageDTO> getReviews(ReviewSearchDTO reviewSearchDTO) {
	
			Integer reviewId = reviewSearchDTO.getReviewId(); 
  			String comments = reviewSearchDTO.getComments(); 
 			String sortBy = reviewSearchDTO.getSortBy();
			String sortOrder = reviewSearchDTO.getSortOrder();
			String searchQuery = reviewSearchDTO.getSearchQuery();
			Integer page = reviewSearchDTO.getPage();
			Integer size = reviewSearchDTO.getSize();

	        Specification<Review> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, reviewId, "reviewId"); 
			
			
			spec = ControllerUtils.andIfNecessary(spec, comments, "comments"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("comments")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Review> reviews = this.getAllReviews(spec, pageable);
		
		//System.out.println(String.valueOf(reviews.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(reviews.getTotalPages()));
		
		List<Review> reviewsList = reviews.getContent();
		
		ReviewConvertCriteriaDTO convertCriteria = new ReviewConvertCriteriaDTO();
		List<ReviewDTO> reviewDTOs = this.convertReviewsToReviewDTOs(reviewsList,convertCriteria);
		
		ReviewPageDTO reviewPageDTO = new ReviewPageDTO();
		reviewPageDTO.setReviews(reviewDTOs);
		reviewPageDTO.setTotalElements(reviews.getTotalElements());
		return ResponseEntity.ok(reviewPageDTO);
	}

	public List<ReviewDTO> convertReviewsToReviewDTOs(List<Review> reviews, ReviewConvertCriteriaDTO convertCriteria) {
		
		List<ReviewDTO> reviewDTOs = new ArrayList<ReviewDTO>();
		
		for (Review review : reviews) {
			reviewDTOs.add(convertReviewToReviewDTO(review,convertCriteria));
		}
		
		return reviewDTOs;

	}
	
	public ReviewDTO convertReviewToReviewDTO(Review review, ReviewConvertCriteriaDTO convertCriteria) {
		
		ReviewDTO reviewDTO = new ReviewDTO();
		
		reviewDTO.setReviewId(review.getReviewId());

	
		reviewDTO.setRating(review.getRating());

	
		reviewDTO.setComments(review.getComments());

	

		
		return reviewDTO;
	}

	public ResultDTO updateReview(ReviewDTO reviewDTO, RequestDTO requestDTO) {
		
		Review review = reviewDao.getById(reviewDTO.getReviewId());

		review.setReviewId(ControllerUtils.setValue(review.getReviewId(), reviewDTO.getReviewId()));

		review.setRating(ControllerUtils.setValue(review.getRating(), reviewDTO.getRating()));

		review.setComments(ControllerUtils.setValue(review.getComments(), reviewDTO.getComments()));



        review = reviewDao.save(review);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ReviewDTO getReviewDTOById(Integer reviewId) {
	
		Review review = reviewDao.getById(reviewId);
			
		
		ReviewConvertCriteriaDTO convertCriteria = new ReviewConvertCriteriaDTO();
		return(this.convertReviewToReviewDTO(review,convertCriteria));
	}







}
