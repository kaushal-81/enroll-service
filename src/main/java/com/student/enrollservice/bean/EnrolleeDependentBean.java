package com.student.enrollservice.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EnrolleeDependentBean {

	private String dependentName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dependentDOB;
	public String getDependentName() {
		return dependentName;
	}
	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}
	public Date getDependentDOB() {
		return dependentDOB;
	}
	public void setDependentDOB(Date dependentDOB) {
		this.dependentDOB = dependentDOB;
	}
}
