package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the degreemaster database table.
 * 
 */
@Entity
@Table(name="Degreemaster")
@NamedQuery(name="Degreemaster.findAll", query="SELECT d FROM DegreeMasterDTO d")
public class DegreeMasterDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String degreeName;

	private byte isActive;

	//bi-directional many-to-one association to Doctordegree
	@OneToMany(mappedBy="degreemaster")
	private List<DoctorDegreeDTO> doctordegrees;

	public DegreeMasterDTO() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDegreeName() {
		return this.degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public List<DoctorDegreeDTO> getDoctordegrees() {
		return this.doctordegrees;
	}

	public void setDoctordegrees(List<DoctorDegreeDTO> doctordegrees) {
		this.doctordegrees = doctordegrees;
	}

	public DoctorDegreeDTO addDoctordegree(DoctorDegreeDTO doctordegree) {
		getDoctordegrees().add(doctordegree);
		doctordegree.setDegreeMaster(this);

		return doctordegree;
	}

	public DoctorDegreeDTO removeDoctordegree(DoctorDegreeDTO doctordegree) {
		getDoctordegrees().remove(doctordegree);
		doctordegree.setDegreeMaster(null);

		return doctordegree;
	}

}