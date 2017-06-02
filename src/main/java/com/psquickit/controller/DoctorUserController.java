package com.psquickit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psquickit.manager.DoctorUserManager;
import com.psquickit.pojo.DoctorUserDetailResponse;
import com.psquickit.pojo.DoctorUserRegisterRequest;
import com.psquickit.pojo.DoctorUserRegisterResponse;
import com.psquickit.pojo.DoctorUserUpdateRequest;
import com.psquickit.pojo.DoctorUserUpdateResponse;
import com.psquickit.pojo.ListAllDegreeResponse;
import com.psquickit.pojo.ListAllMciResponse;
import com.psquickit.pojo.ListAllSpecializationResponse;
import com.psquickit.util.ServiceUtils;

@RestController
@RequestMapping("/user/doctor")
public class DoctorUserController {

	@Autowired
	DoctorUserManager manager;

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody DoctorUserRegisterResponse registerUser(
			@RequestPart(value="profilePic", required=false) MultipartFile profilePic,
			@RequestPart(value = "doctorRegistration", required=true) DoctorUserRegisterRequest request) {
		DoctorUserRegisterResponse response = new DoctorUserRegisterResponse();
		try {
			response = manager.registerUser(request, profilePic);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Doctor User Registration", e);
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody DoctorUserUpdateResponse updateUser(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestPart(value="profilePic", required=false) MultipartFile profilePic,
			@RequestPart(value = "doctorUpdate", required=true) DoctorUserUpdateRequest request) {
		DoctorUserUpdateResponse response = new DoctorUserUpdateResponse();
		try {
			response = manager.updateUser(authToken, request, profilePic);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Doctor User Registration", e);
		}
		return response;
	}

	@RequestMapping(value = "/list/degree", method = RequestMethod.GET)
	public @ResponseBody ListAllDegreeResponse listAllDegrees() {
		ListAllDegreeResponse response = new ListAllDegreeResponse();
		try {
			response = manager.listAllDegree();
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "List All Degree", e);
		}
		return response;
	}

	@RequestMapping(value = "/list/mci", method = RequestMethod.GET)
	public @ResponseBody ListAllMciResponse listAllMci() {
		ListAllMciResponse response = new ListAllMciResponse();
		try {
			response = manager.listAllMci();
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "List All MCI", e);
		}
		return response;
	}

	@RequestMapping(value = "/list/specialization", method = RequestMethod.GET)
	public @ResponseBody ListAllSpecializationResponse listAllSpecialization() {
		ListAllSpecializationResponse response = new ListAllSpecializationResponse();
		try {
			response = manager.listAllSpecialization();
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "List All MCI", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/get/details", method = RequestMethod.GET)
	public @ResponseBody DoctorUserDetailResponse getDoctorUserDetail(
			@RequestHeader(value="authToken", required=true) String authToken) {
		DoctorUserDetailResponse response = new DoctorUserDetailResponse();
		try {
			response = manager.getDoctorUserDetail(authToken);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Get doctor user details", e);
		}
		return response;
	}
}
