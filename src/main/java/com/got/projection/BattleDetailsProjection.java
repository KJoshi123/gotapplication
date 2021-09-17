package com.got.projection;

public interface BattleDetailsProjection {

	public Integer getBattle_number();
	public String getName();
	public Integer year();
	public String getAttacker_king();
	public String getDefender_king();
	public String getAttacker_1();
	public String getAttacker_2();
	public String getAttacker_3();
	public String getAttacker_4();
	public String getDefender_1();
	public String getDefender_2();
	public String getDefender_3();
	public String getDefender_4();
	public String getAttacker_outcome();
	public String getBattle_type();
	public Integer getMajor_death();
	public Integer getMajor_capture();
	public Integer getAttacker_size();
	public Integer getDefender_size();
	public String getAttacker_commander();
	public String getDefender_commander();
	public Integer getSummer();
	public String getLocation();
	public String getRegion();
	public String getNote();
}
