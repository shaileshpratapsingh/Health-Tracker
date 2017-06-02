package com.psquickit.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "AdhaarNumber", length = 50)
	private String uid;

	@Column(name = "FirstName", length = 50)
	private String firstName;

	@Column(name = "age", length = 50)
	private String age;

	@Column(name = "gender", length = 50)
	private String gender;

	@Column(name = "LastName", length = 50)
	private String lastName;

	@Column(name = "Email", length = 50)
	private String email;

	@Column(name = "ContactNumber", length = 50)
	private String contactNumber;

	@Column(name = "AlternateContactNumber", length = 50)
	private String alternateContactNumber;

	@Column(name = "PermanentAddress", length = 500)
	private String permanentAddress;

	@Column(name = "AlternateAddress", length = 500)
	private String alternateAddress;

	@OneToOne
	@JoinColumn(name = "ProfileImageFileStoreId")
	private FileStoreDTO profileImageFileStoreId;

	@Column(name = "UserType", length = 500)
	private String userType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}

	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getAlternateAddress() {
		return alternateAddress;
	}

	public void setAlternateAddress(String alternateAddress) {
		this.alternateAddress = alternateAddress;
	}

	public FileStoreDTO getProfileImageFileStoreId() {
		return profileImageFileStoreId;
	}

	public void setProfileImageFileStoreId(FileStoreDTO profileImageFileStoreId) {
		this.profileImageFileStoreId = profileImageFileStoreId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
