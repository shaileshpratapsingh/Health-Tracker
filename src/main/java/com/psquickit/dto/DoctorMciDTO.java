package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the doctormci database table.
 * 
 */
@Entity
@Table(name = "doctormci")
public class DoctorMciDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "DoctorUserId")
	private DoctorUserDTO doctorUserDTO;
	
	@ManyToOne
	@JoinColumn(name = "MciMasterId")
	private MciMasterDTO mCIMasterDTO;

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

	public MciMasterDTO getmCIMasterDTO() {
		return mCIMasterDTO;
	}

	public void setmCIMasterDTO(MciMasterDTO mCIMasterDTO) {
		this.mCIMasterDTO = mCIMasterDTO;
	}
	 
}