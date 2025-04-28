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

import com.example.entity.Pharmacy;
import com.example.entity.Prescription;
import com.example.entity.PrescriptionTransfer;
import com.example.entity.User;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.PharmacyService;
import com.example.service.PrescriptionTransferService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1")
public class PharmacyController 
{
	@Autowired
	private PharmacyService pharmacyService;
		
	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;
	
	@PostMapping("/addPharmacy")
	public ResponseEntity<Object> addPharmacy(@RequestBody Pharmacy pharmacy)
	{
		pharmacy.setPharmacyId(databaseaSequencesGeneratorService.generateSequence(pharmacy.SEQUENCE_NAME));
		pharmacyService.addPharmacy(pharmacy);
		return new ResponseEntity<>("Pharmacy added",HttpStatus.CREATED);
	}
	
	@PostMapping("/pharmacyLogin")
    public ResponseEntity<Object> pharmacyLogin(@RequestBody Pharmacy pharmacy, HttpSession session)
 {
	 
        Pharmacy validatedPharmacy = pharmacyService.loginValidate(pharmacy);
        if (validatedPharmacy != null) {
            session.setAttribute("user", validatedPharmacy);
            return new ResponseEntity<>(validatedPharmacy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

	
	@GetMapping("/getAllPharmacies")
	public ResponseEntity<Object> getAllPharmacies()
	{
		List<Pharmacy> pharmacy=pharmacyService.getAllPharmacies();
		ResponseEntity<Object> entity=new ResponseEntity<>(pharmacy, HttpStatus.OK);
		return entity;
		
	}
	
	@GetMapping("/getPharmacyById/{pharmacyId}")
    public ResponseEntity<Object> getPharmacyById(@PathVariable Long pharmacyId) 
	{
        Pharmacy pharmacy = pharmacyService.getPharmacyById(pharmacyId);
        if (pharmacy != null) {
            return new ResponseEntity<>(pharmacy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pharmacy not found", HttpStatus.NOT_FOUND);
        }
    }
	
	@PutMapping("/updatePharmacyById/{pharmacyId}")
    public ResponseEntity<Object> updatePharmacyById(@PathVariable("pharmacyId") long pharmacyId,@RequestBody Pharmacy pharmacy) 
	{
        boolean updated = pharmacyService.updatePharmacy(pharmacy);
        if (updated) {
            return new ResponseEntity<>("Pharmacy updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pharmacy not found", HttpStatus.NOT_FOUND);
        }
    }
 
	
	 @DeleteMapping("/deletePharmacyById/{pharmacyId}")
	 public ResponseEntity<Object> deletePharmacyById(@PathVariable Long pharmacyId)
	 {
	     boolean deleted = pharmacyService.deletePharmacy(pharmacyId);
	     if (deleted) {
	         return new ResponseEntity<>("Pharmacy deleted", HttpStatus.OK);
	     } else {
	         return new ResponseEntity<>("Pharmacy not deleted", HttpStatus.NOT_FOUND);
	     }
	 }
}
