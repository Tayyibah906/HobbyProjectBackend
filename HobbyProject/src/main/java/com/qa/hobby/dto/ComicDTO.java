package com.qa.hobby.dto;


public class ComicDTO {
	
	private long id;
	
	private String title;
	
	private String publisher;
	
	private String writer;
	
    private String coverArtist;
	
	private int issue;
	
	public ComicDTO(long id, String title,  String publisher, String writer, String coverArtist, int issue) {
		super();
		this.id = id;
		this.title= title;
		this.publisher = publisher;
		this.writer=writer;
		this.coverArtist = coverArtist;
		this.issue=issue;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
