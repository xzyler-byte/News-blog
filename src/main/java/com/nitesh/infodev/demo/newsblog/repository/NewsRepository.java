package com.nitesh.infodev.demo.newsblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.infodev.demo.newsblog.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {

	News findByHeadline(String headline);
}
