package com.psquickit.manager;

import com.psquickit.pojo.DoctorUserRegisterRequest;
import com.psquickit.pojo.DoctorUserRegisterResponse;
import com.psquickit.pojo.DoctorUserUpdateRequest;
import com.psquickit.pojo.DoctorUserUpdateResponse;

import com.psquickit.pojo.ListAllDegreeResponse;
import com.psquickit.pojo.ListAllMciResponse;
import com.psquickit.pojo.ListAllSpecializationResponse;

public interface DoctorUserManager {

	DoctorUserRegisterResponse registerUser(DoctorUserRegisterRequest request) throws Exception;
	DoctorUserUpdateResponse updateUser(DoctorUserUpdateRequest request) throws Exception;

	ListAllDegreeResponse listAllDegree() throws Exception;
	ListAllMciResponse listAllMci() throws Exception;
	ListAllSpecializationResponse listAllSpecialization() throws Exception;
	
}
