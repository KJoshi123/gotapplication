package com.got.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.got.configuration.ApplicationConfiguration;
import com.got.configuration.Constants;
import com.got.entity.GotDataEntity;
import com.got.projection.ReadCsvProjection;
import com.got.repository.GotRepository;
import com.got.response.CommonResponse;

@Service
public class ReadCsvService {

	private static final Logger logger = LoggerFactory.getLogger(ReadCsvService.class);
	
	@Autowired
	GotRepository repository;
	
	@Autowired
	ApplicationConfiguration appConfig;
	
	public CommonResponse readCsv(MultipartFile file) throws Exception{
		logger.info(Constants.ENTERF,"readCsv");
		CommonResponse response = appConfig.getCommonResponse();
		String[] headerArray = new String[]{ "name", "year", "battle_number", "attacker_king", "defender_king",
				"attacker_outcome", "battle_type", "major_death", "major_capture", "attacker_size",
				"defender_size", "attacker_commander", "defender_commander", "summer", "location",
				"region", "note","attacker_1","attacker_2","attacker_3","attacker_4","defender_1","defender_2","defender_3","defender_4" };
		List<String> headerList = Arrays.asList(headerArray);
		
		
		InputStream is = file.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		CSVParser parser = new CSVParser(br, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
		List<CSVRecord> csvrecords = parser.getRecords();
		if(csvrecords.isEmpty()) {
			response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			response.setMessage(Constants.FILENOTINFORMAT + " empty");
			logger.error(Constants.FILENOTINFORMAT + " empty");
			parser.close();
			return response;
		}
		
		List<String> headersFromFile = parser.getHeaderNames();
		if(headersFromFile.size()!=headerList.size() || !headersFromFile.containsAll(headerList)) {
			response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			response.setMessage(Constants.FILENOTINFORMAT);
			logger.error(Constants.FILENOTINFORMAT);
			parser.close();
			return response;
		}
		parser.close();
		for(CSVRecord csvRecord : csvrecords) {
			String attackerKing = csvRecord.get("attacker_king").equals("")?null:csvRecord.get("attacker_king");
			String defenderKing = csvRecord.get("defender_king").equals("")?null:csvRecord.get("defender_king");
			String attacker1 = csvRecord.get("attacker_1").trim().equals("")?null:csvRecord.get("attacker_1");
			String attacker2 = csvRecord.get("attacker_2").trim().equals("")?null:csvRecord.get("attacker_2");
			String attacker3 = csvRecord.get("attacker_3").trim().equals("")?null:csvRecord.get("attacker_3");
			String attacker4 = csvRecord.get("attacker_4").trim().equals("")?null:csvRecord.get("attacker_4");
			String defender1 = csvRecord.get("defender_1").trim().equals("")?null:csvRecord.get("defender_1");
			String defender2 = csvRecord.get("defender_2").trim().equals("")?null:csvRecord.get("defender_2");
			String defender3 = csvRecord.get("defender_3").trim().equals("")?null:csvRecord.get("defender_3");
			String defender4 = csvRecord.get("defender_4").trim().equals("")?null:csvRecord.get("defender_4");
			String battleType = csvRecord.get("battle_type").trim().equals("")?null:csvRecord.get("battle_type");
			String location = csvRecord.get("location").trim().equals("")?null:csvRecord.get("location");
			String region = csvRecord.get("region").trim().equals("")?null:csvRecord.get("region");
			Optional<ReadCsvProjection> intergerValues = repository.getOrInsertVariables(attackerKing, defenderKing, attacker1, attacker2, attacker3, attacker4, defender1, defender2, defender3, defender4, battleType, location, region);
			if(!intergerValues.isPresent()) {
				logger.error(Constants.NOT_FOUND + " " + csvRecord.get("battle_number"));
			}
			ReadCsvProjection integerValues = intergerValues.get();
			Optional<GotDataEntity> fromDb = repository.findById(Integer.parseInt(csvRecord.get("battle_number")));
			if(fromDb.isPresent()) {
				GotDataEntity entityObject = fromDb.get();
				entityObject.setBattle_number(Integer.parseInt(csvRecord.get("battle_number")));
				entityObject.setName(csvRecord.get("name"));
				entityObject.setYear(csvRecord.get("year").trim().equals("")?null:Integer.parseInt(csvRecord.get("year")));
				entityObject.setAttacker_king(integerValues.getAttackerKing());
				entityObject.setDefender_king(integerValues.getDefenderKing());
				entityObject.setAttacker_1(integerValues.getAttacker1());
				entityObject.setAttacker_2(integerValues.getAttacker2());
				entityObject.setAttacker_3(integerValues.getAttacker3());
				entityObject.setAttacker_4(integerValues.getAttacker4());
				entityObject.setDefender_1(integerValues.getDefender1());
				entityObject.setDefender_2(integerValues.getDefender2());
				entityObject.setDefender_3(integerValues.getDefender3());
				entityObject.setDefender_4(integerValues.getDefender4());
				entityObject.setAttacker_outcome(csvRecord.get("attacker_outcome"));
				entityObject.setBattle_type(integerValues.getBattleType());
				entityObject.setMajor_death(csvRecord.get("major_death").trim().equals("")?null:Integer.parseInt(csvRecord.get("major_death")));
				entityObject.setMajor_capture(csvRecord.get("major_capture").trim().equals("")?null:Integer.parseInt(csvRecord.get("major_capture")));
				entityObject.setAttacker_size(csvRecord.get("attacker_size").trim().equals("")?null:Integer.parseInt(csvRecord.get("attacker_size")));
				entityObject.setDefender_size(csvRecord.get("defender_size").trim().equals("")?null:Integer.parseInt(csvRecord.get("defender_size")));
				entityObject.setAttacker_commander(csvRecord.get("attacker_commander"));
				entityObject.setDefender_commander(csvRecord.get("defender_commander"));
				entityObject.setSummer(csvRecord.get("summer").trim().equals("")?null:Integer.parseInt(csvRecord.get("summer")));
				entityObject.setLocation(integerValues.getLocation());
				entityObject.setRegion(integerValues.getRegion());
				entityObject.setNote(csvRecord.get("note"));
				repository.save(entityObject);
			}
			else {
				GotDataEntity entityObject = new GotDataEntity();
				entityObject.setName(csvRecord.get("name"));
				entityObject.setYear(csvRecord.get("year").trim().equals("")?null:Integer.parseInt(csvRecord.get("year")));
				entityObject.setAttacker_king(integerValues.getAttackerKing());
				entityObject.setDefender_king(integerValues.getDefenderKing());
				entityObject.setAttacker_1(integerValues.getAttacker1());
				entityObject.setAttacker_2(integerValues.getAttacker2());
				entityObject.setAttacker_3(integerValues.getAttacker3());
				entityObject.setAttacker_4(integerValues.getAttacker4());
				entityObject.setDefender_1(integerValues.getDefender1());
				entityObject.setDefender_2(integerValues.getDefender2());
				entityObject.setDefender_3(integerValues.getDefender3());
				entityObject.setDefender_4(integerValues.getDefender4());
				entityObject.setAttacker_outcome(csvRecord.get("attacker_outcome"));
				entityObject.setBattle_type(integerValues.getBattleType());
				entityObject.setMajor_death(csvRecord.get("major_death").trim().equals("")?null:Integer.parseInt(csvRecord.get("major_death")));
				entityObject.setMajor_capture(csvRecord.get("major_capture").trim().equals("")?null:Integer.parseInt(csvRecord.get("major_capture")));
				entityObject.setAttacker_size(csvRecord.get("attacker_size").trim().equals("")?null:Integer.parseInt(csvRecord.get("attacker_size")));
				entityObject.setDefender_size(csvRecord.get("defender_size").trim().equals("")?null:Integer.parseInt(csvRecord.get("defender_size")));
				entityObject.setAttacker_commander(csvRecord.get("attacker_commander"));
				entityObject.setDefender_commander(csvRecord.get("defender_commander"));
				entityObject.setSummer(csvRecord.get("summer").trim().equals("")?null:Integer.parseInt(csvRecord.get("summer")));
				entityObject.setLocation(integerValues.getLocation());
				entityObject.setRegion(integerValues.getRegion());
				entityObject.setNote(csvRecord.get("note"));
				repository.save(entityObject);
			}
			
		}
		logger.info(Constants.EXITF,"readCSV");
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage(Constants.SUCCESS);
		return response;
	}
}
