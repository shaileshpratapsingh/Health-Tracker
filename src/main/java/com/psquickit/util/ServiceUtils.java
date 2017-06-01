package com.psquickit.util;

import org.apache.log4j.Logger;

import com.psquickit.pojo.GenericServiceResponse;

public class ServiceUtils {

	private static Logger logger = Logger.getLogger(ServiceUtils.class);

	public static <T extends GenericServiceResponse> T setResponse(T response, boolean status, String operation) {
		response.setStatus(status);
		response.setOperation(operation);
		return response;
	}

	public static <T extends GenericServiceResponse> T setResponse(T response, boolean status, String operation,
			Exception ex) {
		setResponse(response, status, operation);
		handleException(response, ex);
		return response;
	}

	public static void handleException(GenericServiceResponse response, Exception ex) {
		ex.printStackTrace();
		logger.error(ex);
		response.setErrorMessage(ex.getMessage());
	}
}
