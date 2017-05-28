package com.psquickit.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "doctorspecialization")
public class DoctorSpecializationDTO implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "DoctorUserId")
	private DoctorUserDTO doctorUser;
	
	@ManyToOne
	@JoinColumn(name = "SpecializationMasterId")
	private SpecializationMasterDTO specializationMaster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DoctorUserDTO getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(DoctorUserDTO doctorUser) {
		this.doctorUser = doctorUser;
	}

	public SpecializationMasterDTO getSpecializationMaster() {
		return specializationMaster;
	}

	public void setSpecializationMaster(SpecializationMasterDTO specializationMaster) {
		this.specializationMaster = specializationMaster;
	}

}
