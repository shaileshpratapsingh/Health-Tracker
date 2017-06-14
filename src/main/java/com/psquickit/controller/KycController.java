package com.psquickit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.psquickit.manager.KycManager;
import com.psquickit.pojo.user.GetKycDetailsResponse;
import com.psquickit.pojo.user.InitiateKycRequest;
import com.psquickit.util.ServiceUtils;

@RestController
@RequestMapping("/kyc")
public class KycController {

	@Autowired
	KycManager manager;
	
	@RequestMapping(value = "/get/details", method = RequestMethod.POST)
	public @ResponseBody DeferredResult<GetKycDetailsResponse> getKycDetails(
			@RequestBody InitiateKycRequest request) {
		final DeferredResult<GetKycDetailsResponse> deferredResult = new DeferredResult<>(20000L);
		try {
			manager.initiateKyc(request, deferredResult);
		} catch (Exception e) {
			manager.clearMap(request.getRequestId());
			deferredResult.setResult(ServiceUtils.setResponse(new GetKycDetailsResponse(), 
					false, "Get KYC Details", e));
		}
		return deferredResult;
	}
	
	@RequestMapping(value = "/initiation/success", method = RequestMethod.GET)
	public void initiateSuccess(@RequestParam("hash") String hash,
			@RequestParam("uuid") String uuid,
			@RequestParam("requestId") String requestId,
			@RequestParam("status") String status) {
		try {
			manager.initiateSuccess(hash, uuid, requestId, status);
		} catch (Exception e) {
			manager.clearMap(requestId);
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/initiation/failure", method = RequestMethod.GET)
	public void initiateFailure(@RequestParam("hash") String hash,
			@RequestParam("uuid") String uuid,
			@RequestParam("requestId") String requestId,
			@RequestParam("status") String status,
			@RequestParam("err") String err) {
		try {
			manager.initiateFailure(hash, uuid, requestId, status, err);
		} catch (Exception e) {
			manager.clearMap(requestId);
			e.printStackTrace();
		}
	}
}
