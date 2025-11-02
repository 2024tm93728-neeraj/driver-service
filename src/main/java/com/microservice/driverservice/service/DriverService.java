package com.microservice.driverservice.service;

import com.microservice.driverservice.client.TripClient;
import com.microservice.driverservice.entity.Driver;
import com.microservice.driverservice.exceptions.ResourceNotFoundException;
import com.microservice.driverservice.repository.DriverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface DriverService {
	 public Driver save(Driver driver);
	 
	 public List<Driver> findAll();
	 
	 public Driver findById(Long driverId);
	 
	 public Driver update(Long driverId, Driver newData);
	 
	 public Driver toggleStatus(Long driverId, boolean isActive);
	 
	 public Boolean acceptTrip(Long driverId, Long tripId);
	 
	 public Boolean cancelTrip(Long driverId, Long tripId);
	 
	 public Driver findActiveDriver();
	 
	 public void deleteDriver(Long driverId);
	 
}
