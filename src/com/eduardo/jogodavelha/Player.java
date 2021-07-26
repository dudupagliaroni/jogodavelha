package com.eduardo.jogodavelha;

public class Player {

	private String name;
	private int points;

	public Player(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}

}
