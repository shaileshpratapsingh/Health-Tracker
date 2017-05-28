package com.psquickit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psquickit.dto.UserDTO;

@Repository
public interface LoginDAO extends JpaRepository<UserDTO, Long> {
	
	

}


