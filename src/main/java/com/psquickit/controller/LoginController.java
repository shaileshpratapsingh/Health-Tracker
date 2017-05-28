package com.psquickit.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psquickit.pojo.IndividualUserRegisterRequest;
import com.psquickit.pojo.UserLoginResponse;
import com.psquickit.util.ServiceUtils;

@RestController
@RequestMapping("/userLogin")
public class LoginController {
	
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public @ResponseBody UserLoginResponse registerUser(
			@RequestBody IndividualUserRegisterRequest request) {
		UserLoginResponse response = new UserLoginResponse();
		try {
			//response = manager.registerUser(request);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "User Registration", e);
		}
		return response;
	}

	

}
