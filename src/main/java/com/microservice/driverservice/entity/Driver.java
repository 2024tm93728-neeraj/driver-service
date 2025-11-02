package com.microservice.driverservice.entity;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "drivers")
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long driverId;

	@NotBlank
	private String name;

	private String phone;
	@Column(name = "vehicle_type")
	private String vehicleType;
	@Column(name = "vehicle_plate")
	private String vehiclePlate;
	@Column(name = "is_active")
	private boolean isActive;

	public Driver() {
	}

	public Driver(String name, String phone, String vehicleType, String vehiclePlate, boolean isActive) {
		this.name = name;
		this.phone = phone;
		this.vehicleType = vehicleType;
		this.vehiclePlate = vehiclePlate;
		this.isActive = isActive;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Long getdriverId() {
		return driverId;
	}

	public void setdriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getvehicleType() {
		return vehicleType;
	}

	public void setvehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getvehiclePlate() {
		return vehiclePlate;
	}

	public void setvehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Driver driver = (Driver) o;
	    return Objects.equals(driverId, driver.driverId);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(driverId);
	}

}
