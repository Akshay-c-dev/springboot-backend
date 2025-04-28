package com.example.service;

import java.util.List;

import com.example.entity.Doctor;

public interface DoctorService 
{
	Doctor addDoctor(Doctor doctor);
	boolean loginValidate(Doctor doctor);
	List<Doctor>getAllDocs();
	//new method
	void updateDoctorReviewsAndRating(Doctor doctor);
	List<String>getAllLocations();
	List<String>getAllSpecializations();
	Doctor getDoctorById(Long doctorId);
	boolean updateDoctor(Doctor doctor);
	boolean deleteDoctorById(Long doctorId);
	Doctor findByMobile(String mobile);
	List<Doctor> getDoctorsBySpecilizationAndLocation(String specilization, String hospitalLocation);
	
}
