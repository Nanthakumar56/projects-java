package com.project.analyzer.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="fetcheddues")
public class FetchedDues {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private String dateid;
	private String title;
	private LocalDateTime duedate;
	private String reportid;
	public FetchedDues() {
		super();
	}
	public FetchedDues(String dateid, String title, LocalDateTime duedate, String reportid) {
		super();
		this.dateid = dateid;
		this.title = title;
		this.duedate = duedate;
		this.reportid = reportid;
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
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	
	
}
