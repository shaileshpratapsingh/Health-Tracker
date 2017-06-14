package com.psquickit.manager;

import org.springframework.web.context.request.async.DeferredResult;

import com.psquickit.pojo.user.GetKycDetailsResponse;
import com.psquickit.pojo.user.InitiateKycRequest;

public interface KycManager {

	String initiateKyc(InitiateKycRequest request, DeferredResult<GetKycDetailsResponse> deferredResult) throws Exception;

	void initiateSuccess(String hash, String uuid, String requestId, String status) throws Exception;

	void initiateFailure(String hash, String uuid, String requestId, String status, String err) throws Exception;

	void clearMap(String requestId);

}
