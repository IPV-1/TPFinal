package com.uqbar.vainilla.appearances;

import java.awt.Color;
import java.awt.Graphics2D;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class Circle implements Appearance {

	private Color color;
	private int diameter;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Circle(Color color, int diameter) {
		this.color = color;
		this.diameter = diameter;
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.diameter;
	}

	@Override
	public double getHeight() {
		return this.diameter;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Circle copy() {
		return new Circle(this.color, this.diameter);
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(double delta) {
	}

	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
		graphics.setColor(this.color);
		graphics.fillOval((int) component.getX(), (int) component.getY(),
				this.diameter, this.diameter);
	}

	// ****************************************************************
	// ** COLLISIONS
	// ****************************************************************

	@Override
	public boolean collides(double x, double y, Appearance ap, double apX,
			double apY) {
		return ap.collidesCircle(apX, apY, x, y, this.getHeight());
	}

	@Override
	public boolean collidesCircle(double x, double y, double cx, double cy,
			double cradius) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstCircle(x, y,
				this.getHeight(), cx, cy, cradius);
	}

	@Override
	public boolean collidesRect(double x, double y, double rx, double ry,
			double rwidth, double rheight) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(x, y,
				this.getHeight(), rx, ry, rwidth, rheight);
	}

}