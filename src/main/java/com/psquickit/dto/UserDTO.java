package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "FirstName", length = 50)
	private String firstName;
	
	@Column(name = "LastName", length = 50)
	private String lastName;
	
	@Column(name = "Email", length = 50)
	private String email;
	
	@Column(name = "ContactNumber",  length = 50)
	private String contactNumber;
	
	@Column(name = "AlternateContactNumber", length = 50)
	private String alternateContactNumber;
	
	@Column(name = "PermanentAddress", length = 500)
	private String permanentAddress;
	
	@Column(name = "AlternateAddress", length = 500)
	private String alternateAddress;
	
	@Column(name = "ProfileImageFileStoreId", length = 50)
	private Long profileImageFileStoreId;
	
	@Column(name = "UserType", length = 500)
	private String userType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getProfileImageFileStoreId() {
		return profileImageFileStoreId;
	}

	public void setProfileImageFileStoreId(Long profileImageFileStoreId) {
		this.profileImageFileStoreId = profileImageFileStoreId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}