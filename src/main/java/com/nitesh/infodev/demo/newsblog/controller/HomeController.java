package com.nitesh.infodev.demo.newsblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nitesh.infodev.demo.newsblog.model.User;
import com.nitesh.infodev.demo.newsblog.service.impl.UserServiceImpl;

@Controller
public class HomeController {
	@Autowired
	UserServiceImpl userService;

	@GetMapping("/")
	public String main() {
		return "index";
	}

	@GetMapping("/login")

	public String login() {
		return "login";
	}
	@GetMapping("user/add")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping(value = "user/add")
	public String newUser(@ModelAttribute("user") User user, Model model) throws Exception {
		model.addAttribute("user", user);
		if (userService.findByUsername(user.getUsername()) != null) {
			model.addAttribute("usernameExists", true);
			return "signup";
		}
		userService.save(user);
		return "redirect:/login";
	}
}
