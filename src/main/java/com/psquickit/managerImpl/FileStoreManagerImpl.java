package com.psquickit.managerImpl;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import com.psquickit.dao.FileStoreDAO;
import com.psquickit.dto.FileStoreDTO;
import com.psquickit.manager.FileStoreManager;
import com.psquickit.util.CommonProperties;

@Service
public class FileStoreManagerImpl implements FileStoreManager {
	
	@Autowired
	FileStoreDAO fileStoreDAO;

	@Override
	public FileStoreDTO uploadFile(InputStream is, String contentType, String fileName) throws Exception {
		String targetFileName = CommonProperties.FILESTORE_PATH + File.separator + (fileStoreDAO.count() + 1)
				+ File.separator + UUID.randomUUID();
		File targetFile = new File(targetFileName);
		FileUtils.copyInputStreamToFile(is, targetFile);
		FileStoreDTO dto = new FileStoreDTO();
		dto.setDocumentType(contentType);
		dto.setLocation(targetFileName);
		dto.setFileName(fileName);
		return fileStoreDAO.save(dto);
	}
	
	@Override
	public void updateFile(FileStoreDTO dto, InputStream is, String contentType, String fileName) throws Exception {
		String targetFileName = dto.getLocation();
		File targetFile = new File(targetFileName);
		FileUtils.copyInputStreamToFile(is, targetFile);
		dto.setFileName(fileName);
		fileStoreDAO.save(dto);
	}

	@Override
	public ByteSource retrieveFile(FileStoreDTO dto) throws Exception {
		String targetFileName = dto.getLocation();
		File targetFile = new File(targetFileName);
		return Files.asByteSource(targetFile);
	}

}
