package com.student.enrollservice.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EnrollRequestBean {
	
	private String enrolleeName;
	private boolean activationStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String phoneNumber;

	private List<EnrolleeDependentBean> dependentDetails;

	

	

	public String getEnrolleeName() {
		return enrolleeName;
	}

	public void setEnrolleeName(String enrolleeName) {
		this.enrolleeName = enrolleeName;
	}

	public boolean getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<EnrolleeDependentBean> getDependentDetails() {
		return dependentDetails;
	}

	public void setDependentDetails(List<EnrolleeDependentBean> dependentDetails) {
		this.dependentDetails = dependentDetails;
	}

	
}
