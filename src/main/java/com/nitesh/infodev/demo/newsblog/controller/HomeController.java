package com.nitesh.infodev.demo.newsblog.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nitesh.infodev.demo.newsblog.model.User;
import com.nitesh.infodev.demo.newsblog.model.security.Role;
import com.nitesh.infodev.demo.newsblog.model.security.UserRole;
import com.nitesh.infodev.demo.newsblog.repository.NewsRepository;
import com.nitesh.infodev.demo.newsblog.repository.UserRepository;
import com.nitesh.infodev.demo.newsblog.repository.UserRoleRepository;
import com.nitesh.infodev.demo.newsblog.service.impl.UserSecurityService;
import com.nitesh.infodev.demo.newsblog.service.impl.UserServiceImpl;
import com.nitesh.infodev.demo.newsblog.utility.SecurityUtility;

@Controller
public class HomeController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	NewsRepository newsRepository;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	UserSecurityService userSecurityService;
	@Autowired
	UserRoleRepository userRoleRepository;

	@RequestMapping("/")

	public String main() {

		return "index";
	}

	@RequestMapping("/login")

	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)

	public String doLogin(@ModelAttribute("user") User user, Model model) {
		User currentUser = userService.findByUsername(user.getUsername());
		if (currentUser == null) {
			model.addAttribute("userNameNotFound", true);
			return "login";
		}
		UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
		model.addAttribute("user", currentUser);
		return "index";
	}

	@RequestMapping("/add/author")
	public String register() {
		return "signup";
	}

	@RequestMapping(value = "/add/author", method = RequestMethod.POST)
	public String newUserPost(@ModelAttribute("user") User user, Model model) throws Exception {
		model.addAttribute("user", user);
		if (userService.findByUsername(user.getUsername()) != null) {
			model.addAttribute("usernameExists", true);

			return "signup";
		}
		String password = SecurityUtility.randomPassword();

		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		user.setActive(true);
		Role role = new Role();
		role.setRoleId(1);
		role.setName("ROLE_ADMIN");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, role));
		userService.createUser(user, userRoles);
		return "redirect:/index";
	}
}
