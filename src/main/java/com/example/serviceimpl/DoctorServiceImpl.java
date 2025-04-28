package com.example.serviceimpl;

import java.util.List;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Doctor;
import com.example.entity.Reviews;
import com.example.repository.DoctorRepository;
import com.example.repository.ReviewsRepository;
import com.example.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private ReviewsRepository reviewsRepository;

	@Override
	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
//	public boolean loginValidate(Doctor doctor) {
//		Doctor doctor1 = doctorRepository.findByMobileAndPassword(doctor.getMobile(), doctor.getPassword());
////		System.out.println("what is there in Doctor object=" + doctor1);
////		return doctor1;
//		if(doctor1==null)
//		{
//			return false;
//		}
//		else
//		{
//			return true;
//		}
//	}
	public boolean loginValidate(Doctor doctor) {
		Doctor existingDoctor = doctorRepository.findByMobileAndPassword(doctor.getMobile(), doctor.getPassword());
		return existingDoctor != null;
	}

	@Override
	public List<Doctor> getAllDocs() {
		return doctorRepository.findAll();

	}

	@Override
	public Doctor getDoctorById(Long doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId).orElse(null);

		if (doctor != null) {
			updateDoctorReviewsAndRating(doctor);
		}

		return doctor;
	}

	@Override
	public boolean updateDoctor(Doctor doctor) {
		if (doctorRepository.existsById(doctor.getDoctorId())) {
			updateDoctorReviewsAndRating(doctor);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDoctorById(Long doctorId) {
		if (doctorRepository.existsById(doctorId)) {
			doctorRepository.deleteById(doctorId);
			return true;
		}
		return false;
	}

	@Override
	public Doctor findByMobile(String mobile) {
		return doctorRepository.findByMobile(mobile);
	}

	@Override
	public List<Doctor> getDoctorsBySpecilizationAndLocation(String specilization, String hospitalLocation) {
		return doctorRepository.findBySpecilizationAndHospitalLocation(specilization, hospitalLocation);

	}

	public List<String> getAllLocations() {
		return doctorRepository.getAllLocations();
	}

	public List<String> getAllSpecializations() {
		return doctorRepository.getAllSpecializations();
	}

	@Override
	public void updateDoctorReviewsAndRating(Doctor doctor) {
		List<Reviews> doctorReviews = reviewsRepository.findByDoctorId(doctor.getDoctorId());
		doctor.setReviews(doctorReviews);

		OptionalDouble averageRating = doctorReviews.stream().mapToInt(Reviews::getDoctorRating).average();

		doctor.setAverageDoctorRating(averageRating.isPresent() ? averageRating.getAsDouble() : 0.0);

		// Save the updated doctor back to the repository
		doctorRepository.save(doctor);
	}

//	@Override
//	public List<String> findAllLocations() {
//		
//		return doctorRepository.findAllLocations();
//	}
//
//	@Override
//	public List<String> findAllSpecializations() {
//		return doctorRepository.findAllSpecializations();
//	}

}
