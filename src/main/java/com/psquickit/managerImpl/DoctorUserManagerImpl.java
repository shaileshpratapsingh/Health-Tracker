package com.psquickit.managerImpl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.psquickit.common.CommonUtil;
import com.psquickit.common.DuplicateUserException;
import com.psquickit.dao.DegreeMasterDAO;
import com.psquickit.dao.DoctorDegreeDAO;
import com.psquickit.dao.DoctorMciDAO;
import com.psquickit.dao.DoctorSpecializationDAO;
import com.psquickit.dao.DoctorUserDAO;
import com.psquickit.dao.MCIMasterDAO;
import com.psquickit.dao.SpecializationMasterDAO;
import com.psquickit.dao.UserDAO;
import com.psquickit.dto.DegreeMasterDTO;
import com.psquickit.dto.DoctorDegreeDTO;
import com.psquickit.dto.DoctorMciDTO;
import com.psquickit.dto.DoctorSpecializationDTO;
import com.psquickit.dto.DoctorUserDTO;
import com.psquickit.dto.MCIMasterDTO;
import com.psquickit.dto.SpecializationMasterDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.DoctorUserManager;
import com.psquickit.pojo.Degree;
import com.psquickit.pojo.DoctorUserRegisterRequest;
import com.psquickit.pojo.DoctorUserResponse;
import com.psquickit.pojo.ListAllDegreeResponse;
import com.psquickit.pojo.ListAllMciResponse;
import com.psquickit.pojo.ListAllSpecializationResponse;
import com.psquickit.pojo.Mci;
import com.psquickit.pojo.Speciliazation;
import com.psquickit.util.ServiceUtils;

@Service
public class DoctorUserManagerImpl implements DoctorUserManager {
	
	private static Logger logger = Logger.getLogger(DoctorUserManagerImpl.class);
	
	@Autowired
	DoctorUserDAO doctorUserDAO;
	
	@Autowired
	DegreeMasterDAO degreeMasterDAO;
	
	@Autowired
	MCIMasterDAO mciMasterDAO;
	
	@Autowired
	SpecializationMasterDAO specializationMasterDAO;
	
	@Autowired
	DoctorSpecializationDAO doctorSpecializationDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	DoctorDegreeDAO doctorDegreeDAO;
	
	@Autowired
	DoctorMciDAO doctorMciDAO;
	
	@Override
	public DoctorUserResponse registerUser(DoctorUserRegisterRequest request) throws Exception {
		logger.info("Reaching in manager");
		UserDTO userDTO = userDAO.checkUIDExist(request.getDoctorUser().getUid());
		if(userDTO != null){
			throw new DuplicateUserException("Duplicate User");
		}
		DoctorUserDTO dto = mapDoctorRegisterRequest(request);
		
		dto = doctorUserDAO.save(dto);
		
		//Doctor specialization
		List<DoctorSpecializationDTO> listDoctorSpecializationDTO = Lists.newArrayList();
		for(Speciliazation speciliazation : request.getDoctorUser().getSpecialization()) {
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
		
		return ServiceUtils.setResponse(new DoctorUserResponse(), 
				true, "Register Doctor User");
	}
	private DoctorUserDTO mapDoctorRegisterRequest (DoctorUserRegisterRequest request){
		UserDTO dto = CommonUtil.mapRequestToDTO(request.getDoctorUser());
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
		List<MCIMasterDTO> mciMasterDTO =  mciMasterDAO.findAll();
		List list =  Lists.newArrayList();
		for(MCIMasterDTO mci : mciMasterDTO){
			list.add(mci.getId());
			list.add(mci.getMciName());
		}
		mciList.getMcis().addAll(list);
		return mciList;
	}

	@Override
	public ListAllSpecializationResponse listAllSpecialization() throws Exception {
		ListAllSpecializationResponse specializationList = new ListAllSpecializationResponse();
		List<SpecializationMasterDTO> splMasterDTO =  specializationMasterDAO.findAll();
		List list =  Lists.newArrayList();
		for(SpecializationMasterDTO spiDTO : splMasterDTO){
			list.add(spiDTO.getId());
			list.add(spiDTO.getSpecializationName());
		}
		specializationList.getSpeciliazations().addAll(list);
		return specializationList;
	}
	
}
