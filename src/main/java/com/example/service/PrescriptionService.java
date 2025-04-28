package com.example.service;

import java.util.List;

import com.example.entity.Prescription;

public interface PrescriptionService
{
	Prescription addPrescription(Prescription prescription);
	List<Prescription> getAllPrescriptions();
	Prescription getPrescriptionById(Long prescriptionId);
	boolean updatePrescription(Prescription prescription);
	boolean deletePrescription(Long prescriptionId);
	List<Prescription> getPrescriptionByAppointmentId(long appointmentId);
	List<Prescription> getPrescriptionsByDoctorId(long doctorId);
}