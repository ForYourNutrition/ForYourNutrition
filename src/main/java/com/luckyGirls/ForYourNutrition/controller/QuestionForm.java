package com.luckyGirls.ForYourNutrition.controller;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QuestionForm implements Serializable {
	private int question_id;
	private String title;
	private String content;

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
