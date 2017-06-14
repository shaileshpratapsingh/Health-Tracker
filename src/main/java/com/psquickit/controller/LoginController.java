package com.psquickit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psquickit.manager.LoginManager;
import com.psquickit.pojo.user.UserLoginRequest;
import com.psquickit.pojo.user.UserLoginResponse;
import com.psquickit.util.ServiceUtils;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginManager loginManager;

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public @ResponseBody UserLoginResponse registerUser(@RequestHeader(value="secretToken", required=true) String secretToken,
			@RequestBody UserLoginRequest request) {
		UserLoginResponse response = new UserLoginResponse();
		try {
			response = loginManager.login(secretToken, request);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "User Login", e);
		}
		return response;
	}

}
