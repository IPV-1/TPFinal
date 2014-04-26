package com.uqbar.vainilla.appearances;


import java.awt.Graphics2D;
import com.uqbar.vainilla.GameComponent;

public class Invisible implements Appearance {

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return 0;
	}

	@Override
	public double getHeight() {
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Invisible copy() {
		return this;
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(double delta) {
	}

	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
	}
	
	
	// ****************************************************************
	// ** COLLISIONS
	// ****************************************************************
	
	@Override
	public boolean collides(double x, double y, Appearance ap, double apX,
			double apY) {
		return false;
	}

	@Override
	public boolean collidesCircle(double x, double y, double cx, double cy,
			double cratio) {
		return false;
	}

	@Override
	public boolean collidesRect(double x, double y, double rx, double xy,
			double rwidth, double rheight) {
		return false;
	}
}