package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "schedule")
public class Schedule
{	
	@Transient
	public static final String SEQUENCE_NAME = "schedule_sequence";  //this name can be any

	@Id
	private long scheduleId;
	
	@Field("doctorId")
	private long doctorId;
	
	@NotBlank
	private String day;
	
	@NotBlank
	private String timings;
	
	@NotBlank
	private String status;

}
