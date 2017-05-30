package com.psquickit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psquickit.dto.MciMasterDTO;

@Repository
public interface MCIMasterDAO extends JpaRepository< MciMasterDTO, Long>{

}
