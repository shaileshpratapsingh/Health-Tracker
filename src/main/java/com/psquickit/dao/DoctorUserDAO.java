package com.psquickit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psquickit.dto.DoctorUserDTO;


@Repository
public interface DoctorUserDAO extends JpaRepository<DoctorUserDTO, Long> {

	@Query("Select doc from DoctorUserDTO doc where doc.userDTO.uid = :uid")
	public DoctorUserDTO getDetailOfDoctorUser(@Param("uid") String uid);

}
