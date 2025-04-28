package com.example.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "medicine")
public class Medicine
{
	@Transient
    public static final String SEQUENCE_NAME = "medicine_sequence";

	@Id
	private long id;
	
	@NotBlank
	private double amount; 

	@NotBlank
	@Indexed(unique = true)
	private String medicineName;
}
