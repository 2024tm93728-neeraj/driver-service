package com.microservice.driverservice.repository;

import com.microservice.driverservice.entity.Driver;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
	
	Optional<Driver> findByDriverId(@Param("driverId") Long driverId);

	List<Driver> findByIsActiveTrue();

}
