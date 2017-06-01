package com.psquickit.manager;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.ByteSource;
import com.psquickit.pojo.IndividualUserRegisterRequest;
import com.psquickit.pojo.IndividualUserRegisterResponse;
import com.psquickit.pojo.IndividualUserUpdateRequest;
import com.psquickit.pojo.IndividualUserUpdateResponse;
import com.psquickit.pojo.UserDetailResponse;

public interface IndividualUserManager {

	IndividualUserRegisterResponse registerUser(IndividualUserRegisterRequest request, 
			MultipartFile profilePic) throws Exception;

	IndividualUserUpdateResponse updateUser(IndividualUserUpdateRequest request,
			MultipartFile profilePic) throws Exception;

	UserDetailResponse getUserDetail(String authToken) throws Exception;

	void getProfilePhoto(String authToken, HttpServletResponse httpResponse) throws Exception;

}
