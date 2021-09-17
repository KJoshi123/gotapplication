package com.got.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;

import com.got.response.BattleInformationResponse;
import com.got.response.CommonResponse;
import com.got.response.CountResponse;
import com.got.response.LocationResponse;

@Configuration
@EnableAsync
public class ApplicationConfiguration {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CountResponse getCountResponse() {
		return new CountResponse();
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public BattleInformationResponse getBattleInformationResponse() {
		return new BattleInformationResponse();
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public LocationResponse getLocationResponse() {
		return new LocationResponse();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CommonResponse getCommonResponse() {
		return new CommonResponse();
	}
}
