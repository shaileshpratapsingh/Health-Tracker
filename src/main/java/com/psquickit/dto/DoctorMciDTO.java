package com.psquickit.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doctormci")
public class DoctorMciDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "DoctorUserId")
	private Long doctorUserId;
	
	@ManyToOne
	@JoinColumn(name = "MciMasterId")
	private MCIMasterDTO mciMasterId;
	
	@Column(name = "RegistrationNumber")
	private Long registrationNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDoctorUserId() {
		return doctorUserId;
	}

	public void setDoctorUserId(Long doctorUserId) {
		this.doctorUserId = doctorUserId;
	}

	

	public MCIMasterDTO getMciMasterId() {
		return mciMasterId;
	}

	public void setMciMasterId(MCIMasterDTO mciMasterId) {
		this.mciMasterId = mciMasterId;
	}

	public Long getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(Long registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	
}
