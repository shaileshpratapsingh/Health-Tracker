package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the degreemaster database table.
 * 
 */
@Entity
@Table(name = "degreemaster")
public class DegreeMasterDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "DegreeName")
	private String degreeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

}