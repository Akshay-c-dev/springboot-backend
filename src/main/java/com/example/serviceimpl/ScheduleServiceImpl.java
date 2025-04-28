package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Schedule;
import com.example.repository.AppointmentsRepository;
import com.example.repository.ScheduleRepository;
import com.example.service.ScheduleService;

@Service

public class ScheduleServiceImpl implements ScheduleService
{
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public Schedule addSchedule(Schedule schedule)
	{
		return scheduleRepository.save(schedule);
	}

	@Override
	public List<Schedule> getAllSchedules() 
	{
		return scheduleRepository.findAll();
	}

	@Override
	public Schedule getScheduleById(Long scheduleId)
	{
		return scheduleRepository.findById(scheduleId).orElse(null);
	}

	@Override
	public boolean updateSchedule(Schedule schedule)
	{
		if (scheduleRepository.existsById(schedule.getScheduleId()))
		{
			scheduleRepository.save(schedule);
            return true;
        }
		return false;

	}

	@Override
	public boolean deleteSchedule(Long scheduleId) 
	{
		 if (scheduleRepository.existsById(scheduleId)) { 
			 scheduleRepository.deleteById(scheduleId); 
	            return true;
	        }	
		return false;
	}

	@Override
	public List<Schedule> getSchedulesByDoctorId(long doctorId)
	{
		return scheduleRepository.getSchedulesByDoctorId(doctorId);
	}

	

	
}
