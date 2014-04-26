package com.uqbar.vainilla.space;

public class Posicionable {
	
	private Coord position;
	
	public Posicionable(double x, double y) {
		this.setPosition(new Coord(x, y));
	}
	
	public Posicionable(Coord position) {
		this.setPosition(position);
	}

	public Coord getPosition() {
		return position;
	}

	public void setPosition(Coord position) {
		this.position = position;
	}
	
	public double getX() {
		return this.getPosition().getX();
	}
	
	public void setX(double x) {
		this.getPosition().setX(x);
	}
	
	public double getY() {
		return this.getPosition().getY();
	}
	
	public void setY(double y) {
		this.getPosition().setY(y);
	}
	
}
