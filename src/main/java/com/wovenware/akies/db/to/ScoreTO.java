package com.wovenware.akies.db.to;

public class ScoreTO {
	
	//Attributes
	private String description = null;
	private String detail = null;
	private int score = 0;
	private String resultMessage = null;
	
		
	//Setters and Getter
	

	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}


}
