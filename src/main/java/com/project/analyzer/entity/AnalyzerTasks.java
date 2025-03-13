package com.project.analyzer.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="analyzertasks")
public class AnalyzerTasks {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private String taskid;
	private String title;
	private LocalDateTime duedate;
	private boolean isadded;
	private String reportid;
	public AnalyzerTasks() {
		super();
	}
	public AnalyzerTasks(String taskid, String title, LocalDateTime duedate, boolean isadded, String reportid) {
		super();
		this.taskid = taskid;
		this.title = title;
		this.duedate = duedate;
		this.isadded = isadded;
		this.reportid = reportid;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
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
	public boolean isIsadded() {
		return isadded;
	}
	public void setIsadded(boolean isadded) {
		this.isadded = isadded;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	
}
