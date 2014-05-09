package com.uqbar.vainilla.appearances;

import java.awt.Color;
import java.awt.Graphics2D;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class Rectangle implements Appearance {

	private Color color;
	private int width, height;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Rectangle(Color color, int width, int height) {
		this.color = color;
		this.height = height;
		this.width = width;
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Rectangle copy() {
		return new Rectangle(this.color, this.height, this.width);
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
		graphics.fillRect((int) (component.getX() - Camera.INSTANCE.getX()), (int) (component.getY() - Camera.INSTANCE.getY()),
				this.width, this.height);
	}

	// ****************************************************************
	// ** COLLISIONS
	// ****************************************************************
		
	@Override
	public boolean collides(double x, double y, Appearance ap, double apX,
			double apY) {
		return ap.collidesRect(apX, apY, x, y, this.getWidth(),
				this.getHeight());
	}

	@Override
	public boolean collidesCircle(double x, double y, double cx, double cy,
			double cradius) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(cx, cy,
				cradius, x, y, this.getWidth(), this.getHeight());
	}

	@Override
	public boolean collidesRect(double x, double y, double rx, double ry,
			double rwidth, double rheight) {
		return CollisionDetector.INSTANCE.collidesRectAgainstRect(rx, ry,
				rwidth, rheight, x, y, this.getWidth(), this.getHeight());
	}
	
}
