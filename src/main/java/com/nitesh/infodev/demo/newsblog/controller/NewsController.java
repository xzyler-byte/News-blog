package com.nitesh.infodev.demo.newsblog.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	public String showAddNewsForm(Model model) {
		News news = new News();
		model.addAttribute("news", news);
		return "addNews";
	}

	@PostMapping("/add")
	public String addNews(Principal principal, @ModelAttribute("news") News news, Model model) throws Exception {
		model.addAttribute("news", news);
		User user = userRepository.findByUsername(principal.getName());
		newsService.createNews(news, user);
		MultipartFile newsImage = news.getNewsImage();
		if (!newsImage.isEmpty()) {
			try {
				byte[] bytes = newsImage.getBytes();
				String name = news.getId() + ".png";
				/*
				 * if ((Paths.get("src/main/resources/static/images/news/" + name)) != null) {
				 * Files.delete(Paths.get("src/main/resources/static/images/news/" + name)); }
				 */
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/images/news/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/";
	}

	@PostMapping("/update/{newsId}")
	public String updateNews(@ModelAttribute("news") News news, @PathVariable("newsId") Long newsId) {
		News currentNews = newsService.findById(newsId);
		System.out.println(currentNews);
		currentNews.setHeadline(news.getHeadline());
		currentNews.setDiscription(news.getDiscription());
		MultipartFile newsImage = news.getNewsImage();
			try {
				byte[] bytes = newsImage.getBytes();
				String name = newsId + ".png";
				
				  
				  Files.delete(Paths.get("src/main/resources/static/images/news/" + name)); 
				 
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/images/news/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		newsService.save(currentNews);
		return "redirect:/";
		
	}

	@PostMapping("/delete/{newsId}")
	public String deleteNews(@PathVariable("newsId") Long newsId) {
		News currentNews = newsService.findById(newsId);
		newsService.deleteNews(currentNews);
		return "redirect:/";
	}

}
