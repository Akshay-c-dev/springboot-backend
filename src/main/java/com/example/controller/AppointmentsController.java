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

import com.example.entity.Appointments;
import com.example.entity.Doctor;
import com.example.service.AppointmentsService;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.DoctorService;

@RestController
@RequestMapping("/api/v1")
public class AppointmentsController
{
	@Autowired
	private AppointmentsService appointmentservice;
	
	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;
	
	@PostMapping("/addAppointment")
	public ResponseEntity<Appointments> addAppointment(@RequestBody Appointments appointment)
	{
		appointment.setAppointmentId(databaseaSequencesGeneratorService.generateSequence(Appointments.SEQUENCE_NAME));
		
		Appointments savedAppointment= appointmentservice.addAppointments(appointment);
		savedAppointment.setAppointmentStatus("Scheduled");
		Appointments updatedAppointment=appointmentservice.addAppointments(savedAppointment);
		return ResponseEntity.ok(updatedAppointment);
	}

	 

	@GetMapping("/getAllAppointments")
	public ResponseEntity<Object> getAllAppointments() {
		List<Appointments> appointment = appointmentservice.getAllAppointments();
		ResponseEntity<Object> entity = new ResponseEntity<>(appointment, HttpStatus.OK);
		return entity;

	}

	@GetMapping("/getAppointmentById/{appointmentId}")
	public ResponseEntity<Object> getAppointmentById(@PathVariable Long appointmentId) 
	{
		Appointments appointments = appointmentservice.getAppointmentsById(appointmentId);
		if (appointments != null) {
			return new ResponseEntity<>(appointments, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateAppointmentByid/{appointmentId}")
	public ResponseEntity<Object> updateAppointmentByid(@PathVariable("appointmentId") long appointmentId,@RequestBody Appointments appointments)
	{
		boolean updated = appointmentservice.updateAppointments(appointments);
		if (updated) {
			return new ResponseEntity<>("Doctor updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAppointmentsByDoctorId/{doctorId}")
    public ResponseEntity<List<Appointments>> getAppointmentsByDoctorId(@PathVariable long doctorId) {
        List<Appointments> appointments = appointmentservice.getAppointmentsByDoctorId(doctorId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
	
	@GetMapping("/getAppointmentsByUserId/{userId}")
    public ResponseEntity<List<Appointments>> getAppointmentsByUserId(@PathVariable long userId) {
        List<Appointments> appointments = appointmentservice.getAppointmentsByUserId(userId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
	
	@DeleteMapping("/deleteAppointmentById/{appointmentId}")
	public ResponseEntity<Object> deleteAppointmentById(@PathVariable Long appointmentId)
	{
		boolean deleted = appointmentservice.deleteAppointments(appointmentId);
		if (deleted) 
		{
			return new ResponseEntity<>("appointment deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
		}
	}

}
