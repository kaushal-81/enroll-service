package com.student.enrollservice.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "HEALTH_CARE_ENROLLEE_DETAILS")
public class EnrollEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENROLLEE_ID")
	private int enrolleId;

	@Column(name = "ENROLLEE_NAME")
	private String enrolleeName;

	@Column(name = "ACTIVATION_STATUS")
	private boolean activationStatus;

	@Column(name = "BIRTH_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "ENROLL_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date enrollDate;

	@Column(name = "HAS_DEPENDENCIES")
	private boolean hasDependencies;

	/**
	 * @return the enrolleId
	 */
	public int getEnrolleId() {
		return enrolleId;
	}

	/**
	 * @param enrolleId the enrolleId to set
	 */
	public void setEnrolleId(int enrolleId) {
		this.enrolleId = enrolleId;
	}

	/**
	 * @return the enrolleeName
	 */
	public String getEnrolleeName() {
		return enrolleeName;
	}

	/**
	 * @param enrolleeName the enrolleeName to set
	 */
	public void setEnrolleeName(String enrolleeName) {
		this.enrolleeName = enrolleeName;
	}

	/**
	 * @return the activationStatus
	 */
	public boolean isActivationStatus() {
		return activationStatus;
	}

	/**
	 * @param activationStatus the activationStatus to set
	 */
	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the enrollDate
	 */
	public Date getEnrollDate() {
		return enrollDate;
	}

	/**
	 * @param enrollDate the enrollDate to set
	 */
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	/**
	 * @return the hasDependencies
	 */
	public boolean isHasDependencies() {
		return hasDependencies;
	}

	/**
	 * @param hasDependencies the hasDependencies to set
	 */
	public void setHasDependencies(boolean hasDependencies) {
		this.hasDependencies = hasDependencies;
	}
	
	
}
