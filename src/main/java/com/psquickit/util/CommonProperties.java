package com.psquickit.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class CommonProperties {

	private static Properties configProp = null;

	static {
		configProp = CommonProperties.loadPropertyFile("config.properties");
	}

	public static final String CONFIG_DIR_PATH = configProp.getProperty("config.dir.path");
	public static final String FILESTORE_PATH = configProp.getProperty("filestore.path");

	public static Properties loadPropertyFile(String filename) {
		Properties properties = new Properties();
		try (InputStream inputStream = CommonProperties.class.getClassLoader().getResourceAsStream(filename)) {
			properties.load(inputStream);
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return trimPropertiesValue(properties);
	}

	public static Properties trimPropertiesValue(Properties properties) {
		Properties trimProperties = new Properties();
		if (null != properties) {
			for (@SuppressWarnings("rawtypes")
			Enumeration propKeys = properties.propertyNames(); propKeys.hasMoreElements();) {
				String tmpKey = (String) propKeys.nextElement();
				trimProperties.put(tmpKey, properties.getProperty(tmpKey).trim());
			}
		}
		return trimProperties;
	}
}
