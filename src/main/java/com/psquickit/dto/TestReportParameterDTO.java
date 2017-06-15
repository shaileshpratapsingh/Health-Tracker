package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the testreportparameter database table.
 * 
 */
@Entity
@Table(name="Testreportparameter")
@NamedQuery(name="Testreportparameter.findAll", query="SELECT t FROM TestReportParameterDTO t")
public class TestReportParameterDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Long createdBy;

	private Timestamp createdOn;

	private String parameterName;

	private String unit;

	private Long updatedBy;

	private Timestamp updatedOn;

	private String value;

	private String valuesRange;

	//bi-directional many-to-one association to Testreport
	@ManyToOne
	@JoinColumn(name="TestReportId")
	private TestReportDTO testreport;

	//bi-directional many-to-one association to Usertestreport
	@OneToMany(mappedBy="testreportparameter")
	private List<UserTestReportDTO> usertestreports;

	public TestReportParameterDTO() {
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

	public String getParameterName() {
		return this.parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValuesRange() {
		return this.valuesRange;
	}

	public void setValuesRange(String valuesRange) {
		this.valuesRange = valuesRange;
	}

	public TestReportDTO getTestreport() {
		return this.testreport;
	}

	public void setTestreport(TestReportDTO testreport) {
		this.testreport = testreport;
	}

	public List<UserTestReportDTO> getUsertestreports() {
		return this.usertestreports;
	}

	public void setUsertestreports(List<UserTestReportDTO> usertestreports) {
		this.usertestreports = usertestreports;
	}

	public UserTestReportDTO addUsertestreport(UserTestReportDTO usertestreport) {
		getUsertestreports().add(usertestreport);
		usertestreport.setTestreportparameter(this);

		return usertestreport;
	}

	public UserTestReportDTO removeUsertestreport(UserTestReportDTO usertestreport) {
		getUsertestreports().remove(usertestreport);
		usertestreport.setTestreportparameter(null);

		return usertestreport;
	}

}