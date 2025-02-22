package com.otpapp.otp.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otpapp.otp.model.Qb;

public interface QbRepo extends  JpaRepository<Qb, Integer>{

	@Query(value = "SELECT * FROM qbs WHERE year = :year ORDER BY RAND() LIMIT 20", nativeQuery = true)
	List<Qb> findQbByYear(@Param("year") String year);

}
