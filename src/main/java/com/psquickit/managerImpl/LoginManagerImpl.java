package com.psquickit.managerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psquickit.common.HandledException;
import com.psquickit.dao.DegreeMasterDAO;
import com.psquickit.dao.DoctorDegreeDAO;
import com.psquickit.dao.DoctorMciDAO;
import com.psquickit.dao.DoctorSpecializationDAO;
import com.psquickit.dao.DoctorUserDAO;
import com.psquickit.dao.MCIMasterDAO;
import com.psquickit.dao.SpecializationMasterDAO;
import com.psquickit.dao.UserDAO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.AuthenticationManager;
import com.psquickit.manager.LoginManager;
import com.psquickit.pojo.UserLoginResponse;
import com.psquickit.util.ServiceUtils;

@Service
public class LoginManagerImpl implements LoginManager {

	@Autowired
	public UserDAO userDAO;

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
	public DoctorDegreeDAO doctorDegreeDAO;

	@Autowired
	public DoctorMciDAO doctorMciDAO;

	@Autowired
	public AuthenticationManager authManager;
	
	@Override
	public UserLoginResponse login(String uid) throws Exception {
		UserDTO userDTO = userDAO.checkUIDExist(uid);
		if (userDTO == null) {
			throw new HandledException("USER_DOES_NOT_EXIST", "User does not exist");
		}
		UserLoginResponse response = new UserLoginResponse();
		/*if (UserType.valueOf(response.getUserDetails().getUserType()) == UserType.DOCTOR_USER) {
			DoctorUserDTO doctorUserDTO = doctorUserDAO.getDetailOfDoctorUser(response.getUserDetails().getUid());
			// response.setUserDetails(value);
			List<DoctorDegreeDTO> listDoctorDegreeDTO = doctorDegreeDAO.getDoctorDegrees(doctorUserDTO.getId());
			List<DoctorSpecializationDTO> listDoctorSpecializationDTO = doctorSpecializationDAO
					.getDoctorSpecializations(doctorUserDTO.getId());
			List<DoctorMciDTO> listDoctorMciDTO = doctorMciDAO.getDoctorMCIs(doctorUserDTO.getId());
			mapDoctorDegreesToResponse(response, listDoctorDegreeDTO);
			mapDoctorMCIsToResponse(response, listDoctorMciDTO);
			mapDoctorSpecializationsToResponse(response, listDoctorSpecializationDTO);
		}*/
		// TODO: Generate authentication token
		String authToken = authManager.generateAuthToken(userDTO.getId());
		response.setAuthToken(authToken);
		return ServiceUtils.setResponse(response, true, "Login User");
	}
	

	/*private void mapDoctorDegreesToResponse(UserLoginResponse response, List<DoctorDegreeDTO> listDoctorDegreeDTO) {
		for (DoctorDegreeDTO doctorDegreeDTO : listDoctorDegreeDTO) {
			Degree degree = new Degree();
			degree.setId(doctorDegreeDTO.getDegreeMasterDTO().getId().toString());
			degree.setTitle(doctorDegreeDTO.getDegreeMasterDTO().getDegreeName());
			response.getUserDetails().getDegrees().add(degree);
		}
	}

	private void mapDoctorSpecializationsToResponse(UserLoginResponse response,
			List<DoctorSpecializationDTO> listDoctorSpecializationDTO) {
		for (DoctorSpecializationDTO doctorSpecializationDTO : listDoctorSpecializationDTO) {
			Specialization specialization = new Specialization();
			specialization.setId(doctorSpecializationDTO.getSpecializationMaster().getId().toString());
			specialization.setTitle(doctorSpecializationDTO.getSpecializationMaster().getSpecializationName());
			response.getUserDetails().getSpecialization().add(specialization);
		}
	}

	private void mapDoctorMCIsToResponse(UserLoginResponse response, List<DoctorMciDTO> listDoctorMciDTO) {
		for (DoctorMciDTO doctorMciDTO : listDoctorMciDTO) {
			Mci mci = new Mci();
			mci.setId(doctorMciDTO.getMciMasterId().getId().toString());
			mci.setTitle(doctorMciDTO.getMciMasterId().getMciName());
			mci.setRegistrationNumber(doctorMciDTO.getRegistrationNumber().toString());
			response.getUserDetails().getMciReg().add(mci);
		}
	}*/

}
