package com.psquickit.managerImpl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.psquickit.dto.DegreeMasterDTO;
import com.psquickit.dto.DoctorDegreeDTO;
import com.psquickit.dto.DoctorMciDTO;
import com.psquickit.dto.DoctorSpecializationDTO;
import com.psquickit.dto.DoctorUserDTO;
import com.psquickit.dto.MciMasterDTO;
import com.psquickit.dto.SpecializationMasterDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.CommonManager;
import com.psquickit.manager.DoctorUserManager;
import com.psquickit.pojo.Degree;
import com.psquickit.pojo.DoctorUser;
import com.psquickit.pojo.DoctorUserRegisterRequest;
import com.psquickit.pojo.DoctorUserRegisterResponse;
import com.psquickit.pojo.DoctorUserUpdateRequest;
import com.psquickit.pojo.DoctorUserUpdateResponse;
import com.psquickit.pojo.ListAllDegreeResponse;
import com.psquickit.pojo.ListAllMciResponse;
import com.psquickit.pojo.ListAllSpecializationResponse;
import com.psquickit.pojo.Mci;
import com.psquickit.pojo.Specialization;
import com.psquickit.util.ServiceUtils;

@Service
public class DoctorUserManagerImpl extends CommonManager implements DoctorUserManager {
	
	private static Logger logger = Logger.getLogger(DoctorUserManagerImpl.class);
	
	@Override
	public DoctorUserRegisterResponse registerUser(DoctorUserRegisterRequest request) throws Exception {
		logger.info("Reaching in manager");
		DoctorUserRegisterResponse response = new DoctorUserRegisterResponse();
		UserDTO userDTO = userDAO.checkUIDExist(request.getDoctorUser().getUid());
		if(userDTO != null){
			throw new Exception("User already exist. Please try again with different UID");
		}
		DoctorUserDTO doctorUserDTO = mapDoctorRegisterRequest(request.getDoctorUser(), null, null);
		
		doctorUserDTO = doctorUserDAO.save(doctorUserDTO);
		saveDoctorDegrees(request.getDoctorUser().getDegrees(), doctorUserDTO);
		saveDoctorMcis(request.getDoctorUser().getMciReg(), doctorUserDTO);
		saveDoctorSpecializations(request.getDoctorUser().getSpecialization(), doctorUserDTO);
		response.setId(doctorUserDTO.getUserDTO().getUid());
		return ServiceUtils.setResponse(response, 
				true, "Register Doctor User");
	}
	
	private void saveDoctorSpecializations(List<Specialization> listSpecialization, DoctorUserDTO doctorUserDTO) {
		//Doctor specialization
		List<DoctorSpecializationDTO> listDoctorSpecializationDTO = Lists.newArrayList();
		for(Specialization speciliazation : listSpecialization) {
			DoctorSpecializationDTO doctorSpecializationDTO = new DoctorSpecializationDTO();
			doctorSpecializationDTO.setDoctorUser(doctorUserDTO);
			doctorSpecializationDTO.setSpecializationMaster(specializationMasterDAO.findOne(Long.parseLong(speciliazation.getId())));
			listDoctorSpecializationDTO.add(doctorSpecializationDTO);
		}
		doctorSpecializationDAO.save(listDoctorSpecializationDTO);
	}
	
	private void saveDoctorDegrees(List<Degree> listDegree, DoctorUserDTO doctorUserDTO){
		List<DoctorDegreeDTO> listDoctorDegreeDTO = Lists.newArrayList();
		for(Degree degree : listDegree) {
			DoctorDegreeDTO doctorDegreeDTO = new DoctorDegreeDTO();
			doctorDegreeDTO.setDoctorUserDTO(doctorUserDTO);
			doctorDegreeDTO.setDegreeMasterDTO(degreeMasterDAO.findOne(Long.parseLong(degree.getId())));
			listDoctorDegreeDTO.add(doctorDegreeDTO);
		}
		
		doctorDegreeDAO.save(listDoctorDegreeDTO);
	}
	
	private void saveDoctorMcis(List<Mci> listMCI, DoctorUserDTO doctorUserDTO) {

		List<DoctorMciDTO> listDoctorMciDTO = Lists.newArrayList();
		for(Mci mci : listMCI) {
			DoctorMciDTO doctorMciDTO = new DoctorMciDTO();
			doctorMciDTO.setDoctorUserDTO(doctorUserDTO);
			doctorMciDTO.setMciMasterId(mciMasterDAO.findOne(Long.parseLong(mci.getId())));
			doctorMciDTO.setRegistrationNumber(Long.parseLong(mci.getRegistrationNumber()));
			listDoctorMciDTO.add(doctorMciDTO);
		}
		
		doctorMciDAO.save(listDoctorMciDTO);
	}
	
	private DoctorUserDTO mapDoctorRegisterRequest (DoctorUser user, UserDTO userDTO, DoctorUserDTO doctorUserDTO){
		UserDTO dto = (userDTO == null) ? mapUserRequestToDTO(user, null) : mapUserRequestToDTO(user, userDTO);
		if(doctorUserDTO == null) doctorUserDTO = new DoctorUserDTO();
		doctorUserDTO.setUserDTO(dto);
		if(user.getClinicAddress() != null) doctorUserDTO.setClinicAddress(user.getClinicAddress());
		if(user.getClinicContactNo() != null) doctorUserDTO.setClinicContactNumber(user.getClinicContactNo());
		if(user.getAlternateContactNo() != null) doctorUserDTO.setClinicAlternateContactNumber(user.getAlternateContactNo());
		if(user.getPracticeArea()!= null) doctorUserDTO.setPracticeArea(user.getPracticeArea());
		if(user.getInPersonConsultant() != null) doctorUserDTO.setInPersonConsultant(user.getInPersonConsultant());
		if(user.getEConsultant() != null) doctorUserDTO.seteConsultant(user.getEConsultant());
		return doctorUserDTO;
	}

	@Override
	public ListAllDegreeResponse listAllDegree() throws Exception {
		ListAllDegreeResponse degreeResponse = new ListAllDegreeResponse();
		List<DegreeMasterDTO> degreeMasterDTO =  degreeMasterDAO.findAll();
		List<Degree> list =  Lists.newArrayList();
		for(DegreeMasterDTO degrees : degreeMasterDTO){
			Degree degree =  new Degree();
			degree.setId(degrees.getId().toString());
			degree.setTitle(degrees.getDegreeName());
			list.add(degree);
		}
		degreeResponse.getDegrees().addAll(list);
		return ServiceUtils.setResponse(degreeResponse, true, "List all degrees");
	}

	@Override
	public ListAllMciResponse listAllMci() throws Exception {
		ListAllMciResponse mciResponse = new ListAllMciResponse();
		List<MciMasterDTO> listMciMasterDTO =  mciMasterDAO.findAll();
		List<Mci> listMci =  Lists.newArrayList();
		for(MciMasterDTO mciMatserDTO : listMciMasterDTO){
			Mci mci = new Mci();
			mci.setId(mciMatserDTO.getId().toString());
			mci.setTitle(mciMatserDTO.getMciName());
			listMci.add(mci);
		}
		mciResponse.getMcis().addAll(listMci);
		return ServiceUtils.setResponse(mciResponse, true, "List All Mci");
	}

	@Override
	public ListAllSpecializationResponse listAllSpecialization() throws Exception {
		ListAllSpecializationResponse specializationResponse = new ListAllSpecializationResponse();
		List<SpecializationMasterDTO> listSplMasterDTO =  specializationMasterDAO.findAll();
		List<Specialization> listSpecialization =  Lists.newArrayList();
		for(SpecializationMasterDTO spiDTO : listSplMasterDTO){
			Specialization specialization = new Specialization();
			specialization.setId(spiDTO.getId().toString());
			specialization.setTitle(spiDTO.getSpecializationName());
			listSpecialization.add(specialization);
		}
		specializationResponse.getSpeciliazations().addAll(listSpecialization);
		return ServiceUtils.setResponse(specializationResponse, true, "List all specialization");
	}
	
	@Override
	public DoctorUserUpdateResponse updateUser(DoctorUserUpdateRequest request) throws Exception {
		//UserDTO userDTO = userDAO.checkUIDExist(request.getDoctorUser().getUid());
		DoctorUserDTO doctorUserDTO = doctorUserDAO.getDetailOfDoctorUser(request.getDoctorUser().getUid());
		if(doctorUserDTO == null){
			throw new Exception("User does not exist");
		}
		DoctorUserUpdateResponse response = new DoctorUserUpdateResponse();
		//Check if User mandatory fields need to be updated
		if(request.getDoctorUser().getUserType() != null){
			UserDTO userDTO = mapUserRequestToDTO(request.getDoctorUser(), doctorUserDTO.getUserDTO());
			response.setId(userDTO.getUid());
			userDTO = userDAO.save(userDTO);
			return ServiceUtils.setResponse(response, true, "Update doctor user mentatory details");
		}else{
			doctorUserDTO = mapDoctorRegisterRequest(request.getDoctorUser(), doctorUserDTO.getUserDTO(), doctorUserDTO);
			doctorUserDAO.save(doctorUserDTO);
			if(request.getDoctorUser().getDegrees() != null){
				saveDoctorDegrees(request.getDoctorUser().getDegrees(), doctorUserDTO);
			}
			if(request.getDoctorUser().getMciReg() != null){
				saveDoctorMcis(request.getDoctorUser().getMciReg(), doctorUserDTO);
			}
			if(request.getDoctorUser().getSpecialization() != null){
				saveDoctorSpecializations(request.getDoctorUser().getSpecialization(), doctorUserDTO);
			}
			response.setId(doctorUserDTO.getUserDTO().getUid());
			return ServiceUtils.setResponse(response, true, "Update doctor user details");
		}
	}
	
}
