package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Prescription;
import com.example.repository.PrescriptionRepository;
import com.example.service.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Override
	public Prescription addPrescription(Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}

	@Override
	public List<Prescription> getAllPrescriptions() {
		return prescriptionRepository.findAll();
	}

	@Override
	public Prescription getPrescriptionById(Long prescriptionId) {
		return prescriptionRepository.findById(prescriptionId).orElse(null);
	}

	@Override
	public boolean updatePrescription(Prescription prescription) {
		if (prescriptionRepository.existsById(prescription.getPrescriptionId())) {
			prescriptionRepository.save(prescription);
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePrescription(Long prescriptionId) {
		if (prescriptionRepository.existsById(prescriptionId)) {
			prescriptionRepository.deleteById(prescriptionId);
			return true;
		}
		return false;
	}

	@Override
	public List<Prescription> getPrescriptionByAppointmentId(long appointmentId)
	{
		return prescriptionRepository.findByAppointmentId(appointmentId);
	}

	@Override
	public List<Prescription> getPrescriptionsByDoctorId(long doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId);
    }


}
