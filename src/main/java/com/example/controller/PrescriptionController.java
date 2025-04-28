package com.example.controller;

import java.math.BigDecimal;
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

import com.example.entity.MedicineRow;
import com.example.entity.Prescription;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.PrescriptionService;
import com.example.service.ScheduleService;

@RestController
@RequestMapping("/api/v1")
public class PrescriptionController {
	@Autowired
	PrescriptionService prescriptionService;

	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;

//	@PostMapping("/addPrescription")
//	public ResponseEntity<Object> addPrescription(@RequestBody Prescription prescription)
//	{
//		prescription.setPrescriptionId(databaseaSequencesGeneratorService.generateSequence(prescription.SEQUENCE_NAME));
//		prescriptionService.addPrescription(prescription);
//		return new ResponseEntity<>("Prescription added",HttpStatus.CREATED);
//	}
	@PostMapping("/addPrescription")
	public ResponseEntity<Object> addPrescription(@RequestBody Prescription prescription) {
		try {
			prescription
					.setPrescriptionId(databaseaSequencesGeneratorService.generateSequence(Prescription.SEQUENCE_NAME));

			// Calculate total amount for the prescription based on medicine rows
			double totalAmount = 0.0; // Changed to double
			List<MedicineRow> medicineRows = prescription.getMedicineRows();
			for (MedicineRow row : medicineRows) {
				// Calculate amount for each medicine row (amount * qty)
				double rowAmount = row.getAmount();
				totalAmount += rowAmount;
			}
			prescription.setTotalAmount(totalAmount);

			prescriptionService.addPrescription(prescription);
			return new ResponseEntity<>("Prescription added", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllPrescriptions")
	public ResponseEntity<Object> getAllPrescriptions() {
		List<Prescription> prescription = prescriptionService.getAllPrescriptions();
		ResponseEntity<Object> entity = new ResponseEntity<>(prescription, HttpStatus.OK);
		return entity;

	}

	@GetMapping("/getPrescriptionById/{prescriptionId}")
	public ResponseEntity<Object> getPrescriptionById(@PathVariable Long prescriptionId) {
		Prescription prescription = prescriptionService.getPrescriptionById(prescriptionId);
		if (prescription != null) {
			return new ResponseEntity<>(prescription, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Prescription not found", HttpStatus.NOT_FOUND);
		}
	}

//	@GetMapping("/getPrescriptionByAppointmentId/{appointmentId}")
//    public ResponseEntity<Object> getPrescriptionByAppointmentId(@PathVariable long appointmentId)
//	{
//        Prescription prescription = prescriptionService.getPrescriptionByAppointmentId(appointmentId);
//        if (prescription != null) {
//            return new ResponseEntity<>(prescription,HttpStatus.OK);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
	@GetMapping("/getPrescriptionByAppointmentId/{appointmentId}")
	public ResponseEntity<Object> getPrescriptionByAppointmentId(@PathVariable long appointmentId) {
		List<Prescription> prescriptions = prescriptionService.getPrescriptionByAppointmentId(appointmentId);
		if (!prescriptions.isEmpty()) {
			return new ResponseEntity<>(prescriptions, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updatePrescriptionById/{prescriptionId}")
	public ResponseEntity<Object> updatePrescriptionById(@PathVariable("prescriptionId") long prescriptionId,
			@RequestBody Prescription prescription) {
		boolean updated = prescriptionService.updatePrescription(prescription);
		if (updated) {
			return new ResponseEntity<>("Prescription updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Precription not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getPrescriptionsByDoctorId/{doctorId}")
	public ResponseEntity<List<Prescription>> getPrescriptionsByDoctorId(@PathVariable long doctorId) {
		List<Prescription> prescriptions = prescriptionService.getPrescriptionsByDoctorId(doctorId);
		return ResponseEntity.ok(prescriptions);
	} 

	@DeleteMapping("/deletePrescriptionById/{prescriptionId}")
	public ResponseEntity<Object> deletePrescriptionById(@PathVariable Long prescriptionId) {
		boolean deleted = prescriptionService.deletePrescription(prescriptionId);
		if (deleted) {
			return new ResponseEntity<>("Prescription deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Prescription not deleted", HttpStatus.NOT_FOUND);
		}
	}

}
