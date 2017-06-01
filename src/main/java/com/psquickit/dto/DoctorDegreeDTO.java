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

/**
 * The persistent class for the doctordegree database table.
 * 
 */
@Entity
@Table(name = "doctordegree")
public class DoctorDegreeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "DoctorUserId")
	private DoctorUserDTO doctorUserDTO;

	@ManyToOne
	@JoinColumn(name = "DegreeMasterId")
	private DegreeMasterDTO degreeMasterDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DoctorUserDTO getDoctorUserDTO() {
		return doctorUserDTO;
	}

	public void setDoctorUserDTO(DoctorUserDTO doctorUserDTO) {
		this.doctorUserDTO = doctorUserDTO;
	}

	public DegreeMasterDTO getDegreeMasterDTO() {
		return degreeMasterDTO;
	}

	public void setDegreeMasterDTO(DegreeMasterDTO degreeMasterDTO) {
		this.degreeMasterDTO = degreeMasterDTO;
	}

}