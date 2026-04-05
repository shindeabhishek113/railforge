package com.railforge.trainservice.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railforge.trainservice.dto.request.TrainRequestDTO;
import com.railforge.trainservice.service.TrainService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Train Controller", description = "Manage trains, coaches and berths")
@RestController
@RequestMapping("/trains")
public class TrainController {
	
	@Autowired
	private TrainService trainService;
	
	@Operation(summary = "Add a new train to the system")
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public Map<String, Object> addTrain(@RequestBody TrainRequestDTO trainRequestDTO) {
		return Collections.singletonMap("trainId", trainService.addTrain(trainRequestDTO));
	}
	
	@Operation(summary = "Get train by ID")
	@GetMapping(value = "/list", produces = "application/json")
	public Map<String, Object> getTrainList() {
		return Collections.singletonMap("trainList", trainService.getTrainList());
	}
	
	@Operation(summary = "Get list of all trains")
	@GetMapping(value = "/object/{id}", produces = "application/json")
	public Map<String, Object> getTrainObject(@PathVariable("id") long id ) throws Exception {
		return Collections.singletonMap("trainObject", trainService.getTrainObject(id));
	}
}
