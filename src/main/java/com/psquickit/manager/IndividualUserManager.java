package com.psquickit.manager;

import com.psquickit.pojo.IndividualUserRegisterRequest;
import com.psquickit.pojo.IndividualUserRegisterResponse;
import com.psquickit.pojo.IndividualUserUpdateRequest;
import com.psquickit.pojo.IndividualUserUpdateResponse;


public interface IndividualUserManager {

	IndividualUserRegisterResponse registerUser(IndividualUserRegisterRequest request) throws Exception;
	IndividualUserUpdateResponse updateUser(IndividualUserUpdateRequest request) throws Exception;

}
