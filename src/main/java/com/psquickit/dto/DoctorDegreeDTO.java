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
	
	
	@Column(name = "DegreeMasterId")
	private String abc;

}