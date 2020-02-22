package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Sample;
import com.example.demo.service.SampleQueueService;
import com.google.gson.JsonObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/sample/v1")
@Tag(name = "Sample Controller", description = "This API is for RabbitMq test")
public class SampleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	private SampleQueueService sampleQueueService;
	
	@Operation(summary = "Put a message into RabbitMQ", description = "After put a message, return 'status: success'.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success")
	})
	@PostMapping("/putAmessage")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> putObjectIntoRabbitMq(@Parameter(description = "Message to put into RabbitMQ.") @Valid @RequestBody Sample sample) {
		
		LOGGER.info("Sample API. Sample message recieved is {}", sample.toString());
		
		sampleQueueService.sendAMessageToRabbitMq(sample);
		
		JsonObject returnObj = new JsonObject();
		returnObj.addProperty("status", "success");
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObj.toString());
	}
	
}
