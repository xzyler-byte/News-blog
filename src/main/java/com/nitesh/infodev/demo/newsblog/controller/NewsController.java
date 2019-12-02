package com.nitesh.infodev.demo.newsblog.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nitesh.infodev.demo.newsblog.model.News;
import com.nitesh.infodev.demo.newsblog.model.User;
import com.nitesh.infodev.demo.newsblog.service.impl.NewsServiceImpl;
import com.nitesh.infodev.demo.newsblog.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	NewsServiceImpl newsService;
	@Autowired
	UserServiceImpl userService;

	@RequestMapping("/add")
	public String showAddNewsForm() {
		return "addNews";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addNews(Principal principal, @ModelAttribute("news") News news, Model model) throws Exception {
		model.addAttribute("news", news);
		User user = userService.findByUsername(principal.getName());
		newsService.save(news);
		newsService.createNews(news, user);
		return "index";
	}
}
