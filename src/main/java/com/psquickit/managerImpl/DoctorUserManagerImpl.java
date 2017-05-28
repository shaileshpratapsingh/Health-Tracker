package com.psquickit.managerImpl;
import java.util.List;
import org.apache.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.psquickit.common.CommonUtil;
import com.psquickit.dao.DegreeMasterDAO;
import com.psquickit.dao.DoctorUserDAO;
import com.psquickit.dao.MCIMasterDAO;
import com.psquickit.dao.SpecializationMasterDAO;
import com.psquickit.dto.DegreeMasterDTO;
import com.psquickit.dto.DoctorUserDTO;
import com.psquickit.dto.MCIMasterDTO;
import com.psquickit.dto.SpecializationMasterDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.DoctorUserManager;
import com.psquickit.pojo.DoctorUserRegisterRequest;
import com.psquickit.pojo.DoctorUserResponse;
import com.psquickit.pojo.ListAllDegreeResponse;
import com.psquickit.pojo.ListAllMciResponse;
import com.psquickit.pojo.ListAllSpecializationResponse;
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
	SpecializationMasterDAO specializationDAO;
	
	@Override
	public DoctorUserResponse registerUser(DoctorUserRegisterRequest request) throws Exception {
		logger.info("Reaching in manager");
		DoctorUserDTO dto = mapDoctorRegisterRequest(request);
		doctorUserDAO.saveAndFlush(dto);
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
		List list =  Lists.newArrayList();
		for(DegreeMasterDTO degrees : degreeMasterDTO){
			list.add(degrees.getId());
			list.add(degrees.getDegreeName());
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
		List<SpecializationMasterDTO> splMasterDTO =  specializationDAO.findAll();
		List list =  Lists.newArrayList();
		for(SpecializationMasterDTO spiDTO : splMasterDTO){
			list.add(spiDTO.getId());
			list.add(spiDTO.getSpecializationName());
		}
		specializationList.getSpeciliazations().addAll(list);
		return specializationList;
	}
	
}
