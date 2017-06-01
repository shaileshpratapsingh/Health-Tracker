package com.psquickit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psquickit.manager.LoginManager;
import com.psquickit.pojo.UserLoginRequest;
import com.psquickit.pojo.UserLoginResponse;
import com.psquickit.util.ServiceUtils;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginManager loginManager;

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public @ResponseBody UserLoginResponse registerUser(@RequestBody UserLoginRequest request) {
		UserLoginResponse response = new UserLoginResponse();
		try {
			response = loginManager.login(request.getUid());
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "User Registration", e);
		}
		return response;
	}

}
