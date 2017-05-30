package com.psquickit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psquickit.manager.IndividualUserManager;
import com.psquickit.pojo.IndividualUserRegisterRequest;
import com.psquickit.pojo.IndividualUserRegisterResponse;
import com.psquickit.pojo.IndividualUserUpdateRequest;
import com.psquickit.pojo.IndividualUserUpdateResponse;
import com.psquickit.util.ServiceUtils;

@RestController
@RequestMapping("/user")
public class IndividualUserController {

	@Autowired
	IndividualUserManager manager;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody IndividualUserRegisterResponse registerUser(
			@RequestBody IndividualUserRegisterRequest request) {
		IndividualUserRegisterResponse response = new IndividualUserRegisterResponse();
		try {
			response = manager.registerUser(request);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "User Registration", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody IndividualUserUpdateResponse updateUser(
			@RequestBody IndividualUserUpdateRequest request) {
		IndividualUserUpdateResponse response = new IndividualUserUpdateResponse();
		try {
			response = manager.updateUser(request);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "User Registration", e);
		}
		return response;
	}
}
