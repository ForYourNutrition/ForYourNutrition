package com.luckyGirls.ForYourNutrition.domain;

import java.util.List;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Survey")
public class Survey implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int survey_id;
	@OneToOne
	@JoinColumn(name = "member_id")
	Member member;
	int gender;
	int birth_year;
	String effect;
	int smoking;
	int drinking;
	int exercising;

	public int getSurvey_id() {
		return survey_id;
	}
	public void setSurvey_id(int survey_id) {
		this.survey_id = survey_id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(int birth_year) {
		this.birth_year = birth_year;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public int getSmoking() {
		return smoking;
	}
	public void setSmoking(int smoking) {
		this.smoking = smoking;
	}
	public int getDrinking() {
		return drinking;
	}
	public void setDrinking(int drinking) {
		this.drinking = drinking;
	}
	public int getExercising() {
		return exercising;
	}
	public void setExercising(int exercising) {
		this.exercising = exercising;
	}
}
