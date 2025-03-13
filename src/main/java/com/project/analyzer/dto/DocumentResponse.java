package com.project.analyzer.dto;

import java.time.LocalDateTime;

public class DocumentResponse {
    private String reportid;
    private String docname;
    private String detectedtasks;
    private String assignedtasks;
    private String unassignedtasks;
    private String createdby;
    private LocalDateTime createdat;
	private String url;
    public DocumentResponse() {
		super();
	}

	public DocumentResponse(String reportid, String docname, String detectedtasks, String assignedtasks,
			String unassignedtasks, String createdby, LocalDateTime createdat, String url) {
		super();
		this.reportid = reportid;
		this.docname = docname;
		this.detectedtasks = detectedtasks;
		this.assignedtasks = assignedtasks;
		this.unassignedtasks = unassignedtasks;
		this.createdby = createdby;
		this.createdat = createdat;
		this.url = url;
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

	public String getDetectedtasks() {
		return detectedtasks;
	}

	public void setDetectedtasks(String detectedtasks) {
		this.detectedtasks = detectedtasks;
	}

	public String getAssignedtasks() {
		return assignedtasks;
	}

	public void setAssignedtasks(String assignedtasks) {
		this.assignedtasks = assignedtasks;
	}

	public String getUnassignedtasks() {
		return unassignedtasks;
	}

	public void setUnassignedtasks(String unassignedtasks) {
		this.unassignedtasks = unassignedtasks;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

