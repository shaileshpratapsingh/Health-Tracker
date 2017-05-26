package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mcimaster database table.
 * 
 */
@Entity
@Table(name = "MciMaster")
public class MciMasterDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "MciName")
	private String mciName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMciName() {
		return mciName;
	}

	public void setMciName(String mciName) {
		this.mciName = mciName;
	}
}