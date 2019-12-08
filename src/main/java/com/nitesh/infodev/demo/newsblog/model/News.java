package com.nitesh.infodev.demo.newsblog.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "news")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Column(name = "headline")
	private String headline;

	@Column(name = "discription",length = 10000,scale = 100000)
	private String discription;

	@Transient
	private MultipartFile newsImage;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;

	public News() {

	}

	public News(String headline, String discription) {
		super();
		this.headline = headline;
		this.discription = discription;
	}

	public long getId() {
		return id;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MultipartFile getNewsImage() {
		return newsImage;
	}

	public void setNewsImage(MultipartFile newsImage) {
		this.newsImage = newsImage;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", headline=" + headline + ", discription=" + discription + ", newsImage=" + newsImage
				+ "]";
	}

}
