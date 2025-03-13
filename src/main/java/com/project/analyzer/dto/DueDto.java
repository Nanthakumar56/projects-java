package com.project.analyzer.dto;

import java.time.LocalDateTime;

public class DueDto {
	private String dateid;
	private String title;
	private LocalDateTime duedate;
	public DueDto() {
		super();
	}
	public DueDto(String dateid, String title, LocalDateTime duedate) {
		super();
		this.dateid = dateid;
		this.title = title;
		this.duedate = duedate;
	}
	public String getDateid() {
		return dateid;
	}
	public void setDateid(String dateid) {
		this.dateid = dateid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getDuedate() {
		return duedate;
	}
	public void setDuedate(LocalDateTime duedate) {
		this.duedate = duedate;
	}
	
}
