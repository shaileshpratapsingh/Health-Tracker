package com.psquickit.manager;

import com.psquickit.pojo.DoctorUserRegisterRequest;
import com.psquickit.pojo.DoctorUserResponse;
import com.psquickit.pojo.ListAllDegreeResponse;
import com.psquickit.pojo.ListAllMciResponse;
import com.psquickit.pojo.ListAllSpecializationResponse;

public interface DoctorUserManager {

	DoctorUserResponse registerUser(DoctorUserRegisterRequest request) throws Exception;
	
	ListAllDegreeResponse listAllDegree() throws Exception;
	ListAllMciResponse listAllMci() throws Exception;
	ListAllSpecializationResponse listAllSpecialization() throws Exception;
	
}
