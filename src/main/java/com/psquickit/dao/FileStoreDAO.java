package com.psquickit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psquickit.dto.FileStoreDTO;

@Repository
public interface FileStoreDAO extends JpaRepository<FileStoreDTO, Long> {

}
