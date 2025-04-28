package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Reviews;
import com.example.repository.ReviewsRepository;
import com.example.service.DoctorService;
import com.example.service.ReviewsService;

@Service
public class ReviewsServiceImpl implements ReviewsService
{

	@Autowired
	ReviewsRepository reviewsrepository;
	
	@Autowired
    DoctorService doctorService;

	@Override
	public Reviews createReview(Reviews review) 
	{
		Reviews savedReview = reviewsrepository.save(review);

        // Update the associated doctor’s reviews and rating
        doctorService.getDoctorById(review.getDoctorId());

        return savedReview;
	}

	@Override
	public Reviews getReviewById(long reviewId)
	{
		return reviewsrepository.findById(reviewId).orElse(null);
	}

	@Override
	public List<Reviews> getAllReviews()
	{
		return reviewsrepository.findAll();
	}

	@Override
	public void deleteReview(long reviewId)
	{
		reviewsrepository.deleteById(reviewId);
		
	}

	@Override
	public boolean updateReviews(Reviews reviews)
	{
		if (isReviewExist(reviews.getReviewId()))
		{
            reviewsrepository.save(reviews);
            return true;
        }

		return false;
	}

	@Override
	public boolean isReviewExist(long reviewId)
	{
		return reviewsrepository.existsById(reviewId);
	}

	@Override
	 public List<Reviews> getReviewsByDoctorId(long doctorId) {
        return reviewsrepository.findByDoctorId(doctorId);
    }
	
}
