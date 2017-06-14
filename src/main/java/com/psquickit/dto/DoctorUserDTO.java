package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the doctoruser database table.
 * 
 */
@Entity
@Table(name = "doctoruser")
@NamedQuery(name = "DoctorUser.findAll", query = "SELECT d FROM DoctorUserDTO d")
public class DoctorUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name = "UserId")
	private UserDTO userDTO;

	@Column(name = "ClinicAddress")
	private String clinicAddress;

	@Column(name = "ClinicContactNumber")
	private String clinicContactNumber;

	@Column(name = "ClinicAlternateContactNumber")
	private String clinicAlternateContactNumber;

	@Column(name = "PracticeArea")
	private String practiceArea;

	@Column(name = "InPersonConsultant")
	private String inPersonConsultant;

	@Column(name = "EConsultant")
	private String eConsultant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getClinicContactNumber() {
		return clinicContactNumber;
	}

	public void setClinicContactNumber(String clinicContactNumber) {
		this.clinicContactNumber = clinicContactNumber;
	}

	public String getClinicAlternateContactNumber() {
		return clinicAlternateContactNumber;
	}

	public void setClinicAlternateContactNumber(String clinicAlternateContactNumber) {
		this.clinicAlternateContactNumber = clinicAlternateContactNumber;
	}

	public String getPracticeArea() {
		return practiceArea;
	}

	public void setPracticeArea(String practiceArea) {
		this.practiceArea = practiceArea;
	}

	public String getInPersonConsultant() {
		return inPersonConsultant;
	}

	public void setInPersonConsultant(String inPersonConsultant) {
		this.inPersonConsultant = inPersonConsultant;
	}

	public String geteConsultant() {
		return eConsultant;
	}

	public void seteConsultant(String eConsultant) {
		this.eConsultant = eConsultant;
	}

}