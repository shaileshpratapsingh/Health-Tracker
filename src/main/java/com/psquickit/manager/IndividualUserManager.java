package com.psquickit.manager;

import com.psquickit.dto.UserDTO;
import com.psquickit.pojo.IndividualUserRegisterRequest;
import com.psquickit.pojo.IndividualUserRegisterResponse;


public interface IndividualUserManager {

	IndividualUserRegisterResponse registerUser(IndividualUserRegisterRequest request) throws Exception;

}
