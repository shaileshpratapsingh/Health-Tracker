package com.psquickit.manager;

import java.io.InputStream;

import com.google.common.io.ByteSource;
import com.psquickit.dto.FileStoreDTO;

public interface FileStoreManager {

	public FileStoreDTO uploadFile(InputStream is, String contentType, String fileName) throws Exception ;
	
	public void updateFile(FileStoreDTO dto, InputStream is, String contentType, String fileName) throws Exception;
	
	public ByteSource retrieveFile(FileStoreDTO dto) throws Exception;
	
}
