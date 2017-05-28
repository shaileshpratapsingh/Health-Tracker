package com.psquickit.util;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ClientUtil {
	private static Properties configProp = null;
	static{
		configProp = ClientUtil.loadConfigPropertyFile("config.properties");
	}
	
	private static final String REST_API_HOST_ON = configProp.getProperty("rest.services.hosted.on");
	private static final String REST_API_HOST_ON_CONTEXT_PATH = configProp.getProperty("rest.services.hosted.on.context.path");
	private static final String DEFAULT_REST_API_HOST_ON = getAppHost();
	public static final String REST_API_CONTEXT_PATH = 
			(((REST_API_HOST_ON!=null) && !REST_API_HOST_ON.equalsIgnoreCase(""))?REST_API_HOST_ON:DEFAULT_REST_API_HOST_ON) +
			REST_API_HOST_ON_CONTEXT_PATH;
	
	public static Properties loadConfigPropertyFile(String filename) {	
		Path configFile = Paths.get(System.getProperty("config.dir"), filename);
		Properties properties = new Properties();
		try (InputStream in = Files.newInputStream(configFile)) {
			properties.load(in);
		}catch (Exception e) {
			System.out.println("Exception while loading properties file :"+e);
		}
		return trimPropertiesValue(properties);
	}
	
	public static Properties trimPropertiesValue(Properties properties){
		Properties trimProperties = new Properties();
		if(null != properties){
			for (@SuppressWarnings("rawtypes") Enumeration propKeys = properties.propertyNames();propKeys.hasMoreElements();) {
				  String tmpKey = (String)propKeys.nextElement();
			      trimProperties.put(tmpKey, properties.getProperty(tmpKey).trim());
			}
		}
		return trimProperties;
	}
	
	//Get Current Request
	public static HttpServletRequest getCurrentRequest(){
		HttpServletRequest curRequest =	((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		return curRequest;
	}
	
	//Get App Context Path
	public static String getAppHost(){
		return getCurrentRequest().getScheme()+"://"+getCurrentRequest().getHeader("host");
	}

}
