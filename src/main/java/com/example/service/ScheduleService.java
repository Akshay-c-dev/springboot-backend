package com.example.service;

import java.util.List;

import com.example.entity.Schedule;

public interface ScheduleService 
{
	Schedule addSchedule(Schedule schedule);
	List<Schedule> getAllSchedules();
	Schedule getScheduleById(Long scheduleId);
	boolean updateSchedule(Schedule schedule);
	boolean deleteSchedule(Long scheduleId);
	List<Schedule> getSchedulesByDoctorId(long doctorId);
	
}
