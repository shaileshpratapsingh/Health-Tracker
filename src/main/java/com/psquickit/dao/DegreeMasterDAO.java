package com.psquickit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psquickit.dto.DegreeMasterDTO;

@Repository
public interface DegreeMasterDAO extends JpaRepository<DegreeMasterDTO, Long> {

}
