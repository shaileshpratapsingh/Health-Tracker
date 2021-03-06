package com.psquickit.managerImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psquickit.common.HandledException;
import com.psquickit.dao.UserDAO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.IndividualUserManager;
import com.psquickit.pojo.IndividualUserRegisterRequest;
import com.psquickit.pojo.IndividualUserRegisterResponse;
import com.psquickit.pojo.IndividualUserUpdateRequest;
import com.psquickit.pojo.IndividualUserUpdateResponse;
import com.psquickit.util.ServiceUtils;

@Service
public class IndividualUserManagerImpl implements IndividualUserManager {

	private static Logger logger = Logger.getLogger(IndividualUserManagerImpl.class);
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	public IndividualUserRegisterResponse registerUser(IndividualUserRegisterRequest request) throws Exception {
		logger.info("Reaching in manager");
		IndividualUserRegisterResponse response = new IndividualUserRegisterResponse();
		UserDTO userDTO = userDAO.checkUIDExist(request.getIndividualUser().getUid());
		if(userDTO != null) {
			throw new HandledException("USER_ALREADY_REGISTERED", "User already exist. Please try again with different UID");
		}
		userDTO = UserCommonManagerImpl.mapUserRequestToDTO(request.getIndividualUser(), null);
		userDAO.save(userDTO);
		response.setId(userDTO.getUid());
		return ServiceUtils.setResponse(response, true, "Register User");
	}

	@Override
	public IndividualUserUpdateResponse updateUser(IndividualUserUpdateRequest request) throws Exception {
		UserDTO userDTO = userDAO.checkUIDExist(request.getIndividualUser().getUid());
		if(userDTO == null) {
			throw new HandledException("USER_DOES_NOT_EXIST", "User does not exist.");
		}
		IndividualUserUpdateResponse response = new IndividualUserUpdateResponse();
		userDTO = UserCommonManagerImpl.mapUserRequestToDTO(request.getIndividualUser(), userDTO);
		userDAO.save(userDTO);
		response.setId(userDTO.getUid());
		return ServiceUtils.setResponse(response, true, "Update User");
	}	
}

