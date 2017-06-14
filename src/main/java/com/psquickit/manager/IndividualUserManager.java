package com.psquickit.manager;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.psquickit.pojo.user.IndividualUserRegisterRequest;
import com.psquickit.pojo.user.IndividualUserRegisterResponse;
import com.psquickit.pojo.user.IndividualUserUpdateRequest;
import com.psquickit.pojo.user.IndividualUserUpdateResponse;
import com.psquickit.pojo.user.UserDetailResponse;

public interface IndividualUserManager {

	IndividualUserRegisterResponse registerUser(String secretToken, IndividualUserRegisterRequest request, 
			MultipartFile profilePic) throws Exception;

	IndividualUserUpdateResponse updateUser(String authToken, IndividualUserUpdateRequest request,
			MultipartFile profilePic) throws Exception;

	UserDetailResponse getUserDetail(String authToken) throws Exception;

	void getProfilePhoto(String authToken, HttpServletResponse httpResponse) throws Exception;

	IndividualUserRegisterResponse registerUser(String secretToken, IndividualUserRegisterRequest request) throws Exception;

	IndividualUserUpdateResponse updateUser(String authToken, IndividualUserUpdateRequest request) throws Exception;

}
