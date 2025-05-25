package com.notice.secure.model;


public class QuestionWrapper {

	private Integer id;
	private String question;
	public QuestionWrapper(Integer id, String question) {
		super();
		this.id = id;
		this.question = question;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
}
