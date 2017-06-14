package com.psquickit.managerImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.psquickit.manager.KycManager;
import com.psquickit.pojo.user.GetKycDetailsResponse;
import com.psquickit.pojo.user.InitiateKycRequest;
import com.psquickit.util.ServiceUtils;

@Service
public class KycManagerImpl implements KycManager {
	
	Map<String, InitateKycRequestDetail> initateKycRequestDetailByRequestId = Maps.newConcurrentMap();
	
	@Override
	public String initiateKyc(InitiateKycRequest request, 
			DeferredResult<GetKycDetailsResponse> deferredResult) throws Exception {
		
		ArrayList<NameValuePair> postParameters = new ArrayList<>();
		postParameters.add(new BasicNameValuePair("purpose", request.getPurpose()));
	    postParameters.add(new BasicNameValuePair("aadhaarId", request.getAadhaarId()));
	    postParameters.add(new BasicNameValuePair("modality", request.getModality()));
	    postParameters.add(new BasicNameValuePair("requestId", request.getRequestId()));
	    postParameters.add(new BasicNameValuePair("saCode", request.getSaCode()));
	    postParameters.add(new BasicNameValuePair("successUrl", request.getSuccessUrl()));
	    postParameters.add(new BasicNameValuePair("failureUrl", request.getFailureUrl()));
	    if (!Strings.isNullOrEmpty(request.getKycCaptureData())) {
	    	postParameters.add(new BasicNameValuePair("kyc-capture-data", request.getKycCaptureData()));
	    }
	    postParameters.add(new BasicNameValuePair("hash", request.getHash()));
	    
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    try {
			HttpPost httpPost = new HttpPost("https://api.aadhaarbridge.com/kua/_init");
			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
			httpPost.addHeader("Content-Type" , "application/x-www-form-urlencoded");
			
			// Create a custom response handler
	        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	
	            @Override
	            public String handleResponse(
	                    final HttpResponse response) throws ClientProtocolException, IOException {
	                int status = response.getStatusLine().getStatusCode();
	                if (status >= 200 && status < 300) {
	                    HttpEntity entity = response.getEntity();
	                    return entity != null ? EntityUtils.toString(entity) : null;
	                } else {
	                    throw new ClientProtocolException("Unexpected response status: " + status);
	                }
	            }
	
	        };
			
			String response = httpClient.execute(httpPost, responseHandler);
			
			InitateKycRequestDetail ikrd = new InitateKycRequestDetail();
			ikrd.setAadhaarId(request.getAadhaarId());
			ikrd.setGetKycDetailResponse(deferredResult);
			ikrd.setSaCode(request.getSaCode());
			initateKycRequestDetailByRequestId.put(request.getRequestId(), ikrd);
		    return request.getRequestId();
	    } finally {
	    	httpClient.close();
	    }		
	}

	@Override
	public void initiateSuccess(String hash, String uuid, String requestId, String status) throws Exception {
		
		InitateKycRequestDetail ikrd = initateKycRequestDetailByRequestId.get(requestId);
		if (ikrd == null) {
			throw new Exception("No kyc initiate request found against request id: " + requestId);
		}
		
		ArrayList<NameValuePair> postParameters = new ArrayList<>();
		postParameters.add(new BasicNameValuePair("saCode", ikrd.getSaCode()));
	    postParameters.add(new BasicNameValuePair("uuid", uuid));
	    postParameters.add(new BasicNameValuePair("requestId", requestId));
	    postParameters.add(new BasicNameValuePair("aadhaarId", ikrd.getAadhaarId()));
	    postParameters.add(new BasicNameValuePair("hash", hash));
	    
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    try {
			HttpPost httpPost = new HttpPost("https://api.aadhaarbridge.com/kua/_kyc");
			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
			httpPost.addHeader("Content-Type" , "application/x-www-form-urlencoded");
			
			// Create a custom response handler
	        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	
	            @Override
	            public String handleResponse(
	                    final HttpResponse response) throws ClientProtocolException, IOException {
	                int status = response.getStatusLine().getStatusCode();
	                if (status >= 200 && status < 300) {
	                    HttpEntity entity = response.getEntity();
	                    return entity != null ? EntityUtils.toString(entity) : null;
	                } else {
	                    throw new ClientProtocolException("Unexpected response status: " + status);
	                }
	            }
	
	        };
			
			String httpResponse = httpClient.execute(httpPost, responseHandler);
			GetKycDetailsResponse response = new GetKycDetailsResponse();
			response.setAuthToken(httpResponse);
			ikrd.getGetKycDetailResponse().setResult(ServiceUtils.setResponse(response, true, "initiate success kyc"));
	    } catch (Exception e) {
	    	ikrd.getGetKycDetailResponse().setErrorResult(ServiceUtils.setResponse(new GetKycDetailsResponse(), false, "initiate success kyc", e));
	    } finally {
	    	initateKycRequestDetailByRequestId.remove(requestId);
	    	httpClient.close();
	    }		
	}

	@Override
	public void initiateFailure(String hash, String uuid, String requestId, String status, String err)
			throws Exception {
		InitateKycRequestDetail ikrd = initateKycRequestDetailByRequestId.get(requestId);
		if (ikrd == null) {
			throw new Exception("No kyc initiate request found against request id: " + requestId);
		}
		//parse error codes
		String errorMessage = "";
		ikrd.getGetKycDetailResponse().setErrorResult(ServiceUtils.setResponse(new GetKycDetailsResponse(), false, "initiate success kyc", new Exception(errorMessage)));
		initateKycRequestDetailByRequestId.remove(requestId);
	}

	@Override
	public void clearMap(String requestId) {
		initateKycRequestDetailByRequestId.remove(requestId);		
	}
}

class InitateKycRequestDetail {
	private DeferredResult<GetKycDetailsResponse> getKycDetailResponse;
	private String saCode;
	private String aadhaarId;
	public DeferredResult<GetKycDetailsResponse> getGetKycDetailResponse() {
		return getKycDetailResponse;
	}
	public void setGetKycDetailResponse(DeferredResult<GetKycDetailsResponse> getKycDetailResponse) {
		this.getKycDetailResponse = getKycDetailResponse;
	}
	public String getSaCode() {
		return saCode;
	}
	public void setSaCode(String saCode) {
		this.saCode = saCode;
	}
	public String getAadhaarId() {
		return aadhaarId;
	}
	public void setAadhaarId(String aadhaarId) {
		this.aadhaarId = aadhaarId;
	}
}