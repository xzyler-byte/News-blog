package com.nitesh.infodev.demo.newsblog.service;

import java.util.List;

import com.nitesh.infodev.demo.newsblog.model.News;
import com.nitesh.infodev.demo.newsblog.model.User;

public interface NewsService {

	News findByHeadline(String headline);

	News findById(long id);

	News save(News news);

	List<News> getNews();

	void createNews(News news, User user) throws Exception;
}
