package com.psquickit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.psquickit.dto.DoctorUserDTO;


@Repository
public interface DoctorUserDAO extends JpaRepository<DoctorUserDTO, Long> {
	
	
   

}
