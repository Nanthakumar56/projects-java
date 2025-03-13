package com.project.analyzer.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="analyzeddata")
public class AnalyzedData {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
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
	private String reportid;
	
	public AnalyzedData() {
		super();
	}

	public AnalyzedData(String dataid, String projecttitle, String scope, String techsummary, String techrequirements,
			LocalDateTime duedate, String clientname, String address, String email, String phone, String reportid) {
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
		this.reportid = reportid;
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

	public String getReportid() {
		return reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
