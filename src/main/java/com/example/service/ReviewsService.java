package com.example.service;

import java.util.List;

import com.example.entity.Reviews;

public interface ReviewsService 
{
	
	Reviews createReview(Reviews review);

	Reviews  getReviewById(long reviewId);

	List<Reviews> getAllReviews();

	void deleteReview(long reviewId);

	boolean updateReviews(Reviews reviews);

	boolean isReviewExist(long reviewId);
	
	List<Reviews> getReviewsByDoctorId(long doctorId);
	
	
}
