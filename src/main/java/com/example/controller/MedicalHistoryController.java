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

import com.example.entity.MedicalHistory;
import com.example.entity.Reviews;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.MedicalHistoryService;
import com.example.service.ReviewsService;

@RestController
@RequestMapping("/api/v1")
public class MedicalHistoryController
{
	@Autowired
	MedicalHistoryService medicalhistoryservice;
	
	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;
	
	 @PostMapping("/createMedicalHistory")
	    public ResponseEntity<Object> createMedicalHistory(@RequestBody MedicalHistory medicalhistory)
	 {
		 medicalhistory.setMedicalId(databaseaSequencesGeneratorService.generateSequence(MedicalHistory.SEQUENCE_NAME));
		 medicalhistoryservice.createMedicalHistory(medicalhistory);
		 return new ResponseEntity<>("Medical History created",HttpStatus.CREATED);
	 }
	 
	 @GetMapping("/getMedicalHistoryByMedicalId/{medicalId}")
	    public ResponseEntity<Object> getMedicalHistoryByMedicalId(@PathVariable("medicalId") long medicalId)
	 	{
	        MedicalHistory medicalhistory= medicalhistoryservice.getMedicalHistoryByMedicalId(medicalId);
	        if(medicalhistory!=null)
	        {
	        	return new ResponseEntity<>(medicalhistory,HttpStatus.OK);
	        }
	        else
	        {
	        	return new ResponseEntity<>(medicalhistory,HttpStatus.NOT_FOUND);
	        }
	    }

	    @GetMapping("/getAllMedicalHistory")
	    public ResponseEntity<Object> getAllMedicalHistory()
	    {
	    	List<MedicalHistory> history=medicalhistoryservice.getAllMedicalHistory();
	    	ResponseEntity<Object>entity=new ResponseEntity<>(history,HttpStatus.OK);
	    	return entity;
	    }
	    
	    @DeleteMapping("/deleteMedicalHistory/{medicalId}")
	    public void deleteMedicalHistory(@PathVariable("medicalId") long medicalId)
	    {
	    	medicalhistoryservice.deleteMedicalHistory(medicalId);
	    }
	    
	    @PutMapping(value = "/updateMedicalHistory/{medicalId}")
	    public ResponseEntity<Object> updateMedicalHistory(@PathVariable("medicalId") long medicalId, @RequestBody MedicalHistory medicalhistory) {
	        boolean updated = medicalhistoryservice.updateMedicalHistory(medicalhistory);
	        if (updated) {
	            return new ResponseEntity<>("MedicalHistory updated successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("MedicalHistory not found", HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    @GetMapping("/getMedicalHistoryByUserId/{userId}")
	    public List<MedicalHistory> getMedicalHistoryByUserId(@PathVariable Long userId)
	    {
	        return medicalhistoryservice.getMedicalHistoryByUserId(userId);
	    }


}
