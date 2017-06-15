package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usertestreport database table.
 * 
 */
@Entity
@Table(name="Usertestreport")
@NamedQuery(name="Usertestreport.findAll", query="SELECT u FROM UserTestReportDTO u")
public class UserTestReportDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Long createdBy;

	private Timestamp createdOn;

	private Long updatedBy;

	private Timestamp updatedOn;

	//bi-directional many-to-one association to Testreportfile
	@OneToMany(mappedBy="usertestreport")
	private List<TestReportFileDTO> testreportfiles;

	//bi-directional many-to-one association to Healthrecord
	@ManyToOne
	@JoinColumn(name="HealthRecordId")
	private HealthRecordDTO healthrecord;

	//bi-directional many-to-one association to Testreportparameter
	@ManyToOne
	@JoinColumn(name="TestReporParameterId")
	private TestReportParameterDTO testreportparameter;

	//bi-directional many-to-one association to Testreport
	@ManyToOne
	@JoinColumn(name="TestReportId")
	private TestReportDTO testreport;

	public UserTestReportDTO() {
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

	public List<TestReportFileDTO> getTestreportfiles() {
		return this.testreportfiles;
	}

	public void setTestreportfiles(List<TestReportFileDTO> testreportfiles) {
		this.testreportfiles = testreportfiles;
	}

	public TestReportFileDTO addTestreportfile(TestReportFileDTO testreportfile) {
		getTestreportfiles().add(testreportfile);
		testreportfile.setUsertestreport(this);

		return testreportfile;
	}

	public TestReportFileDTO removeTestreportfile(TestReportFileDTO testreportfile) {
		getTestreportfiles().remove(testreportfile);
		testreportfile.setUsertestreport(null);

		return testreportfile;
	}

	public HealthRecordDTO getHealthrecord() {
		return this.healthrecord;
	}

	public void setHealthrecord(HealthRecordDTO healthrecord) {
		this.healthrecord = healthrecord;
	}

	public TestReportParameterDTO getTestreportparameter() {
		return this.testreportparameter;
	}

	public void setTestreportparameter(TestReportParameterDTO testreportparameter) {
		this.testreportparameter = testreportparameter;
	}

	public TestReportDTO getTestreport() {
		return this.testreport;
	}

	public void setTestreport(TestReportDTO testreport) {
		this.testreport = testreport;
	}

}