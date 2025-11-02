package com.microservice.driverservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.driverservice.client.config.FeignConfig;

@FeignClient(
	    name = "trip-service",
	    url = "http://ec2-3-6-160-223.ap-south-1.compute.amazonaws.com:8080",
	    configuration = FeignConfig.class
	)
public interface TripClient {
	@RequestMapping(
		    method = RequestMethod.PATCH,
		    value = "/v1/trips/{tripId}/accept"
		)
	Boolean acceptTrip(@PathVariable("tripId") Long tripId);

	@RequestMapping(
		    method = RequestMethod.PATCH,
		    value = "/v1/trips/{tripId}/cancel"
		)
	Boolean cancelTrip(@PathVariable("tripId") Long tripId);
}
