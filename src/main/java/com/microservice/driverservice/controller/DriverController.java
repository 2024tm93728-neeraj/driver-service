package com.microservice.driverservice.controller;

import com.microservice.driverservice.entity.Driver;
import com.microservice.driverservice.exceptions.ApiResponse;
import com.microservice.driverservice.service.DriverService;

import jakarta.validation.Valid;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/drivers")
public class DriverController {
	
	@Autowired
    private DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Driver>> create(@Valid @RequestBody Driver driver) {
        Driver savedDriver = service.save(driver);
        ApiResponse<Driver> response = new ApiResponse<>(true, "Driver created successfully", savedDriver);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Driver>>> getAll() {
        List<Driver> drivers = service.findAll();
        if (drivers.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "No drivers found", drivers));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Drivers fetched successfully", drivers));
    }


    @GetMapping("/{driverId}")
    public ResponseEntity<ApiResponse<Driver>> getById(@PathVariable("driverId") Long driverId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Drivers fetched successfully", service.findById(driverId)));
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<ApiResponse<Driver>> update(@PathVariable("driverId") Long driverId, @RequestBody Driver driver) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Driver updated successfully", service.update(driverId, driver)));  
    }
    
    @PatchMapping("/{driverId}/status")
    public ResponseEntity<ApiResponse<Driver>> updateStatus(
            @PathVariable("driverId") Long driverId,
            @RequestBody Map<String, Object> body) throws BadRequestException{
        boolean isActive = (Boolean) body.get("isActive");
        return ResponseEntity.ok(new ApiResponse<>(true, "status updated successfully", service.toggleStatus(driverId, isActive)));
    }
    
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<Driver>> getActiveDriver() {
    	return ResponseEntity.ok(new ApiResponse<>(true, "Active driver fetched successfully", service.findActiveDriver()));
    }

    //Driver accepts trip
    @PostMapping("/{driverId}/trips/{tripId}/accept")
    public ResponseEntity<ApiResponse<Boolean>> acceptTrip(
            @PathVariable("driverId") Long driverId,
            @PathVariable("tripId") Long tripId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Driver accepted the trip", service.acceptTrip(driverId, tripId)));
    }

    //Driver cancels trip
    @PostMapping("/{driverId}/trips/{tripId}/cancel")
    public ResponseEntity<ApiResponse<Boolean>> cancelTrip(
            @PathVariable("driverId") Long driverId,
            @PathVariable("tripId") Long tripId) {
    	
        return ResponseEntity.ok(new ApiResponse<>(true, "Driver canceled the trip", service.cancelTrip(driverId, tripId)));
    }
    
    @DeleteMapping("/{driverId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteDriver(
            @PathVariable("driverId") Long driverId) {

        service.deleteDriver(driverId);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Driver deleted successfully", true)
        );
    }

}
