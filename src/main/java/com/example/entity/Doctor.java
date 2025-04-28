//package com.example.entity;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Transient;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.Data;
//
//@Document(collection = "Doctor")
//@Data
//public class Doctor 
//{
//	@Transient
//	public static final String SEQUENCE_NAME = "doctor_sequence";
//	
//	@Id
//	private long doctorId;
//	
//	@NotBlank
//	private String doctorName;
//	
//	@NotBlank
//	private String specilization;
//	
//	@NotBlank
//	private String email;
//	
//	@NotBlank
//	private String mobile;
//	
//	@NotBlank
//	private String certificates;
//	
//	@NotBlank
//	private String doctorPic;
//	
//	@NotBlank
//	private String hospitalName;
//	
//	@NotBlank
//	@Size(max = 100)
//	private String hospitalLocation;
//	
//	@NotBlank
//	@Size(max = 100)
//	private int experience;
//	
//	@NotBlank
//	private String status;
//	
//	@NotBlank
//	@Size(max = 100)
//	private int consultationFee;
//	
//	@NotBlank
//	private String password;
//	
//	@NotBlank
//	private String gender;
//	
//	@NotBlank
//	private String drAcc;
//
//	
//}
package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Document(collection = "Doctor")
@Data
public class Doctor {
    @Transient
    public static final String SEQUENCE_NAME = "doctor_sequence";

    @Id
    private long doctorId;

    @NotBlank
    private String doctorName;

    @NotBlank
    private String specilization;

    @NotBlank
    private String email;

    @NotBlank
    private String mobile;

    @NotBlank
    private String certificates;

    @NotBlank
    private String doctorPic;

    @NotBlank
    private String hospitalName;

    @NotBlank
    @Size(max = 100)
    private String hospitalLocation;

    @NotBlank
    @Size(max = 100)
    private int experience;

    @NotBlank
    private String status;

    @NotBlank
    @Size(max = 100)
    private int consultationFee;

    @NotBlank
    private String password;

    @NotBlank
    private String gender;

    @NotBlank
    private String drAcc;

    // Initialize reviews as null and averageDoctorRating as 0.0
    @Field("reviews")
    private List<Reviews> reviews = null;

    @Field("averageDoctorRating")
    private double averageDoctorRating = 0.0;
}
