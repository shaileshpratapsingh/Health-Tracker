package com.psquickit.managerImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psquickit.common.CommonErrorMessage;
import com.psquickit.common.CommonUtil;
import com.psquickit.common.UserNotFoundException;
import com.psquickit.dao.DoctorDegreeDAO;
import com.psquickit.dao.DoctorMciDAO;
import com.psquickit.dao.DoctorSpecializationDAO;
import com.psquickit.dao.DoctorUserDAO;
import com.psquickit.dao.UserDAO;
import com.psquickit.dto.DoctorDegreeDTO;
import com.psquickit.dto.DoctorMciDTO;
import com.psquickit.dto.DoctorSpecializationDTO;
import com.psquickit.dto.DoctorUserDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.LoginManager;
import com.psquickit.pojo.Degree;
import com.psquickit.pojo.Mci;
import com.psquickit.pojo.Speciliazation;
import com.psquickit.pojo.UserLoginResponse;

@Service
public class LoginManagerImpl implements LoginManager {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	DoctorUserDAO doctorUserDAO;
	
	@Autowired
	DoctorDegreeDAO doctorDegreeDAO;
	
	@Autowired
	DoctorSpecializationDAO doctorSpecializationDAO;
	
	@Autowired
	DoctorMciDAO doctorMciDAO;
	
	@Override
	public UserLoginResponse login(String uid) throws Exception {
		UserDTO userDTO = userDAO.checkUIDExist(uid);
		if(userDTO == null){
			throw new UserNotFoundException(CommonErrorMessage.USER_NOT_FOUND_CODE, CommonErrorMessage.USER_ALREADY_EXIST_DESC);
		}
		UserLoginResponse response = CommonUtil.mapDtoToResponse(userDTO);
		if(response.getUserDetails().getUserType() == "DoctorUser") {
			DoctorUserDTO doctorUserDTO = doctorUserDAO.getDetailOfDoctorUser(response.getUserDetails().getUid());
			//response.setUserDetails(value);
			List<DoctorDegreeDTO> listDoctorDegreeDTO = doctorDegreeDAO.getDoctorDegrees(doctorUserDTO.getId());
			List<DoctorSpecializationDTO> listDoctorSpecializationDTO = doctorSpecializationDAO.getDoctorSpecializations(doctorUserDTO.getId());
			List<DoctorMciDTO> listDoctorMciDTO = doctorMciDAO.getDoctorMCIs(doctorUserDTO.getId());
			mapDoctorDegreesToResponse(response, listDoctorDegreeDTO);
			mapDoctorMCIsToResponse(response, listDoctorMciDTO);
			mapDoctorSpecializationsToResponse(response, listDoctorSpecializationDTO);
		}
		return response;
	}
	
	private void mapDoctorDegreesToResponse(UserLoginResponse response, List<DoctorDegreeDTO> listDoctorDegreeDTO){
		for(DoctorDegreeDTO doctorDegreeDTO : listDoctorDegreeDTO) {
			Degree degree = new Degree();
			degree.setId(doctorDegreeDTO.getDegreeMasterDTO().getId().toString());
			degree.setTitle(doctorDegreeDTO.getDegreeMasterDTO().getDegreeName());
			response.getUserDetails().getDegrees().add(degree);
		}
	}
	
	private void mapDoctorSpecializationsToResponse(UserLoginResponse response, List<DoctorSpecializationDTO> listDoctorSpecializationDTO){
		for(DoctorSpecializationDTO doctorSpecializationDTO :listDoctorSpecializationDTO) {
			Speciliazation specialization = new Speciliazation();
			specialization.setId(doctorSpecializationDTO.getSpecializationMaster().getId().toString());
			specialization.setTitle(doctorSpecializationDTO.getSpecializationMaster().getSpecializationName());
			response.getUserDetails().getSpecialization().add(specialization);
		}
	}
	
	private void mapDoctorMCIsToResponse(UserLoginResponse response, List<DoctorMciDTO> listDoctorMciDTO){
		for(DoctorMciDTO doctorMciDTO :listDoctorMciDTO) {
			Mci mci = new Mci();
			mci.setId(doctorMciDTO.getMciMasterId().getId().toString());
			mci.setTitle(doctorMciDTO.getMciMasterId().getMciName());
			mci.setRegistrationNumber(doctorMciDTO.getRegistrationNumber().toString());
			response.getUserDetails().getMciReg().add(mci);
		}
	}

}
