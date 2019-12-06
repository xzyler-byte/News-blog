package com.nitesh.infodev.demo.newsblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.infodev.demo.newsblog.model.News;
import com.nitesh.infodev.demo.newsblog.model.User;
import com.nitesh.infodev.demo.newsblog.repository.NewsRepository;
import com.nitesh.infodev.demo.newsblog.repository.UserRepository;
import com.nitesh.infodev.demo.newsblog.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsRepository newsRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public News findByHeadline(String headline) {
		return newsRepository.findByHeadline(headline);
	}

	@Override
	public News findById(long id) {
		return newsRepository.findById(id).get();
	}

	@Override
	public News save(News news) {
		return newsRepository.save(news);
	}

	@Override
	public List<News> getNews() {
		return newsRepository.findAll();
	}

	@Override
	public void createNews(News news, User user) throws Exception {
		User currentUser = userRepository.findByUsername(user.getUsername());
		news.setUser(currentUser);
		newsRepository.save(news);

	}

}
