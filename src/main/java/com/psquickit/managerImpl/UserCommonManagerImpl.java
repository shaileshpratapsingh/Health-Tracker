package com.psquickit.managerImpl;

import com.psquickit.common.UserType;
import com.psquickit.dto.FileStoreDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.pojo.BasicUserDetails;
import com.psquickit.pojo.UserLoginResponse;

public class UserCommonManagerImpl {

	public static <T extends BasicUserDetails> UserDTO updateUserDTO(T userDetails, UserDTO userDTO, FileStoreDTO profilePicFileStoreDTO) {
		userDTO.setUid(userDetails.getUid());
		userDTO.setFirstName(userDetails.getFirstName());
		userDTO.setLastName(userDetails.getLastName());
		userDTO.setContactNumber(userDetails.getContactNo());
		userDTO.setAlternateContactNumber(userDetails.getAlternateContactNo());
		userDTO.setAlternateAddress(userDetails.getAlternateAddress());
		userDTO.setPermanentAddress(userDetails.getPermanentAddress());
		userDTO.setAge(userDetails.getAge());
		userDTO.setGender(userDetails.getGender());
		userDTO.setEmail(userDetails.getEmail());
		userDTO.setUserType(UserType.fromName(userDetails.getUserType()).getName());
		userDTO.setProfileImageFileStoreId(profilePicFileStoreDTO);
		return userDTO;
	}

	public static BasicUserDetails toBasicUserDetails(UserDTO userDTO) {
		BasicUserDetails details = new BasicUserDetails();
		details.setUid(userDTO.getUid());
		details.setFirstName(userDTO.getFirstName());
		details.setLastName(userDTO.getLastName());
		details.setContactNo(userDTO.getContactNumber());
		details.setAlternateContactNo(userDTO.getAlternateContactNumber());
		details.setAlternateAddress(userDTO.getAlternateAddress());
		details.setPermanentAddress(userDTO.getPermanentAddress());
		details.setAge(userDTO.getAge());
		details.setGender(userDTO.getGender());
		details.setEmail(userDTO.getEmail());
		details.setUserType(UserType.fromName(userDTO.getUserType()).getName());
		return details;
	}

	public static <T extends BasicUserDetails> UserDTO createUserDTO(T userDetails, FileStoreDTO profilePicFileStoreDTO) {
		UserDTO userDTO = new UserDTO();
		updateUserDTO(userDetails, userDTO, profilePicFileStoreDTO);		
		return userDTO;
	}
}
