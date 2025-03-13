package com.project.analyzer.dto;

import java.time.LocalDateTime;

public class DataDto 
{
	private String dataid;
	private String projecttitle;
	private String scope;
	private String techsummary;
	private String techrequirements;
	private LocalDateTime duedate;
	private String clientname;
	private String address;
	private String email;
	private String phone;
	
	public DataDto() {
		super();
	}

	public DataDto(String dataid, String projecttitle, String scope, String techsummary, String techrequirements,
			LocalDateTime duedate, String clientname, String address, String email, String phone) {
		super();
		this.dataid = dataid;
		this.projecttitle = projecttitle;
		this.scope = scope;
		this.techsummary = techsummary;
		this.techrequirements = techrequirements;
		this.duedate = duedate;
		this.clientname = clientname;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getProjecttitle() {
		return projecttitle;
	}

	public void setProjecttitle(String projecttitle) {
		this.projecttitle = projecttitle;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTechsummary() {
		return techsummary;
	}

	public void setTechsummary(String techsummary) {
		this.techsummary = techsummary;
	}

	public String getTechrequirements() {
		return techrequirements;
	}

	public void setTechrequirements(String techrequirements) {
		this.techrequirements = techrequirements;
	}

	public LocalDateTime getDuedate() {
		return duedate;
	}

	public void setDuedate(LocalDateTime duedate) {
		this.duedate = duedate;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
