package com.example.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Appointments;
import com.example.repository.AppointmentsRepository;
import com.example.service.AppointmentsService;

@Service
public class AppointmentsServiceImpl implements AppointmentsService 
{
	@Autowired
	private AppointmentsRepository appointmentRepository;

	@Override
	public Appointments addAppointments(Appointments appointments)
	{
		return appointmentRepository.save(appointments);
	}

	@Override
	public List<Appointments> getAllAppointments()
	{
		return appointmentRepository.findAll();
	}

	@Override
	public Appointments getAppointmentsById(Long appointmentId) 
	{
		return appointmentRepository.findById(appointmentId).orElse(null);
	}

	@Override
	public boolean updateAppointments(Appointments appointments)
	{
		if (appointmentRepository.existsById(appointments.getAppointmentId()))
		{
			appointmentRepository.save(appointments);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAppointments(Long appointmentId)
	{
		if (appointmentRepository.existsById(appointmentId)) 
		{
			appointmentRepository.deleteById(appointmentId);
			return true;
		}
		return false;
	}

	@Override
	public List<Appointments> getAppointmentsByDoctorId(long doctorId)
	{
        return appointmentRepository.findByDoctorId(doctorId);

	}

	@Override
	public Appointments updateAppointmentStatus(long appointmentId, String status)
	{
        Optional<Appointments> appointmentOptional = appointmentRepository.findById(appointmentId);
        if (appointmentOptional.isPresent()) {
            Appointments appointment = appointmentOptional.get();
            appointment.setAppointmentStatus(status);
            return appointmentRepository.save(appointment);
        }
        return null; // or throw an exception
    }

	@Override
	public List<Appointments> getAppointmentsByUserId(long userId)
	{
		return appointmentRepository.findByUserId(userId);
	}
}
