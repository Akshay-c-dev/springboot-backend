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

import com.example.entity.Doctor;
import com.example.entity.User;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.DoctorService;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1")
public class UserController 
{
	@Autowired
	UserService userservice;
	
	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;

	@PostMapping("/addUser")
	public User addUser(@RequestBody User user)
	{
		user.setUserId(databaseaSequencesGeneratorService.generateSequence(User.SEQUENCE_NAME));
		return userservice.addUser(user);
	}
	
	 @PostMapping("/userLogin")
	    public ResponseEntity<Object> userLogin(@RequestBody User user, HttpSession session)
	 {
		 
	        User validatedUser = userservice.loginValidate(user);
	        if (validatedUser != null) {
	            session.setAttribute("user", validatedUser);
	            return new ResponseEntity<>(validatedUser, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
	        }
	    }

//	@PostMapping(value="/userLogin")
//	public ResponseEntity<Object> userLogin(@RequestBody User user){
//	    User user1 = userservice.loginValidate(user);
//	    return new ResponseEntity<>(user1, HttpStatus.OK);
//	}
	
	@GetMapping("getUserById/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") long userId)
	{
        User user= userservice.getUserById(userId);
        if(user!=null)
        {
        	return new ResponseEntity<>(user,HttpStatus.OK);
        }
        else
        {
        	return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> getAllUsers()
    {
       List<User>users=userservice.getAllUsers();
       ResponseEntity<Object>entity= new ResponseEntity<>(users,HttpStatus.OK);
       return entity;
    }

    @DeleteMapping("deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") long userId) {
        userservice.deleteUser(userId);
    }
    
    @PutMapping("/updateUsers/{userId}")
    public ResponseEntity<Object> updateuser(@PathVariable("userId") long userId, @RequestBody User user) {
        boolean updated = userservice.updateUser(user);
        if (updated) {
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
