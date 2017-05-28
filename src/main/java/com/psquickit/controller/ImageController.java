package com.psquickit.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.psquickit.util.ClientUtil;

@RestController
public class ImageController {
	
	@Autowired
	ServletContext context; 
	
	@RequestMapping(value = "/image/{userId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable("userId") String userId) {
	    HttpHeaders headers = new HttpHeaders();
	    ResponseEntity<byte[]> responseEntity = null;
	    try{
	    	Path configFile = Paths.get(ClientUtil.REST_IMAGE_PATH, userId+".jpg");
		    InputStream in = Files.newInputStream(configFile);
		    byte[] media = IOUtils.toByteArray(in);
		    
		    headers.setContentType(MediaType.IMAGE_PNG);
		    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		     
		    responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    }catch(Exception e){
	    	responseEntity = new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return responseEntity;
	}
}
