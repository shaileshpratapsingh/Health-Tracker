package com.psquickit.managerImpl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.psquickit.common.HandledException;
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
import com.psquickit.dto.MciMasterDTO;
import com.psquickit.dto.SpecializationMasterDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.DoctorUserManager;
import com.psquickit.pojo.Degree;
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
public class DoctorUserManagerImpl implements DoctorUserManager {
	
	private static Logger logger = Logger.getLogger(DoctorUserManagerImpl.class);
	
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
	
	@Override
	public DoctorUserRegisterResponse registerUser(DoctorUserRegisterRequest request) throws Exception {
		UserDTO userDTO = userDAO.checkUIDExist(request.getDoctorUser().getUid());
		if(userDTO != null){
			throw new HandledException("DUPLICATE_USER_REGISTRATION", 
					"Duplicate User Registration");
		}
		DoctorUserDTO dto = mapDoctorRegisterRequest(request);
		
		dto = doctorUserDAO.save(dto);
		
		//Doctor specialization
		List<DoctorSpecializationDTO> listDoctorSpecializationDTO = Lists.newArrayList();
		for(Specialization s : request.getDoctorUser().getSpecialization()) {
			DoctorSpecializationDTO doctorSpecializationDTO = new DoctorSpecializationDTO();
			doctorSpecializationDTO.setDoctorUser(dto);
			doctorSpecializationDTO.setSpecializationMaster(specializationMasterDAO.findOne(Long.parseLong(s.getId())));
			listDoctorSpecializationDTO.add(doctorSpecializationDTO);
		}
		doctorSpecializationDAO.save(listDoctorSpecializationDTO);
		
		//Doctor specialization
		List<DoctorDegreeDTO> listDoctorDegreeDTO = Lists.newArrayList();
		for(Degree d : request.getDoctorUser().getDegrees()) {
			DoctorDegreeDTO doctorDegreeDTO = new DoctorDegreeDTO();
			doctorDegreeDTO.setDoctorUserDTO(dto);
			doctorDegreeDTO.setDegreeMasterDTO(degreeMasterDAO.findOne(Long.parseLong(d.getId())));
			listDoctorDegreeDTO.add(doctorDegreeDTO);
		}		
		doctorDegreeDAO.save(listDoctorDegreeDTO);
		
		List<DoctorMciDTO> listDoctorMciDTO = Lists.newArrayList();
		for(Mci m : request.getDoctorUser().getMciReg()) {
			DoctorMciDTO doctorMciDTO = new DoctorMciDTO();
			doctorMciDTO.setDoctorUserDTO(dto);
			doctorMciDTO.setMciMasterId(mciMasterDAO.findOne(Long.parseLong(m.getId())));
			doctorMciDTO.setRegistrationNumber(Long.parseLong(m.getRegistrationNumber()));
			listDoctorMciDTO.add(doctorMciDTO);
		}
		
		doctorMciDAO.save(listDoctorMciDTO);
		
		return ServiceUtils.setResponse(new DoctorUserRegisterResponse(), 
				true, "Register Doctor User");
	}
	
	private DoctorUserDTO mapDoctorRegisterRequest(DoctorUserRegisterRequest request) {
		UserDTO dto = UserCommonManagerImpl.mapUserRequestToDTO(request.getDoctorUser());
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
		List<MciMasterDTO> listMciMasterDTO =  mciMasterDAO.findAll();
		List<Mci> listMci =  Lists.newArrayList();
		for(MciMasterDTO mciMatserDTO : listMciMasterDTO){
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
	public DoctorUserUpdateResponse updateUser(DoctorUserUpdateRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
