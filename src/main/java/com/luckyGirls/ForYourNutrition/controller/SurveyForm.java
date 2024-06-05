package com.luckyGirls.ForYourNutrition.controller;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SurveyForm implements Serializable {
	private int smoking;
	private int drinking;
	private int exercising;
	private List<Integer> effect;

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
	public List<Integer> getEffect() {
		return effect;
	}
	public void setEffect(List<Integer> effect) {
		this.effect = effect;
	}
}
