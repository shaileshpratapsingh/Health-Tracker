package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the testreport database table.
 * 
 */
@Entity
@Table(name="Testreport")
@NamedQuery(name="Testreport.findAll", query="SELECT t FROM TestReportDTO t")
public class TestReportDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Long createdBy;

	private Timestamp createdOn;

	private Long testName;

	private Long updatedBy;

	private Timestamp updatedOn;

	//bi-directional many-to-one association to Testreportparameter
	@OneToMany(mappedBy="testreport")
	private List<TestReportParameterDTO> testreportparameters;

	//bi-directional many-to-one association to Usertestreport
	@OneToMany(mappedBy="testreport")
	private List<UserTestReportDTO> usertestreports;

	public TestReportDTO() {
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

	public Long getTestName() {
		return this.testName;
	}

	public void setTestName(Long testName) {
		this.testName = testName;
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

	public List<TestReportParameterDTO> getTestreportparameters() {
		return this.testreportparameters;
	}

	public void setTestreportparameters(List<TestReportParameterDTO> testreportparameters) {
		this.testreportparameters = testreportparameters;
	}

	public TestReportParameterDTO addTestreportparameter(TestReportParameterDTO testreportparameter) {
		getTestreportparameters().add(testreportparameter);
		testreportparameter.setTestreport(this);

		return testreportparameter;
	}

	public TestReportParameterDTO removeTestreportparameter(TestReportParameterDTO testreportparameter) {
		getTestreportparameters().remove(testreportparameter);
		testreportparameter.setTestreport(null);

		return testreportparameter;
	}

	public List<UserTestReportDTO> getUsertestreports() {
		return this.usertestreports;
	}

	public void setUsertestreports(List<UserTestReportDTO> usertestreports) {
		this.usertestreports = usertestreports;
	}

	public UserTestReportDTO addUsertestreport(UserTestReportDTO usertestreport) {
		getUsertestreports().add(usertestreport);
		usertestreport.setTestreport(this);

		return usertestreport;
	}

	public UserTestReportDTO removeUsertestreport(UserTestReportDTO usertestreport) {
		getUsertestreports().remove(usertestreport);
		usertestreport.setTestreport(null);

		return usertestreport;
	}

}