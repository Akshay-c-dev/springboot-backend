package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
    private UserRepository userRepository;

	@Override
	public User addUser(User user)
	{
		return userRepository.save(user);
	}

	@Override
	public User loginValidate(User user) {
		User user1= userRepository.findByMobileAndPassword(user.getMobile(), user.getPassword());
		System.out.println("what is there in User=" + user1);
		return user1;
	}


	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Long userId)
	{
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public boolean updateUser(User user) {
	    if (userRepository.existsById(user.getUserId())) {
	        userRepository.save(user);
	        return true;
	    }
	    return false;
	}


	@Override
	public boolean deleteUser(Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
