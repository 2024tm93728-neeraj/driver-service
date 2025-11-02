package com.microservice.driverservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.microservice.driverservice.client.TripClient;
import com.microservice.driverservice.entity.Driver;
import com.microservice.driverservice.exceptions.ResourceNotFoundException;
import com.microservice.driverservice.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService{

	@Autowired
    private DriverRepository repo;
    @Autowired
    private TripClient tripClient;

    @Override
    public Driver save(Driver driver) {
    	List<Driver> existingDrivers=findAll();
    	if(existingDrivers.contains(driver)) {
    		throw new DataIntegrityViolationException("Driver already exists");
    	}
        return repo.save(driver);
    }

    @Override
    public List<Driver> findAll() {
        return repo.findAll();
    }
    
    @Override
    public Driver findById(Long driverId) {
        return repo.findByDriverId(driverId)
                   .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + driverId));
    }
    
    @Override
    public Driver update(Long driverId, Driver newData) {
        Driver existing = findById(driverId);
        if(existing == null) {
        	throw new ResourceNotFoundException("Driver not found");
        }
        existing.setName(newData.getName());
        existing.setPhone(newData.getPhone());
        existing.setvehicleType(newData.getvehicleType());
        existing.setvehiclePlate(newData.getvehiclePlate());
        return repo.save(existing);
    }
    
    @Override
    public Driver toggleStatus(Long driverId, boolean isActive) {
        Driver d = findById(driverId);
        if(d == null) {
        	throw new ResourceNotFoundException("Driver not found");
        }
        d.setIsActive(isActive);
        return repo.save(d);
    }
    
    @Override
    public Driver findActiveDriver() {
        List<Driver> activeDrivers = repo.findByIsActiveTrue();

        if (activeDrivers.isEmpty()) {
            throw new ResourceNotFoundException("No active driver available");
        }

        // return first active driver
        return activeDrivers.get(0);
    }
    
    @Override
    public Boolean acceptTrip(Long driverId, Long tripId) {

        Driver driver = repo.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found: " + driverId));

		if (!driver.getIsActive()) {
            throw new RuntimeException("Driver is not available");
        }

        // 1. Trip status change in Trip-Service
        Boolean tripUpdated = tripClient.acceptTrip(tripId);

        if (tripUpdated) {
            driver.setIsActive(false); // driver busy
            repo.save(driver);
        }

        return tripUpdated;
    }

    @Override
    public Boolean cancelTrip(Long driverId, Long tripId) {

        Driver driver = repo.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found: " + driverId));

        // 1. Trip update trip-service
        Boolean cancelled = tripClient.cancelTrip(tripId);

        if (cancelled) {
            driver.setIsActive(true); // driver free again
            repo.save(driver);
        }

        return cancelled;
    }
}
