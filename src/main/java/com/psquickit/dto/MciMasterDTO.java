package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mcimaster database table.
 * 
 */
@Entity
@Table(name="Mcimaster")
@NamedQuery(name="Mcimaster.findAll", query="SELECT m FROM MciMasterDTO m")
public class MciMasterDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private byte isActive;

	private byte isVerified;

	private String mciName;

	//bi-directional many-to-one association to Doctormci
	@OneToMany(mappedBy="mcimaster")
	private List<DoctorMciDTO> doctormcis;

	public MciMasterDTO() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public byte getIsVerified() {
		return this.isVerified;
	}

	public void setIsVerified(byte isVerified) {
		this.isVerified = isVerified;
	}

	public String getMciName() {
		return this.mciName;
	}

	public void setMciName(String mciName) {
		this.mciName = mciName;
	}

	public List<DoctorMciDTO> getDoctormcis() {
		return this.doctormcis;
	}

	public void setDoctormcis(List<DoctorMciDTO> doctormcis) {
		this.doctormcis = doctormcis;
	}

	public DoctorMciDTO addDoctormci(DoctorMciDTO doctormci) {
		getDoctormcis().add(doctormci);
		doctormci.setMciMaster(this);

		return doctormci;
	}

	public DoctorMciDTO removeDoctormci(DoctorMciDTO doctormci) {
		getDoctormcis().remove(doctormci);
		doctormci.setMciMaster(null);

		return doctormci;
	}

}