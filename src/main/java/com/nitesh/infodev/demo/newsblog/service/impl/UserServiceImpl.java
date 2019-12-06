package com.nitesh.infodev.demo.newsblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.infodev.demo.newsblog.model.User;
import com.nitesh.infodev.demo.newsblog.repository.UserRepository;
import com.nitesh.infodev.demo.newsblog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}


	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return null;
	}

}
