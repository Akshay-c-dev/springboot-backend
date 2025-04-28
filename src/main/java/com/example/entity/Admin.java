package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Document(collection = "Admin")
@Data
public class Admin
{
	@Transient
	public static final String SEQUENCE_NAME = "employee_sequence";
	
	@Id
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String userName;
	
	@NotBlank
	@Size(max = 100)
	private String password;

}
