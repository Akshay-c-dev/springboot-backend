package com.example.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Document(collection = "appointments") 
@Data
public class Appointments {
	@Transient
	public static final String SEQUENCE_NAME = "appointments_sequence"; // this name can be any

	@Id
	private long appointmentId;

	@Field("userId")
	private long userId;

	@Field("doctorId")
	private long doctorId;

	@CreatedDate
	private String bookDate;
	
	@NotBlank
	private String appointmentDate;

	@NotBlank
	private String appointmentStatus="On Progress";

	@NotBlank
	private String appointmentTime;

	@NotBlank
	private String symptoms;

	@NotBlank
	private String weight;

	@NotBlank
	private int age;

}
