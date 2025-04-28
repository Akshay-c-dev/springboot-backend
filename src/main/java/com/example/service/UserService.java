package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService
{
	User addUser(User user);
	public User loginValidate(User user);
	List<User> getAllUsers();
	User getUserById(Long userId);
	boolean updateUser(User user);
	boolean deleteUser(Long userId);

}
