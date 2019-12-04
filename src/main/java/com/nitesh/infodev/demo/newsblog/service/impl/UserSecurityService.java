package com.nitesh.infodev.demo.newsblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nitesh.infodev.demo.newsblog.model.User;

@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userService.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}

		return user;
	}
}
