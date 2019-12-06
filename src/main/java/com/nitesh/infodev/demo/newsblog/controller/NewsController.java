package com.nitesh.infodev.demo.newsblog.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nitesh.infodev.demo.newsblog.model.News;
import com.nitesh.infodev.demo.newsblog.model.User;
import com.nitesh.infodev.demo.newsblog.repository.UserRepository;
import com.nitesh.infodev.demo.newsblog.service.impl.NewsServiceImpl;
import com.nitesh.infodev.demo.newsblog.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	NewsServiceImpl newsService;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/add")
	public String showAddNewsForm() {

		return "addNews";
	}

	@PostMapping("/add")
	public String addNews(Principal principal, @ModelAttribute("news") News news, Model model) throws Exception {
		model.addAttribute("news", news);
		User user = userRepository.findByUsername(principal.getName());
		newsService.createNews(news, user);
		return "index";
	}
}
