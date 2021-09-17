package com.got.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.got.entity.GotDataEntity;
import com.got.projection.BattleDetailsProjection;
import com.got.projection.LocationProjection;
import com.got.projection.ReadCsvProjection;


public interface GotRepository extends JpaRepository<GotDataEntity, Integer>{

	@Query(value = "SELECT (SELECT `location` FROM `locationdata` WHERE `id` = got.location) AS location,(SELECT region FROM regiondata WHERE id = got.region) AS region FROM `got`", nativeQuery = true)
	List<LocationProjection> getLocationInformation()throws Exception;
	
	@Query(value = "CALL `getBattleDetails`(:battleNumber)", nativeQuery = true)
	Optional<BattleDetailsProjection> getBattleDetails(@Param("battleNumber")Integer battleNumber) throws Exception;
	
	@Query(value = "CALL getOrInsertVariables(:p_attackerKing,:p_defenderKing,:p_attacker1,:p_attacker2,:p_attacker3,:p_attacker4,:p_defender1,:p_defender2,:p_defender3,:p_defender4,:p_battleType,:p_location,:p_region)", nativeQuery = true)
	Optional<ReadCsvProjection> getOrInsertVariables(@Param("p_attackerKing") String attackerKing,@Param("p_defenderKing") String defenderKing,@Param("p_attacker1") String attacker1,
													@Param("p_attacker2") String attacker2,@Param("p_attacker3") String attacker3,@Param("p_attacker4") String attacker4,@Param("p_defender1") String defender1,
													@Param("p_defender2") String defender2,@Param("p_defender3") String defender3,@Param("p_defender4") String defender4,@Param("p_battleType") String p_battleType,
													@Param("p_location") String location,@Param("p_region") String region) throws Exception;
}
