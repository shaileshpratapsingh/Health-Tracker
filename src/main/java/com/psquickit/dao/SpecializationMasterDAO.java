package com.psquickit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psquickit.dto.SpecializationMasterDTO;

@Repository
public interface SpecializationMasterDAO extends JpaRepository<SpecializationMasterDTO, Long> {

}
