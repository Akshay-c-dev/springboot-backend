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
import org.springframework.web.client.RestTemplate;

import com.example.entity.Admin;
import com.example.entity.Doctor;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.DoctorService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {
	@Autowired
	private DoctorService docservice;

	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;

	@PostMapping("/addDoctor")
	public Doctor addDoctor(@RequestBody Doctor doctor) {
		System.out.println("Doc obj from registration=" + doctor);
		doctor.setDoctorId(databaseaSequencesGeneratorService.generateSequence(Doctor.SEQUENCE_NAME));
		return docservice.addDoctor(doctor);
	}

//	@PostMapping("/doctorLogin")
//	public ResponseEntity<Object> doctorLogin(@RequestBody Doctor doctor,HttpSession session)
//	{
//		boolean flag = docservice.loginValidate(doctor);
//		System.out.println("Doctor="+doctor);
//		
//		RestTemplate restTemplate = new RestTemplate();
//		
//		ResponseEntity<Doctor> response= restTemplate.getForEntity("http://localhost:8092/getDoctorByMobile/"+doctor.getMobile(),Doctor.class);
//		
//		Doctor responseObject=response.getBody();
//		System.out.println("Doctor Response object="+responseObject);
//		
//		if(responseObject!=null)
//		{
//			System.out.println("in if block");
//			session.setAttribute("doctor", responseObject);
//		}
//		else
//		{
//			System.out.println("in else block");
//			System.out.println("Invalid mobile number/password");
//			
//		}
//		
//		
//		return new ResponseEntity<>(flag,HttpStatus.OK);
//		
//	}
	@PostMapping("/doctorLogin")
	public ResponseEntity<Object> doctorLogin(@RequestBody Doctor doctor, HttpSession session) {
		boolean isValid = docservice.loginValidate(doctor);
		if (isValid) {
			Doctor responseObject = docservice.findByMobile(doctor.getMobile());
			if (responseObject != null) {
				System.out.println("response object=" + responseObject);
				session.setAttribute("doctor", responseObject);
				return new ResponseEntity<>(responseObject, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(value = "/getDoctorByMobile/{mobile}")
	public Doctor getDoctorByMobile(@PathVariable("mobile") String mobile) {
		return docservice.findByMobile(mobile);
	}

	@GetMapping("/getAllDocs")
	public ResponseEntity<Object> getAllDocs() {
		List<Doctor> doctor = docservice.getAllDocs();
		ResponseEntity<Object> entity = new ResponseEntity<>(doctor, HttpStatus.OK);
		return entity;

	}
	
//    @GetMapping("/getAllLocations")
//	public List<String> getAllLocations() {
//		List<String> locations = docservice.findAllLocations();
//		return (locations);
//	}
//
//	@GetMapping("/getAllSpecializations")
//	public ResponseEntity<List<String>> getAllSpecializations() {
//		List<String> specializations = docservice.findAllSpecializations();
//		return ResponseEntity.ok(specializations);
//	}
	@GetMapping("/getAllLocations")
    public List<String> getAllLocations() {
        return docservice.getAllLocations();
    }

    @GetMapping("/getAllSpecializations")
    public List<String> getAllSpecializations() {
        return docservice.getAllSpecializations();
    }

	@GetMapping("/getDoctorsBySpecilizationAndLocation/{specilization}/{hospitalLocation}")
	public List<Doctor> getDoctorsBySpecilizationAndLocation(@PathVariable String specilization,
			@PathVariable String hospitalLocation) {
		return docservice.getDoctorsBySpecilizationAndLocation(specilization, hospitalLocation);
	}

	@GetMapping("/getDoctorById/{doctorId}")
	public ResponseEntity<Object> getDoctorById(@PathVariable Long doctorId) {
		Doctor doctor = docservice.getDoctorById(doctorId);
		if (doctor != null) {
			return new ResponseEntity<>(doctor, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateDoctorById/{doctorId}")
	public ResponseEntity<Object> updateDoctorById(@PathVariable("doctorId") long doctorId,
			@RequestBody Doctor doctor) {
		System.out.println("method called");
		boolean updated = docservice.updateDoctor(doctor);
		if (updated) {
			return new ResponseEntity<>("Doctor updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteDoctorById/{doctorId}")
	public ResponseEntity<Object> deleteDoctorById(@PathVariable Long doctorId) {
		boolean deleted = docservice.deleteDoctorById(doctorId);
		if (deleted) {
			return new ResponseEntity<>("Doctor deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
		}
	}

}
