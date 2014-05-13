package com.uqbar.vainilla.appearances;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import java.awt.*;

public class FilledArc implements Appearance {

	private Color color;
	private final int radius;
	private final double startAngle;
	private final double arcAngle;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public FilledArc(Color color, int radius, double startAngle, double arcAngle) {
		this.color = color;
		this.radius = radius;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.radius * 2;
	}

	@Override
	public double getHeight() {
		return this.radius * 2;
	}

	@Override
	@SuppressWarnings("unchecked")
	public FilledArc copy() {
		return new FilledArc(this.color, this.radius, this.startAngle,
				this.arcAngle);
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(double delta) {
	}

	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
		int x = (int) (component.getX());
		int y = (int) (component.getY());

		graphics.setColor(this.color);
		graphics.fillArc(x, y, (int) this.getWidth(), (int) this.getHeight(),
				(int) this.startAngle, (int) this.arcAngle);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	// ****************************************************************
	// ** COLLISIONS
	// ****************************************************************

	@Override
	public boolean collides(double x, double y, Appearance ap, double apX,
			double apY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collidesCircle(double x, double y, double cx, double cy,
			double cradius) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstArc(cx, cy,
				cradius, x, y, getRadius(), getStartAngle(), getArcAngle());
	}

	@Override
	public boolean collidesRect(double x, double y, double rx, double ry,
			double rwidth, double rheight) {
		return CollisionDetector.INSTANCE.collidesRectAgainstArc(rx, ry,
				rwidth, rheight, x, y, getRadius(), getStartAngle(), getArcAngle());
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************
	public int getRadius() {
		return radius;
	}

	public double getStartAngle() {
		return startAngle;
	}

	public double getArcAngle() {
		return arcAngle;
	}

}