package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Survey")
public class Survey implements Serializable {
    @Id
    private int survey_id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "gender")
    private int gender;

    @Column(name = "birth_year")
    private int birth_year;

    @Column(name = "effect")
    private String effect;

    @Column(name = "smoking")
    private int smoking;

    @Column(name = "drinking")
    private int drinking;

    @Column(name = "exercising")
    private int exercising;

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