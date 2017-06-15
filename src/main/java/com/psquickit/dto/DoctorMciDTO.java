package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the doctormci database table.
 * 
 */
@Entity
@Table(name="Doctormci")
@NamedQuery(name="Doctormci.findAll", query="SELECT d FROM DoctorMciDTO d")
public class DoctorMciDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Long createdBy;

	private Timestamp createdOn;

	private String registrationNumber;

	private Long updatedBy;

	private Timestamp updatedOn;

	//bi-directional many-to-one association to Doctoruser
	@ManyToOne
	@JoinColumn(name="DoctorUserId")
	private DoctorUserDTO doctorUser;

	//bi-directional many-to-one association to Mcimaster
	@ManyToOne
	@JoinColumn(name="MciMasterId")
	private MciMasterDTO mciMaster;

	public DoctorMciDTO() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public DoctorUserDTO getDoctorUser() {
		return this.doctorUser;
	}

	public void setDoctorUser(DoctorUserDTO doctorUser) {
		this.doctorUser = doctorUser;
	}

	public MciMasterDTO getMciMaster() {
		return this.mciMaster;
	}

	public void setMciMaster(MciMasterDTO mciMaster) {
		this.mciMaster = mciMaster;
	}

}