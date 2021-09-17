package com.got.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.got.configuration.ApplicationConfiguration;
import com.got.configuration.Constants;
import com.got.response.CommonResponse;
import com.got.service.ReadCsvService;

@RestController
@RequestMapping(value = "/gotBattle/api/")
public class ReadCsvController {

	private static final Logger logger = LoggerFactory.getLogger(ReadCsvController.class);
	
	@Autowired
	ReadCsvService service;
	
	@Autowired
	ApplicationConfiguration appConfiguration;
	
	@PostMapping(value = "uploadCsv")
	public ResponseEntity<CommonResponse> uploadCsv(
			@RequestParam(value = "file", required = true) MultipartFile file
			) {
		logger.info(Constants.ENTERF,"uploadCSv");
		CommonResponse response = appConfiguration.getCommonResponse();
		try {
			if(!file.getContentType().equals(Constants.FILETYPE)) {
				logger.error(Constants.FILENOTINFORMAT);
				logger.error(Constants.FILETYPE);
				response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				response.setMessage(Constants.FILENOTINFORMAT);
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
			}
			response = service.readCsv(file);
			logger.info(Constants.EXITF,"uploadCsv");
			return ResponseEntity.ok().body(response);
		}
		catch(Exception e) {
			logger.error(Constants.INTERNALERR + e.getMessage());
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(Constants.INTERNALERR + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
		}
	}
}
