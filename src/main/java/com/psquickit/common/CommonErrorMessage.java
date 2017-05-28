package com.psquickit.common;

import java.util.Properties;

import com.psquickit.util.ClientUtil;

public interface CommonErrorMessage {
	
	Properties prop = ClientUtil.loadSrcPropertyFile("errorcode.properties");	
	String USER_NOT_FOUND_CODE = prop.getProperty("user.not.found.code");
	String USER_NOT_FOUND_DESC = prop.getProperty("user.not.found.desc");
	String USER_ALREADY_EXIST_CODE = prop.getProperty("user.already.exist.code");
	String USER_ALREADY_EXIST_DESC = prop.getProperty("user.already.exist.desc");

}
