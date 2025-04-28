package com.example.service;

import com.example.entity.Admin;

public interface AdminService 
{
	Admin createAdmin(Admin admin);
	boolean loginValidate(Admin admin);

	
}
