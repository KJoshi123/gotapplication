package com.got.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "got")
public class GotDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer battle_number;
	String name;
	Integer year;
	Integer attacker_king;
	Integer defender_king;
	Integer attacker_1;
	Integer attacker_2;
	Integer attacker_3;
	Integer attacker_4;
	Integer defender_1;
	Integer defender_2;
	Integer defender_3;
	Integer defender_4;
	String attacker_outcome;
	Integer battle_type;
	Integer major_death;
	Integer major_capture;
	Integer attacker_size;
	Integer defender_size;
	String attacker_commander;
	String defender_commander;
	Integer summer;
	Integer location;
	Integer region;
	String note;

	public Integer getBattle_number() {
		return battle_number;
	}

	public void setBattle_number(Integer battle_number) {
		this.battle_number = battle_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getAttacker_king() {
		return attacker_king;
	}

	public void setAttacker_king(Integer attacker_king) {
		this.attacker_king = attacker_king;
	}

	public Integer getDefender_king() {
		return defender_king;
	}

	public void setDefender_king(Integer defender_king) {
		this.defender_king = defender_king;
	}

	public Integer getAttacker_1() {
		return attacker_1;
	}

	public void setAttacker_1(Integer attacker_1) {
		this.attacker_1 = attacker_1;
	}

	public Integer getAttacker_2() {
		return attacker_2;
	}

	public void setAttacker_2(Integer attacker_2) {
		this.attacker_2 = attacker_2;
	}

	public Integer getAttacker_3() {
		return attacker_3;
	}

	public void setAttacker_3(Integer attacker_3) {
		this.attacker_3 = attacker_3;
	}

	public Integer getAttacker_4() {
		return attacker_4;
	}

	public void setAttacker_4(Integer attacker_4) {
		this.attacker_4 = attacker_4;
	}

	public Integer getDefender_1() {
		return defender_1;
	}

	public void setDefender_1(Integer defender_1) {
		this.defender_1 = defender_1;
	}

	public Integer getDefender_2() {
		return defender_2;
	}

	public void setDefender_2(Integer defender_2) {
		this.defender_2 = defender_2;
	}

	public Integer getDefender_3() {
		return defender_3;
	}

	public void setDefender_3(Integer defender_3) {
		this.defender_3 = defender_3;
	}

	public Integer getDefender_4() {
		return defender_4;
	}

	public void setDefender_4(Integer defender_4) {
		this.defender_4 = defender_4;
	}

	public String getAttacker_outcome() {
		return attacker_outcome;
	}

	public void setAttacker_outcome(String attacker_outcome) {
		this.attacker_outcome = attacker_outcome;
	}

	public Integer getBattle_type() {
		return battle_type;
	}

	public void setBattle_type(Integer battle_type) {
		this.battle_type = battle_type;
	}

	public Integer getMajor_death() {
		return major_death;
	}

	public void setMajor_death(Integer major_death) {
		this.major_death = major_death;
	}

	public Integer getMajor_capture() {
		return major_capture;
	}

	public void setMajor_capture(Integer major_capture) {
		this.major_capture = major_capture;
	}

	public Integer getAttacker_size() {
		return attacker_size;
	}

	public void setAttacker_size(Integer attacker_size) {
		this.attacker_size = attacker_size;
	}

	public Integer getDefender_size() {
		return defender_size;
	}

	public void setDefender_size(Integer defender_size) {
		this.defender_size = defender_size;
	}

	public String getAttacker_commander() {
		return attacker_commander;
	}

	public void setAttacker_commander(String attacker_commander) {
		this.attacker_commander = attacker_commander;
	}

	public String getDefender_commander() {
		return defender_commander;
	}

	public void setDefender_commander(String defender_commander) {
		this.defender_commander = defender_commander;
	}

	public Integer getSummer() {
		return summer;
	}

	public void setSummer(Integer summer) {
		this.summer = summer;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
