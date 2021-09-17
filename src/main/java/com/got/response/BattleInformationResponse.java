package com.got.response;

import com.got.dto.BattleDetailsDto;

public class BattleInformationResponse {

	public Integer statusCode;
	public String message;
	public BattleDetailsDto battleDetails;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BattleDetailsDto getBattleDetails() {
		return battleDetails;
	}

	public void setBattleDetails(BattleDetailsDto battleDetails) {
		this.battleDetails = battleDetails;
	}

}
