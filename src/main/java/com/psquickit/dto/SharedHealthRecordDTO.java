package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the sharedhealthrecord database table.
 * 
 */
@Entity
@Table(name="Sharedhealthrecord")
@NamedQuery(name="Sharedhealthrecord.findAll", query="SELECT s FROM SharedHealthRecordDTO s")
public class SharedHealthRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Long createdBy;

	private Timestamp createdOn;

	private Long updatedBy;

	private Timestamp updatedOn;

	//bi-directional many-to-one association to Healthrecord
	@ManyToOne
	@JoinColumn(name="HealthRecordId")
	private HealthRecordDTO healthrecord;

	//bi-directional many-to-one association to Shareduserrecord
	@ManyToOne
	@JoinColumn(name="SharedUserRecordId")
	private SharedUserRecordDTO shareduserrecord;

	public SharedHealthRecordDTO() {
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

	public HealthRecordDTO getHealthrecord() {
		return this.healthrecord;
	}

	public void setHealthrecord(HealthRecordDTO healthrecord) {
		this.healthrecord = healthrecord;
	}

	public SharedUserRecordDTO getShareduserrecord() {
		return this.shareduserrecord;
	}

	public void setShareduserrecord(SharedUserRecordDTO shareduserrecord) {
		this.shareduserrecord = shareduserrecord;
	}

}