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

import com.example.entity.Schedule;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.ScheduleService;

@RestController
@RequestMapping("/api/v1")
public class ScheduleController {
	@Autowired
	ScheduleService scheduleService;

	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;

	@PostMapping("/addSchedules")
	public ResponseEntity<Object> addSchedules(@RequestBody List<Schedule> schedules) {
		for (Schedule schedule : schedules) {
			// Assign a unique schedule ID
			schedule.setScheduleId(databaseaSequencesGeneratorService.generateSequence(Schedule.SEQUENCE_NAME));

			// Add each schedule to the database
			scheduleService.addSchedule(schedule);
		}

		// Return a response indicating that schedules were added successfully
		return new ResponseEntity<>("Schedules added", HttpStatus.CREATED);
	}

	@GetMapping("/getAllSchedules")
	public ResponseEntity<Object> getAllSchedules() {
		List<Schedule> schedule = scheduleService.getAllSchedules();
		ResponseEntity<Object> entity = new ResponseEntity<>(schedule, HttpStatus.OK);
		return entity;

	}

	@GetMapping("/getSchedulesByDoctorId/{doctorId}")
	public ResponseEntity<List<Schedule>> getSchedulesByDoctorId(@PathVariable long doctorId)
	{
		List<Schedule> schedules = scheduleService.getSchedulesByDoctorId(doctorId);
		return new ResponseEntity<>(schedules, HttpStatus.OK);
	}

	@GetMapping("/getSchedulesById/{scheduleId}")
	public ResponseEntity<Object> getSchedulesById(@PathVariable Long scheduleId) {
		Schedule schedule = scheduleService.getScheduleById(scheduleId);
		if (schedule != null) {
			return new ResponseEntity<>(schedule, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Schedules not found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateScheduleById/{scheduleId}")
	public ResponseEntity<Object> updateScheduleById(@PathVariable("scheduleId") int scheduleId,
			@RequestBody Schedule schedule) {
		boolean updated = scheduleService.updateSchedule(schedule);
		if (updated) {
			return new ResponseEntity<>("Schedule updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Schedule not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteScheduleById/{scheduleId}")
	public ResponseEntity<Object> deleteScheduleById(@PathVariable Long scheduleId) {
		boolean deleted = scheduleService.deleteSchedule(scheduleId);
		if (deleted) {
			return new ResponseEntity<>("Schedule deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Schedule not deleted", HttpStatus.NOT_FOUND);
		}
	}

}
