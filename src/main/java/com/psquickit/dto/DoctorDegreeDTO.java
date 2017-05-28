package com.psquickit.dto;

import java.io.Serializable;
import java.security.DomainLoadStoreParameter;

import javax.persistence.*;


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
	@JoinColumn(name="DoctorUserId")
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