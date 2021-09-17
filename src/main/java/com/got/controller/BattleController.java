package com.got.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.got.configuration.ApplicationConfiguration;
import com.got.configuration.Constants;
import com.got.response.BattleInformationResponse;
import com.got.response.CountResponse;
import com.got.response.LocationResponse;
import com.got.service.BattleService;;

@RestController
@RequestMapping(value = "/gotBattle/api/")
public class BattleController {

	private static final Logger logger = LoggerFactory.getLogger(BattleController.class);
	
	@Autowired
	ApplicationConfiguration appConfig;
	
	@Autowired
	BattleService service;
	
	@GetMapping(value = "/count")
	public ResponseEntity<CountResponse> getCount(
			@RequestHeader(value = Constants.ACCEPT, required = true) String accept
			)
	{
		logger.info(Constants.ENTERF,"getCount");
		CountResponse response = appConfig.getCountResponse();
		try {
			if(!accept.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
				logger.info(Constants.INVALID_ACCEPT);
				response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				response.setMessage(Constants.INVALID_ACCEPT);
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value()).body(response);
			}
			
			//TODO : CALL THE SERVICE HERE
			response = service.getCount();
			logger.info(Constants.EXITF,"getCount");
			return ResponseEntity.ok().body(response);
		}
		catch (Exception e) {
			logger.error(Constants.INTERNALERR);
			e.printStackTrace();
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(Constants.INTERNALERR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
		}
	}
	
	@GetMapping(value = "/getBattleInformation")
	public ResponseEntity<BattleInformationResponse> getBattleInformation(
			@RequestHeader(value = Constants.ACCEPT, required = true) String accept,
			@RequestHeader(value = Constants.CONTENT_TYPE, required = true) String contentType,
			@RequestParam( value = Constants.BATTLENUMBER, required = true) Integer battleNumber
			){
		logger.info(Constants.ENTERF,"getBattleInformation");
		BattleInformationResponse response = appConfig.getBattleInformationResponse();
		try {
			if(!accept.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
				logger.info(Constants.INVALID_ACCEPT);
				response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				response.setMessage(Constants.INVALID_ACCEPT);
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value()).body(response);
			}
			if(!contentType.equalsIgnoreCase(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
				logger.info(Constants.INVALID_CONTENTTYPE);
				response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				response.setMessage(Constants.INVALID_CONTENTTYPE);
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value()).body(response);
			}
			
			response = service.getBattleInformation(battleNumber);
			logger.info(Constants.EXITF,"getBattleInformation");
			return ResponseEntity.ok().body(response);
		}
		catch(Exception e) {
			logger.error(Constants.INTERNALERR + e.getMessage());
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(Constants.INTERNALERR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
		}
	}
	
	@GetMapping(value = "/list")
	public ResponseEntity<LocationResponse> getLocationInformation(
			@RequestHeader(value = Constants.ACCEPT, required = true) String accept
			){
		logger.info(Constants.ENTERF,"getLocationInformation");
		LocationResponse response = appConfig.getLocationResponse();
		try {
			if(!accept.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
				logger.info(Constants.INVALID_ACCEPT);
				response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				response.setMessage(Constants.INVALID_ACCEPT);
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value()).body(response);
			}
			response = service.getLocationInformation();
			logger.info(Constants.EXITF,"getLocationInformation");
			return ResponseEntity.ok().body(response);
		}
		catch(Exception e) {
			logger.error(Constants.INTERNALERR + e.getMessage());
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(Constants.INTERNALERR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
		}
	}
}
