package com.psquickit.managerImpl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.psquickit.common.DuplicateUserException;
import com.psquickit.dto.DegreeMasterDTO;
import com.psquickit.dto.DoctorDegreeDTO;
import com.psquickit.dto.DoctorMciDTO;
import com.psquickit.dto.DoctorSpecializationDTO;
import com.psquickit.dto.DoctorUserDTO;
import com.psquickit.dto.MCIMasterDTO;
import com.psquickit.dto.SpecializationMasterDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.CommonManager;
import com.psquickit.manager.DoctorUserManager;
import com.psquickit.pojo.Degree;
import com.psquickit.pojo.DoctorUserRegisterRequest;
import com.psquickit.pojo.DoctorUserRegisterResponse;
import com.psquickit.pojo.DoctorUserUpdateRequest;
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
		UserDTO userDTO = userDAO.checkUIDExist(request.getDoctorUser().getUid());
		if(userDTO != null){
			throw new DuplicateUserException("Duplicate User");
		}
		DoctorUserDTO dto = mapDoctorRegisterRequest(request);
		
		dto = doctorUserDAO.save(dto);
		
		//Doctor specialization
		List<DoctorSpecializationDTO> listDoctorSpecializationDTO = Lists.newArrayList();
		for(Specialization speciliazation : request.getDoctorUser().getSpecialization()) {
			DoctorSpecializationDTO doctorSpecializationDTO = new DoctorSpecializationDTO();
			doctorSpecializationDTO.setDoctorUser(dto);
			doctorSpecializationDTO.setSpecializationMaster(specializationMasterDAO.findOne(Long.parseLong(speciliazation.getId())));
			listDoctorSpecializationDTO.add(doctorSpecializationDTO);
		}
		doctorSpecializationDAO.save(listDoctorSpecializationDTO);
		
		//Doctor specialization
		List<DoctorDegreeDTO> listDoctorDegreeDTO = Lists.newArrayList();
		for(Degree degree : request.getDoctorUser().getDegrees()) {
			DoctorDegreeDTO doctorDegreeDTO = new DoctorDegreeDTO();
			doctorDegreeDTO.setDoctorUserDTO(dto);
			doctorDegreeDTO.setDegreeMasterDTO(degreeMasterDAO.findOne(Long.parseLong(degree.getId())));
			listDoctorDegreeDTO.add(doctorDegreeDTO);
		}
		
		doctorDegreeDAO.save(listDoctorDegreeDTO);
		
		List<DoctorMciDTO> listDoctorMciDTO = Lists.newArrayList();
		for(Mci mci : request.getDoctorUser().getMciReg()) {
			DoctorMciDTO doctorMciDTO = new DoctorMciDTO();
			doctorMciDTO.setDoctorUserDTO(dto);
			doctorMciDTO.setMciMasterId(mciMasterDAO.findOne(Long.parseLong(mci.getId())));
			doctorMciDTO.setRegistrationNumber(Long.parseLong(mci.getRegistrationNumber()));
			listDoctorMciDTO.add(doctorMciDTO);
		}
		
		doctorMciDAO.save(listDoctorMciDTO);
		
		return ServiceUtils.setResponse(new DoctorUserRegisterResponse(), 
				true, "Register Doctor User");
	}
	private DoctorUserDTO mapDoctorRegisterRequest (DoctorUserRegisterRequest request){
		UserDTO dto = mapUserRequestToDTO(request.getDoctorUser());
		DoctorUserDTO doctorUserDTO = new DoctorUserDTO();
		doctorUserDTO.setUserDTO(dto);
		doctorUserDTO.setClinicAddress(request.getDoctorUser().getClinicAddress());
		doctorUserDTO.setClinicContactNumber(request.getDoctorUser().getClinicContactNo());
		doctorUserDTO.setClinicAlternateContactNumber(null);
		doctorUserDTO.setPracticeArea(request.getDoctorUser().getPracticeArea());
		doctorUserDTO.setInPersonConsultant(request.getDoctorUser().getInPersonConsultant());
		doctorUserDTO.seteConsultant(request.getDoctorUser().getEConsultant());
		
		
		return doctorUserDTO;
	}

	@Override
	public ListAllDegreeResponse listAllDegree() throws Exception {
		ListAllDegreeResponse degreeList = new ListAllDegreeResponse();
		List<DegreeMasterDTO> degreeMasterDTO =  degreeMasterDAO.findAll();
		List<Degree> list =  Lists.newArrayList();
		for(DegreeMasterDTO degrees : degreeMasterDTO){
			Degree degree =  new Degree();
			degree.setId(degrees.getId().toString());
			degree.setTitle(degrees.getDegreeName());
			list.add(degree);
		}
		degreeList.getDegrees().addAll(list);
		return degreeList;
	}

	@Override
	public ListAllMciResponse listAllMci() throws Exception {
		ListAllMciResponse mciList = new ListAllMciResponse();
		List<MCIMasterDTO> listMciMasterDTO =  mciMasterDAO.findAll();
		List<Mci> listMci =  Lists.newArrayList();
		for(MCIMasterDTO mciMatserDTO : listMciMasterDTO){
			Mci mci = new Mci();
			mci.setId(mciMatserDTO.getId().toString());
			mci.setTitle(mciMatserDTO.getMciName());
			listMci.add(mci);
		}
		mciList.getMcis().addAll(listMci);
		return mciList;
	}

	@Override
	public ListAllSpecializationResponse listAllSpecialization() throws Exception {
		ListAllSpecializationResponse specializationList = new ListAllSpecializationResponse();
		List<SpecializationMasterDTO> listSplMasterDTO =  specializationMasterDAO.findAll();
		List<Specialization> listSpecialization =  Lists.newArrayList();
		for(SpecializationMasterDTO spiDTO : listSplMasterDTO){
			Specialization specialization = new Specialization();
			specialization.setId(spiDTO.getId().toString());
			specialization.setTitle(spiDTO.getSpecializationName());
			listSpecialization.add(specialization);
		}
		specializationList.getSpeciliazations().addAll(listSpecialization);
		return specializationList;
	}
	@Override
	public DoctorUserRegisterResponse updateUser(DoctorUserUpdateRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
