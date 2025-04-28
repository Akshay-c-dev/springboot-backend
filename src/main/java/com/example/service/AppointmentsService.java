package com.example.service;

import java.util.List;

import com.example.entity.Appointments;

public interface AppointmentsService {
	Appointments addAppointments(Appointments appointments);

	// public Appointments loginValidate(Appointments appointments);
	List<Appointments> getAllAppointments();

	List<Appointments> getAppointmentsByDoctorId(long doctorId);
	
	List<Appointments> getAppointmentsByUserId(long userId);

	Appointments getAppointmentsById(Long appointmentId);

	boolean updateAppointments(Appointments appointments);

	boolean deleteAppointments(Long appointmentId);
	
	Appointments updateAppointmentStatus(long appointmentId, String status);

}
