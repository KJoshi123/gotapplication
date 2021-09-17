package com.got.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.got.configuration.ApplicationConfiguration;
import com.got.configuration.Constants;
import com.got.dto.BattleDetailsDto;
import com.got.projection.BattleDetailsProjection;
import com.got.projection.LocationProjection;
import com.got.repository.GotRepository;
import com.got.response.BattleInformationResponse;
import com.got.response.CountResponse;
import com.got.response.LocationResponse;

@Service
public class BattleService {

	private static final Logger logger = LoggerFactory.getLogger(BattleService.class);
	
	@Autowired
	ApplicationConfiguration appConfig;
	
	@Autowired
	GotRepository repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public CountResponse getCount() throws Exception{
		logger.info(Constants.ENTERF,"getCount");
		CountResponse response = appConfig.getCountResponse();
		Long count = repository.count();
		logger.info(Constants.SUCCESS);
		response.setCount(count);
		response.setMessage(Constants.SUCCESS);
		response.setStatusCode(HttpStatus.OK.value());
		logger.info(Constants.EXITF,"getCount");
		return response;
	}
	
	public BattleInformationResponse getBattleInformation(Integer battleNumber) throws Exception {
		logger.info(Constants.ENTERF,"getBattleInformation");
		BattleInformationResponse response = appConfig.getBattleInformationResponse();
		
		Optional<BattleDetailsProjection> battleInfo = repository.getBattleDetails(battleNumber);
		//TODO: move this to applicationConfiguration for bean creation
		BattleDetailsDto battleDetails = new BattleDetailsDto();
		
		if(!battleInfo.isPresent()) {
			logger.error(Constants.NOT_FOUND);
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage(Constants.NOT_FOUND);
			return response;
		}
		battleDetails = modelMapper.map(battleInfo.get(), BattleDetailsDto.class);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage(Constants.SUCCESS);
		response.setBattleDetails(battleDetails);
		logger.info(Constants.EXITF,"getBattleInformation");
		return response;
	}
	
	public LocationResponse getLocationInformation() throws Exception{
		logger.info(Constants.ENTERF,"getLocationInformation");
		LocationResponse response = appConfig.getLocationResponse();
		List<LocationProjection> locationList = repository.getLocationInformation();
		if(locationList.isEmpty()) {
			logger.error(Constants.NOT_FOUND);
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage(Constants.NOT_FOUND);
			return response;
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage(Constants.SUCCESS);
		response.setLocations(locationList);
		logger.info(Constants.EXITF,"getLocationInformation");
		return response;
	}
}
