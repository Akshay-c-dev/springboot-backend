package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Admin;
import com.example.service.AdminService;
import com.example.service.DatabaseaSequencesGeneratorService;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;

	@PostMapping("createAdmin")
	public Admin createEmployee(@RequestBody Admin admin) {
		admin.setId(databaseaSequencesGeneratorService.generateSequence(Admin.SEQUENCE_NAME));
		return adminService.createAdmin(admin);
	}
	
	@PostMapping(value = "/adminLogin")
	public ResponseEntity<Object> adminLogin(@RequestBody Admin admin)
	{
		boolean flag = adminService.loginValidate(admin);
		
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}

}
