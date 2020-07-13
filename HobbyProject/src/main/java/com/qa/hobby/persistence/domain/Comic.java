package com.qa.hobby.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "comic")
public class Comic {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "comic_title", unique = false)
	private String title;

	@Column
	private String publisher;

	@Column
	private String writer;

	@Column
	private String coverArtist;

	@Column(nullable = false)
	private int issue;

	@ManyToOne(targetEntity = Universe.class)
	private Universe universe;

	public Comic() {

	}

	public Comic(String title, String writer, String publisher, String coverArtist, int issue) {
		super();
		this.title = title;
		this.publisher = publisher;
		this.coverArtist = coverArtist;
		this.issue = issue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCoverArtist() {
		return coverArtist;
	}

	public void setCoverArtist(String coverArtist) {
		this.coverArtist = coverArtist;
	}

	public int getIssue() {
		return issue;
	}

	public void setIssue(int issue) {
		this.issue = issue;
	}

	public Universe getUniverse() {
		return universe;
	}

	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

}
