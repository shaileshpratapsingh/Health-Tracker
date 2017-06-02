package com.psquickit.manager;

import org.springframework.web.multipart.MultipartFile;

import com.psquickit.pojo.DoctorUserDetailResponse;
import com.psquickit.pojo.DoctorUserRegisterRequest;
import com.psquickit.pojo.DoctorUserRegisterResponse;
import com.psquickit.pojo.DoctorUserUpdateRequest;
import com.psquickit.pojo.DoctorUserUpdateResponse;

import com.psquickit.pojo.ListAllDegreeResponse;
import com.psquickit.pojo.ListAllMciResponse;
import com.psquickit.pojo.ListAllSpecializationResponse;

public interface DoctorUserManager {

	DoctorUserRegisterResponse registerUser(DoctorUserRegisterRequest request, MultipartFile profilePic)
			throws Exception;

	DoctorUserUpdateResponse updateUser(String authToken, DoctorUserUpdateRequest request, MultipartFile profilePic) throws Exception;

	ListAllDegreeResponse listAllDegree() throws Exception;

	ListAllMciResponse listAllMci() throws Exception;

	ListAllSpecializationResponse listAllSpecialization() throws Exception;
	
	DoctorUserDetailResponse getDoctorUserDetail(String authToken) throws Exception;

}
