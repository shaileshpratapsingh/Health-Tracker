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
	
	@ManyToOne
	@JoinColumn(name = "DoctorUserId")
	private DoctorUserDTO doctorUserDTO;
	
	@ManyToOne
	@JoinColumn(name = "MciMasterId")
	private MciMasterDTO mciMasterId;
	
	@Column(name = "RegistrationNumber")
	private Long registrationNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MciMasterDTO getMciMasterId() {
		return mciMasterId;
	}

	public void setMciMasterId(MciMasterDTO mciMasterId) {
		this.mciMasterId = mciMasterId;
	}

	public Long getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(Long registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public DoctorUserDTO getDoctorUserDTO() {
		return doctorUserDTO;
	}

	public void setDoctorUserDTO(DoctorUserDTO doctorUserDTO) {
		this.doctorUserDTO = doctorUserDTO;
	}
	
	
}
