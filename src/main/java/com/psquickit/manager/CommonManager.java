package com.psquickit.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.psquickit.common.UserType;
import com.psquickit.dao.DegreeMasterDAO;
import com.psquickit.dao.DoctorDegreeDAO;
import com.psquickit.dao.DoctorMciDAO;
import com.psquickit.dao.DoctorSpecializationDAO;
import com.psquickit.dao.DoctorUserDAO;
import com.psquickit.dao.MCIMasterDAO;
import com.psquickit.dao.SpecializationMasterDAO;
import com.psquickit.dao.UserDAO;
import com.psquickit.dto.FileStoreDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.pojo.DoctorUser;
import com.psquickit.pojo.User;
import com.psquickit.pojo.UserLoginResponse;

public abstract class CommonManager {
	
	@Autowired
	public DoctorUserDAO doctorUserDAO;
	
	@Autowired
	public DegreeMasterDAO degreeMasterDAO;
	
	@Autowired
	public MCIMasterDAO mciMasterDAO;
	
	@Autowired
	public SpecializationMasterDAO specializationMasterDAO;
	
	@Autowired
	public DoctorSpecializationDAO doctorSpecializationDAO;
	
	@Autowired
	public UserDAO userDAO;
	
	@Autowired
	public DoctorDegreeDAO doctorDegreeDAO;
	
	@Autowired
	public DoctorMciDAO doctorMciDAO;
	
	public UserDTO mapUserRequestToDTO (User user){
		UserDTO userDTO = new UserDTO();
		userDTO.setUid(user.getUid());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setContactNumber(user.getContactNo());
		userDTO.setAlternateContactNumber(user.getAlternateContactNo());
		userDTO.setAlternateAddress(user.getAlternateAddress());
		userDTO.setPermanentAddress(user.getPermanentAddress());
		userDTO.setAge(user.getAge());
		userDTO.setGender(user.getGender());
		userDTO.setEmail(user.getEmail());
		userDTO.setUserType(UserType.fromName(user.getUserType()).getName());
		
		//TODO- change logic to save file on filestore
		FileStoreDTO fileStoreDTO = new FileStoreDTO();
		fileStoreDTO.setDocumentType("ProfilePicture");
		fileStoreDTO.setLocation("c/image/image.jpg");
		userDTO.setProfileImageFileStoreId(fileStoreDTO);
		return userDTO;
	}
	
	
	public UserLoginResponse mapUserDTOToResponse (UserDTO userDTO){
		UserLoginResponse userResponse = new UserLoginResponse();
		DoctorUser user = new DoctorUser();
		user.setUid(userDTO.getUid());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setContactNo(userDTO.getContactNumber());
		user.setAlternateContactNo(userDTO.getAlternateContactNumber());
		user.setAlternateAddress(userDTO.getAlternateAddress());
		user.setPermanentAddress(userDTO.getPermanentAddress());
		user.setAge(userDTO.getAge());
		user.setGender(userDTO.getGender());
		user.setEmail(userDTO.getEmail());
		user.setUserType(UserType.fromName(userDTO.getUserType()).getName());
		user.setProfileImg("demo");
		userResponse.setUserDetails(user);
		return userResponse;
	}
}
