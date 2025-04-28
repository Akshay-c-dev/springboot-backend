package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.BankAccount;
import com.example.entity.Reviews;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.ReviewsService;

@RestController
@RequestMapping("/api/v1")
public class ReviewsController {
	@Autowired
	ReviewsService reviewService;

	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;

//	@PostMapping("/addReview")
//    public ResponseEntity<Object> addReview(@RequestBody Reviews review)
//	{
//		review.setReviewId(databaseaSequencesGeneratorService.generateSequence(Reviews.SEQUENCE_NAME));
//		reviewService.createReview(review);
//		return new ResponseEntity<>("Reviews are added",HttpStatus.OK);
//    }

	@PostMapping("/addReview")
	public ResponseEntity<Object> addReview(@RequestBody Reviews review) {
		review.setReviewId(databaseaSequencesGeneratorService.generateSequence(Reviews.SEQUENCE_NAME));
		reviewService.createReview(review);
		return new ResponseEntity<>("Reviews are added", HttpStatus.OK);
	}

	@GetMapping("getReviewById/{reviewId}")
	public ResponseEntity<Object> getReviewById(@PathVariable("reviewId") long reviewId) {
		Reviews reviews = reviewService.getReviewById(reviewId);
		if (reviews != null) {
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Reviews not found", HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/getAllReviews")
	public ResponseEntity<Object> getAllReviews() {
		List<Reviews> reviews = reviewService.getAllReviews();
		ResponseEntity<Object> entity = new ResponseEntity<>(reviews, HttpStatus.OK);
		return entity;
	}

	@DeleteMapping("deleteReview/{reviewId}")
	public void deleteReview(@PathVariable("reviewId") long reviewId) {
		reviewService.deleteReview(reviewId);
	}

	@PutMapping(value = "/updateReviews/{reviewId}")
	public ResponseEntity<Object> updatereview(@PathVariable("reviewId") long reviewId, @RequestBody Reviews review) {
		boolean updated = reviewService.updateReviews(review);
		if (updated) {
			return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getDocReviewsAndRatingsByDoctorId/{doctorId}")
	public ResponseEntity<Object> getDocReviewsAndRatingsByDoctorId(@PathVariable("doctorId") long doctorId) {
		List<Reviews> reviews = reviewService.getReviewsByDoctorId(doctorId);
		if (reviews.isEmpty()) {
			return new ResponseEntity<>("No reviews found for the doctor", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

}
