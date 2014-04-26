package com.uqbar.vainilla.space;

import java.awt.geom.Point2D.Double;

public class Coord {
	private double x;
	private double y;
	
	public Coord(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	public Coord(Double position) {
		this.setX(position.getX());
		this.setY(position.getY());
	}

	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public UnitVector2D getDirectionTo(Coord to) {
		double toX = to.getX() - this.getX();
		double toY = to.getY() - this.getY();
		
		return new UnitVector2D(toX, toY);
	}

}
