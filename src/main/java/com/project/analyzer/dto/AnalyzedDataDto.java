package com.project.analyzer.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AnalyzedDataDto {
	private String reportid;
	private String docname;
	private LocalDateTime createdat;
	private DataDto data;
	private List<TasksDto> tasksdto;
	private List<DueDto> duedto;
	private String url;
	public AnalyzedDataDto() {
		super();
	}

	public AnalyzedDataDto(String reportid, String docname, LocalDateTime createdat, DataDto data,
			List<TasksDto> tasksdto, List<DueDto> duedto, String url) {
		super();
		this.reportid = reportid;
		this.docname = docname;
		this.createdat = createdat;
		this.data = data;
		this.tasksdto = tasksdto;
		this.duedto = duedto;
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

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	public DataDto getData() {
		return data;
	}

	public void setData(DataDto data) {
		this.data = data;
	}

	public List<TasksDto> getTasksdto() {
		return tasksdto;
	}

	public void setTasksdto(List<TasksDto> tasksdto) {
		this.tasksdto = tasksdto;
	}

	public List<DueDto> getDuedto() {
		return duedto;
	}

	public void setDuedto(List<DueDto> duedto) {
		this.duedto = duedto;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
