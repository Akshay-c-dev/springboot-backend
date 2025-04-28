package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "reviews")
public class Reviews
{
	@Transient
    public static final String SEQUENCE_NAME = "reviews_sequence"; 

	@Id
	private long reviewId;
	
	@Field("doctorId")
	private long doctorId;
	
	@Field("userId")
	private long userId;
	
	@NotBlank
	private int doctorRating;

	@NotBlank
	private int pharmacyRating;
	
	@NotBlank
	private String doctorReview;
	
	@NotBlank
	private String pharmacyReview;
	
	@Field("pharmacyId")
	private long pharmacyId;
	
}
