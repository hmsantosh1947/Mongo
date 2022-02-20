package com.san.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.san.mongo.model.User;
import com.san.mongo.repo.UserRepository;

@RestController
@RequestMapping
public class Controller {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping(value = "/adduser")
	public String addUser() {
		User user = new User();
		user.setUserId(123);
		user.setUserName("Jai");
		
		userRepository.save(user);
		return "User added successfully";
	}
	
	@GetMapping(value = "/user")
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	@GetMapping(value = "/update/{userId}/{userName}")
	public String update(@PathVariable Integer userId, @PathVariable String userName) {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			return "No User matching Id " + userId;
		}else {
			user.get().setUserName(userName);
			userRepository.save(user.get());
			return "Ok, just updated record matching " + userId + " with user name as " + userName;
		}
		
	}
}
