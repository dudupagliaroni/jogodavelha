package com.eduardo.jogodavelha;

public class Player {

	private String name;
	private int mark;
	private int points;


	public Player(String name, int mark, int points) {
		this.name = name;
		this.mark = mark;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
