package com.project.analyzer.dto;

import java.time.LocalDateTime;

public class TasksDto {
	private String taskid;
	private String title;
	private LocalDateTime duedate;
	private boolean isadded;
	public TasksDto() {
		super();
	}
	public TasksDto(String taskid, String title, LocalDateTime duedate, boolean isadded) {
		super();
		this.taskid = taskid;
		this.title = title;
		this.duedate = duedate;
		this.isadded = isadded;
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

}
