package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the healthrecord database table.
 * 
 */
@Entity
@Table(name="Healthrecord")
@NamedQuery(name="Healthrecord.findAll", query="SELECT h FROM HealthRecordDTO h")
public class HealthRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Long createdBy;

	private Timestamp createdOn;

	private Date recordDate;

	private Long updatedBy;

	private Timestamp updatedOn;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserId")
	private UserDTO user;

	//bi-directional many-to-one association to Sharedhealthrecord
	@OneToMany(mappedBy="healthrecord")
	private List<SharedHealthRecordDTO> sharedhealthrecords;

	//bi-directional many-to-one association to Userdiagnosisreport
	@OneToMany(mappedBy="healthrecord")
	private List<UserDiagnosisReportDTO> userdiagnosisreports;

	//bi-directional many-to-one association to Userprescription
	@OneToMany(mappedBy="healthrecord")
	private List<UserPrescriptionDTO> userprescriptions;

	//bi-directional many-to-one association to Usertestreport
	@OneToMany(mappedBy="healthrecord")
	private List<UserTestReportDTO> usertestreports;

	public HealthRecordDTO() {
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

	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
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

	public UserDTO getUser() {
		return this.user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<SharedHealthRecordDTO> getSharedhealthrecords() {
		return this.sharedhealthrecords;
	}

	public void setSharedhealthrecords(List<SharedHealthRecordDTO> sharedhealthrecords) {
		this.sharedhealthrecords = sharedhealthrecords;
	}

	public SharedHealthRecordDTO addSharedhealthrecord(SharedHealthRecordDTO sharedhealthrecord) {
		getSharedhealthrecords().add(sharedhealthrecord);
		sharedhealthrecord.setHealthrecord(this);

		return sharedhealthrecord;
	}

	public SharedHealthRecordDTO removeSharedhealthrecord(SharedHealthRecordDTO sharedhealthrecord) {
		getSharedhealthrecords().remove(sharedhealthrecord);
		sharedhealthrecord.setHealthrecord(null);

		return sharedhealthrecord;
	}

	public List<UserDiagnosisReportDTO> getUserdiagnosisreports() {
		return this.userdiagnosisreports;
	}

	public void setUserdiagnosisreports(List<UserDiagnosisReportDTO> userdiagnosisreports) {
		this.userdiagnosisreports = userdiagnosisreports;
	}

	public UserDiagnosisReportDTO addUserdiagnosisreport(UserDiagnosisReportDTO userdiagnosisreport) {
		getUserdiagnosisreports().add(userdiagnosisreport);
		userdiagnosisreport.setHealthrecord(this);

		return userdiagnosisreport;
	}

	public UserDiagnosisReportDTO removeUserdiagnosisreport(UserDiagnosisReportDTO userdiagnosisreport) {
		getUserdiagnosisreports().remove(userdiagnosisreport);
		userdiagnosisreport.setHealthrecord(null);

		return userdiagnosisreport;
	}

	public List<UserPrescriptionDTO> getUserprescriptions() {
		return this.userprescriptions;
	}

	public void setUserprescriptions(List<UserPrescriptionDTO> userprescriptions) {
		this.userprescriptions = userprescriptions;
	}

	public UserPrescriptionDTO addUserprescription(UserPrescriptionDTO userprescription) {
		getUserprescriptions().add(userprescription);
		userprescription.setHealthrecord(this);

		return userprescription;
	}

	public UserPrescriptionDTO removeUserprescription(UserPrescriptionDTO userprescription) {
		getUserprescriptions().remove(userprescription);
		userprescription.setHealthrecord(null);

		return userprescription;
	}

	public List<UserTestReportDTO> getUsertestreports() {
		return this.usertestreports;
	}

	public void setUsertestreports(List<UserTestReportDTO> usertestreports) {
		this.usertestreports = usertestreports;
	}

	public UserTestReportDTO addUsertestreport(UserTestReportDTO usertestreport) {
		getUsertestreports().add(usertestreport);
		usertestreport.setHealthrecord(this);

		return usertestreport;
	}

	public UserTestReportDTO removeUsertestreport(UserTestReportDTO usertestreport) {
		getUsertestreports().remove(usertestreport);
		usertestreport.setHealthrecord(null);

		return usertestreport;
	}

}