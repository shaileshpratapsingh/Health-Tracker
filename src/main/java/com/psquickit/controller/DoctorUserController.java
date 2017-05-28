package com.psquickit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psquickit.manager.DoctorUserManager;
import com.psquickit.pojo.Degree;
import com.psquickit.pojo.DoctorUserRegisterRequest;
import com.psquickit.pojo.DoctorUserResponse;
import com.psquickit.pojo.ListAllDegreeResponse;
import com.psquickit.pojo.ListAllMciResponse;
import com.psquickit.pojo.ListAllSpecializationResponse;
import com.psquickit.util.ServiceUtils;

@RestController
@RequestMapping("/doctorUser")
public class DoctorUserController {

	@Autowired
	DoctorUserManager doctorUserManger;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody DoctorUserResponse registerUser(@RequestBody DoctorUserRegisterRequest request) {
		DoctorUserResponse response = new DoctorUserResponse();
		try {
			response = doctorUserManger.registerUser(request);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Doctor User Registration", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/degreeList", method = RequestMethod.GET)
	@ResponseBody public ListAllDegreeResponse listAllDegrees(){
		ListAllDegreeResponse response = new ListAllDegreeResponse();
		try {
			response = doctorUserManger.listAllDegree();
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "List All Degree", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/mciList", method = RequestMethod.GET)
	@ResponseBody public ListAllMciResponse listAllMci(){
		ListAllMciResponse response = new ListAllMciResponse();
		try {
			response = doctorUserManger.listAllMci();
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "List All MCI", e);
		}
		return response;
	}
	@RequestMapping(value = "/specializationList", method = RequestMethod.GET)
	@ResponseBody public ListAllSpecializationResponse listAllSpecialization(){
		ListAllSpecializationResponse response = new ListAllSpecializationResponse();
		try {
			response = doctorUserManger.listAllSpecialization();
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "List All MCI", e);
		}
		return response;
	}
}
