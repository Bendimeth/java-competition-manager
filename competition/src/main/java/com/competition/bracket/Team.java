package com.competition.bracket;

public class Team {
	private String teamName;
	private int score;

	public Team(){
		teamName = "";
		score = 0;
	}

	/**
	 * Constructor that creates a team with a given name and starting rank
	 * 
	 * @param teamName Team's name
	 * @param teamRank Starting rank
	 */
	public Team(String teamName, Integer teamRank){
		this.teamName = teamName;
		this.score = teamRank;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getTeamScore() {
		return score;
	}

	public void setTeamScore(Integer teamRank) {
		this.score = teamRank;
	}

	public void setTeam(Team a){
		this.teamName = a.teamName;
		this.score = a.score;
	}
}
