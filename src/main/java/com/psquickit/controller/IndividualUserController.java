package com.psquickit.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psquickit.manager.IndividualUserManager;
import com.psquickit.pojo.user.IndividualUserRegisterRequest;
import com.psquickit.pojo.user.IndividualUserRegisterResponse;
import com.psquickit.pojo.user.IndividualUserUpdateRequest;
import com.psquickit.pojo.user.IndividualUserUpdateResponse;
import com.psquickit.pojo.user.UserDetailResponse;
import com.psquickit.util.ServiceUtils;

@RestController
@RequestMapping("/user")
public class IndividualUserController {

	@Autowired
	IndividualUserManager manager;

	@RequestMapping(value = "/register/multipart", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody IndividualUserRegisterResponse registerUser(
			@RequestHeader(value="secretToken", required=true) String secretToken,
			@RequestPart(value="profilePic", required=false) MultipartFile profilePic,
			@RequestPart(value = "userRegistration", required=true) IndividualUserRegisterRequest request) {
		IndividualUserRegisterResponse response = new IndividualUserRegisterResponse();
		try {
			response = manager.registerUser(secretToken, request, profilePic);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "User Registration", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody IndividualUserRegisterResponse registerUser(
			@RequestHeader(value="secretToken", required=true) String secretToken,
			@RequestBody IndividualUserRegisterRequest request) {
		IndividualUserRegisterResponse response = new IndividualUserRegisterResponse();
		try {
			response = manager.registerUser(secretToken, request);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "User Registration", e);
		}
		return response;
	}

	@RequestMapping(value = "/update/multipart", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody IndividualUserUpdateResponse updateUser(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestPart(value="profilePic", required=false) MultipartFile profilePic,
			@RequestPart(value = "userUpdate", required=true) IndividualUserUpdateRequest request) {
		IndividualUserUpdateResponse response = new IndividualUserUpdateResponse();
		try {
			response = manager.updateUser(authToken, request, profilePic);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "User Registration", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody IndividualUserUpdateResponse updateUser(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestBody IndividualUserUpdateRequest request) {
		IndividualUserUpdateResponse response = new IndividualUserUpdateResponse();
		try {
			response = manager.updateUser(authToken, request);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "User Registration", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/get/details", method = RequestMethod.GET)
	public @ResponseBody UserDetailResponse getUserDetails(
			@RequestHeader(value="authToken", required=true) String authToken) {
		UserDetailResponse response = new UserDetailResponse();
		try {
			response = manager.getUserDetail(authToken);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Get user details", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/get/profilepic", method = RequestMethod.GET)
	public void getProfilePhoto(
			@RequestHeader(value="authToken", required=true) String authToken,
			final HttpServletResponse httpResponse) {
		try {
			manager.getProfilePhoto(authToken, httpResponse);
		} catch (Exception e) {
			httpResponse.setHeader("Exception", e.getMessage());	
			httpResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}
}
