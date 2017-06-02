package com.psquickit.managerImpl;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psquickit.common.HandledException;
import com.psquickit.dao.UserDAO;
import com.psquickit.dto.FileStoreDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.AuthenticationManager;
import com.psquickit.manager.FileStoreManager;
import com.psquickit.manager.IndividualUserManager;
import com.psquickit.pojo.BasicUserDetails;
import com.psquickit.pojo.IndividualUserRegisterRequest;
import com.psquickit.pojo.IndividualUserRegisterResponse;
import com.psquickit.pojo.IndividualUserUpdateRequest;
import com.psquickit.pojo.IndividualUserUpdateResponse;
import com.psquickit.pojo.UserDetailResponse;
import com.psquickit.util.CommonUtils;
import com.psquickit.util.ServiceUtils;

@Service
public class IndividualUserManagerImpl implements IndividualUserManager {

	private static Logger logger = Logger.getLogger(IndividualUserManagerImpl.class);

	@Autowired
	UserDAO userDAO;

	@Autowired
	FileStoreManager fileStoreManager;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Override
	public IndividualUserRegisterResponse registerUser(IndividualUserRegisterRequest request,
			MultipartFile profilePic) throws Exception {
		IndividualUserRegisterResponse response = new IndividualUserRegisterResponse();
		UserDTO userDTO = userDAO.checkUIDExist(request.getUid());
		if (userDTO != null) {
			throw new HandledException("USER_ALREADY_REGISTERED",
					"User already exist. Please try again with different UID");
		}
		
		FileStoreDTO profilePicFileStoreDTO = null;
		if (profilePic != null) {
			profilePicFileStoreDTO = fileStoreManager.uploadFile(profilePic.getInputStream(), profilePic.getContentType(), profilePic.getOriginalFilename());
		}
		
		userDTO = UserCommonManagerImpl.createUserDTO(request, profilePicFileStoreDTO);
		userDAO.save(userDTO);
		response.setId(userDTO.getUid());
		
		return ServiceUtils.setResponse(response, true, "Register User");
	}

	@Override
	public IndividualUserUpdateResponse updateUser(String authToken, IndividualUserUpdateRequest request,
			MultipartFile profilePic) throws Exception {
		
		long userId = authManager.getUserId(authToken);
		
		UserDTO userDTO = userDAO.findOne(userId);
		if (userDTO == null) {
			throw new HandledException("USER_DOES_NOT_EXIST", "User does not exist.");
		}
		if (!request.getUid().equalsIgnoreCase(userDTO.getUid())) {
			throw new HandledException("CANNOT_UPDATE_UID", "Aadhaar number cannot be updated");
		}
		IndividualUserUpdateResponse response = new IndividualUserUpdateResponse();
		
		FileStoreDTO profilePicFileStoreDTO = userDTO.getProfileImageFileStoreId();
		if (profilePic != null) {
			if (profilePicFileStoreDTO == null) {
				fileStoreManager.updateFile(profilePicFileStoreDTO, profilePic.getInputStream(), profilePic.getContentType(), profilePic.getOriginalFilename());
			} else {
				profilePicFileStoreDTO = fileStoreManager.uploadFile(profilePic.getInputStream(), profilePic.getContentType(), profilePic.getOriginalFilename());
			}
		}
		
		userDTO = UserCommonManagerImpl.updateUserDTO(request, userDTO, profilePicFileStoreDTO);
		userDAO.save(userDTO);
		response.setId(userDTO.getUid());
		return ServiceUtils.setResponse(response, true, "Update User");
	}
	
	@Override
	public UserDetailResponse getUserDetail(String authToken) throws Exception {
		UserDetailResponse response = new UserDetailResponse();
		long userId = authManager.getUserId(authToken);
		UserDTO dto = userDAO.findOne(userId);
		response.setUserDetails(UserCommonManagerImpl.toBasicUserDetails(new BasicUserDetails(), dto));
		return ServiceUtils.setResponse(response, true, "Get User Details");
	}
	
	@Override
	public void getProfilePhoto(String authToken, HttpServletResponse httpResponse) throws Exception {
		long userId = authManager.getUserId(authToken);
		UserDTO dto = userDAO.findOne(userId);
		FileStoreDTO profilePicFileStoreDTO = dto.getProfileImageFileStoreId();
		httpResponse.setContentType(profilePicFileStoreDTO.getDocumentType());
		httpResponse.setHeader(CommonUtils.CONTENT_DISPOSITION, CommonUtils.CONTENT_DISPOSITION_ATTACHMENT + profilePicFileStoreDTO.getName());
		try (OutputStream outputStream = httpResponse.getOutputStream()) {
			outputStream.write(fileStoreManager.retrieveFile(profilePicFileStoreDTO).read());
		}
	}
	
}
