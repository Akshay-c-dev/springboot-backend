package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Doctor;
import com.example.entity.PrescriptionTransfer;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.PrescriptionTransferService;

@RestController
@RequestMapping("/api/v1")
public class PrescriptionTransferController
{

	@Autowired
	private PrescriptionTransferService prescriptionTransferService;

	
	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;
	

    @PostMapping("/transferPrescription")
    public PrescriptionTransfer transferPrescription(@RequestBody PrescriptionTransfer prescriptionTransfer)
    {
    	prescriptionTransfer.setId(databaseaSequencesGeneratorService.generateSequence(PrescriptionTransfer.SEQUENCE_NAME));
        prescriptionTransfer.setOrderStatus("Pending"); // Set the order status to "Pending"
    	return prescriptionTransferService.savePrescriptionTransfer(prescriptionTransfer);
    }
    
    @GetMapping("/getPrescriptionsByPharmacyId/{pharmacyId}")
    public ResponseEntity<List<PrescriptionTransfer>> getPrescriptionsByPharmacyId(@PathVariable long pharmacyId)
    {
        List<PrescriptionTransfer> prescriptions = prescriptionTransferService.getPrescriptionsByPharmacyId(pharmacyId);
        return ResponseEntity.ok(prescriptions);
    }
    
//    @PutMapping("/updateOrderStatus/{id}")
//    public ResponseEntity<PrescriptionTransfer> updateOrderStatus(@PathVariable long id, @RequestBody String status)
//    {
//        PrescriptionTransfer updatedPrescription = prescriptionTransferService.updateOrderStatus(id, status);
//        if (updatedPrescription != null) {
//            return ResponseEntity.ok(updatedPrescription);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @PutMapping("/updateOrderStatus/{id}")
    public ResponseEntity<PrescriptionTransfer> updateOrderStatus(@PathVariable long id, @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("orderStatus");
        PrescriptionTransfer updatedPrescription = prescriptionTransferService.updateOrderStatus(id, status);
        if (updatedPrescription != null) {
            return ResponseEntity.ok(updatedPrescription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getOrdersByUserId/{userId}")
    public ResponseEntity<List<PrescriptionTransfer>> getOrdersByUserId(@PathVariable long userId)
    {
        List<PrescriptionTransfer> orders = prescriptionTransferService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }
}
