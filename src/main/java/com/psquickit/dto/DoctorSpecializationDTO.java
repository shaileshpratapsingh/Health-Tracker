package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the doctorspecialization database table.
 * 
 */
@Entity
@Table(name="Doctorspecialization")
@NamedQuery(name="Doctorspecialization.findAll", query="SELECT d FROM DoctorSpecializationDTO d")
public class DoctorSpecializationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Long createdBy;

	private Timestamp createdOn;

	private Long updatedBy;

	private Timestamp updatedOn;

	//bi-directional many-to-one association to Doctoruser
	@ManyToOne
	@JoinColumn(name="DoctorUserId")
	private DoctorUserDTO doctorUser;

	//bi-directional many-to-one association to Specializationmaster
	@ManyToOne
	@JoinColumn(name="SpecializationMasterId")
	private SpecializationMasterDTO specializationMaster;

	public DoctorSpecializationDTO() {
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

	public SpecializationMasterDTO getSpecializationMaster() {
		return this.specializationMaster;
	}

	public void setSpecializationMaster(SpecializationMasterDTO specializationMaster) {
		this.specializationMaster = specializationMaster;
	}

}