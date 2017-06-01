package com.psquickit.managerImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import com.psquickit.dto.FileStoreDTO;
import com.psquickit.dto.MciMasterDTO;
import com.psquickit.dto.SpecializationMasterDTO;
import com.psquickit.dto.UserDTO;
import com.psquickit.manager.DoctorUserManager;
import com.psquickit.manager.FileStoreManager;
import com.psquickit.pojo.Degree;
import com.psquickit.pojo.DoctorUserDetails;
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
	
	@Autowired
	FileStoreManager fileStoreManager;

	@Override
	public DoctorUserRegisterResponse registerUser(DoctorUserRegisterRequest request, MultipartFile profilePic)
			throws Exception {
		UserDTO userDTO = userDAO.checkUIDExist(request.getUid());
		if (userDTO != null) {
			throw new HandledException("DUPLICATE_USER_REGISTRATION", "Duplicate User Registration");
		}
		
		FileStoreDTO profilePicFileStoreDTO = null;
		if (profilePic != null) {
			profilePicFileStoreDTO = fileStoreManager.uploadFile(profilePic.getInputStream(), profilePic.getContentType(), profilePic.getOriginalFilename());
		}
		
		DoctorUserDTO doctorUserDTO = createDoctorDTO(request, profilePicFileStoreDTO);
		doctorUserDTO = doctorUserDAO.save(doctorUserDTO);
		saveDoctorDegrees(request.getDegrees(), doctorUserDTO);
		saveDoctorMcis(request.getMciReg(), doctorUserDTO);
		saveDoctorSpecializations(request.getSpecialization(), doctorUserDTO);

		return ServiceUtils.setResponse(new DoctorUserRegisterResponse(), true, "Register Doctor User");
	}

	private void saveDoctorSpecializations(List<Specialization> listSpecialization, DoctorUserDTO doctorUserDTO) {
		List<DoctorSpecializationDTO> listDoctorSpecializationDTO = Lists.newArrayList();
		for (Specialization speciliazation : listSpecialization) {
			DoctorSpecializationDTO doctorSpecializationDTO = new DoctorSpecializationDTO();
			doctorSpecializationDTO.setDoctorUser(doctorUserDTO);
			doctorSpecializationDTO.setSpecializationMaster(specializationMasterDAO.findOne(Long.parseLong(speciliazation.getId())));
			listDoctorSpecializationDTO.add(doctorSpecializationDTO);
		}
		doctorSpecializationDAO.save(listDoctorSpecializationDTO);
	}

	private void saveDoctorDegrees(List<Degree> listDegree, DoctorUserDTO doctorUserDTO) {
		List<DoctorDegreeDTO> listDoctorDegreeDTO = Lists.newArrayList();
		for (Degree degree : listDegree) {
			DoctorDegreeDTO doctorDegreeDTO = new DoctorDegreeDTO();
			doctorDegreeDTO.setDoctorUserDTO(doctorUserDTO);
			doctorDegreeDTO.setDegreeMasterDTO(degreeMasterDAO.findOne(Long.parseLong(degree.getId())));
			listDoctorDegreeDTO.add(doctorDegreeDTO);
		}
		doctorDegreeDAO.save(listDoctorDegreeDTO);
	}

	private void saveDoctorMcis(List<Mci> listMCI, DoctorUserDTO doctorUserDTO) {
		List<DoctorMciDTO> listDoctorMciDTO = Lists.newArrayList();
		for (Mci mci : listMCI) {
			DoctorMciDTO doctorMciDTO = new DoctorMciDTO();
			doctorMciDTO.setDoctorUserDTO(doctorUserDTO);
			doctorMciDTO.setMciMasterId(mciMasterDAO.findOne(Long.parseLong(mci.getId())));
			//TODO: This needs to be corrected
			//doctorMciDTO.setRegistrationNumber(Long.parseLong(mci.getRegistrationNumber()));
			listDoctorMciDTO.add(doctorMciDTO);
		}
		doctorMciDAO.save(listDoctorMciDTO);
	}

	private <T extends DoctorUserDetails> DoctorUserDTO createDoctorDTO(T request, FileStoreDTO profilePicFileStoreDTO) {
		UserDTO userDTO = UserCommonManagerImpl.createUserDTO(request, profilePicFileStoreDTO);
		DoctorUserDTO doctorUserDTO = new DoctorUserDTO();
		return updateDoctorUserDTO(request, doctorUserDTO, userDTO);
	}

	@Override
	public ListAllDegreeResponse listAllDegree() throws Exception {
		ListAllDegreeResponse response = new ListAllDegreeResponse();
		List<DegreeMasterDTO> degreeMasterDTO = degreeMasterDAO.findAll();
		List<Degree> list = Lists.newArrayList();
		for (DegreeMasterDTO degrees : degreeMasterDTO) {
			Degree degree = new Degree();
			degree.setId(degrees.getId().toString());
			degree.setTitle(degrees.getDegreeName());
			list.add(degree);
		}
		response.getDegrees().addAll(list);
		return ServiceUtils.setResponse(response, true, "List all degrees");
	}

	@Override
	public ListAllMciResponse listAllMci() throws Exception {
		ListAllMciResponse response = new ListAllMciResponse();
		List<MciMasterDTO> listMciMasterDTO = mciMasterDAO.findAll();
		List<Mci> listMci = Lists.newArrayList();
		for (MciMasterDTO mciMatserDTO : listMciMasterDTO) {
			Mci mci = new Mci();
			mci.setId(mciMatserDTO.getId().toString());
			mci.setTitle(mciMatserDTO.getMciName());
			listMci.add(mci);
		}
		response.getMcis().addAll(listMci);
		return ServiceUtils.setResponse(response, true, "List all MCI");
	}

	@Override
	public ListAllSpecializationResponse listAllSpecialization() throws Exception {
		ListAllSpecializationResponse response = new ListAllSpecializationResponse();
		List<SpecializationMasterDTO> listSplMasterDTO = specializationMasterDAO.findAll();
		List<Specialization> listSpecialization = Lists.newArrayList();
		for (SpecializationMasterDTO spiDTO : listSplMasterDTO) {
			Specialization specialization = new Specialization();
			specialization.setId(spiDTO.getId().toString());
			specialization.setTitle(spiDTO.getSpecializationName());
			listSpecialization.add(specialization);
		}
		response.getSpeciliazations().addAll(listSpecialization);
		return ServiceUtils.setResponse(response, true, "List all specializations");
	}

	@Override
	public DoctorUserUpdateResponse updateUser(DoctorUserUpdateRequest request, MultipartFile profilePic)
			throws Exception {
		
		DoctorUserDTO doctorUserDTO = doctorUserDAO.getDoctorUserDetail(request.getUid());
		if (doctorUserDTO == null) {
			throw new HandledException("USER_DOES_NOT_EXIST", "User does not exist");
		}
		
		FileStoreDTO profilePicFileStoreDTO = doctorUserDTO.getUserDTO().getProfileImageFileStoreId();
		if (profilePic != null) {
			if (profilePicFileStoreDTO == null) {
				fileStoreManager.updateFile(profilePicFileStoreDTO, profilePic.getInputStream(), profilePic.getContentType(), profilePic.getOriginalFilename());
			} else {
				profilePicFileStoreDTO = fileStoreManager.uploadFile(profilePic.getInputStream(), profilePic.getContentType(), profilePic.getOriginalFilename());
			}
		}
		
		UserDTO userDTO = UserCommonManagerImpl.updateUserDTO(request, doctorUserDTO.getUserDTO(), profilePicFileStoreDTO);
		doctorUserDTO = updateDoctorUserDTO(request, doctorUserDTO, userDTO);
		doctorUserDAO.save(doctorUserDTO);
		
		updateDoctorDegrees(request.getDegrees(), doctorUserDTO);
		updateDoctorSpecialization(request.getSpecialization(), doctorUserDTO);
		updateDoctorMci(request.getMciReg(), doctorUserDTO);
		
		DoctorUserUpdateResponse response = new DoctorUserUpdateResponse();
		
		return ServiceUtils.setResponse(response, true, "Update doctor user details");
	}

	private void updateDoctorMci(List<Mci> mciReg, DoctorUserDTO doctorUserDTO) {
		// TODO Auto-generated method stub
		
	}

	private void updateDoctorSpecialization(List<Specialization> specialization, DoctorUserDTO doctorUserDTO) {
		// TODO Auto-generated method stub
		
	}

	private void updateDoctorDegrees(List<Degree> degrees, DoctorUserDTO doctorUserDTO) {
		
		
	}

	private <T extends DoctorUserDetails> DoctorUserDTO updateDoctorUserDTO(T request, DoctorUserDTO doctorUserDTO, UserDTO userDTO) {
		doctorUserDTO.setUserDTO(userDTO);
		doctorUserDTO.setClinicAddress(request.getClinicAddress());
		doctorUserDTO.setClinicContactNumber(request.getClinicContactNo());
		doctorUserDTO.setClinicAlternateContactNumber(request.getAlternateContactNo());
		doctorUserDTO.setPracticeArea(request.getPracticeArea());
		doctorUserDTO.setInPersonConsultant(request.getInPersonConsultant());
		doctorUserDTO.seteConsultant(request.getEConsultant());
		return doctorUserDTO;
	}
}
