package com.project.analyzer.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "projectanalyzer")
public class ProjectAnalyzer 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private String reportid;
	private String docname;
	private String filesize;
	private String createdby;
	private LocalDateTime createdat;
	private byte[] file;
	
	public ProjectAnalyzer() {
		super();
	}

	public ProjectAnalyzer(String reportid, String docname, String filesize, String createdby, LocalDateTime createdat,
			byte[] file) {
		super();
		this.reportid = reportid;
		this.docname = docname;
		this.filesize = filesize;
		this.createdby = createdby;
		this.createdat = createdat;
		this.file = file;
	}

	public String getReportid() {
		return reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	
	
}
