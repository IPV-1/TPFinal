package com.uqbar.vainilla.appearances;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class Triangle implements Appearance {

	protected Color color;
	protected Point2D.Double p1, p2, p3;

	public Triangle(Color color, Double p1, Double p2, Double p3) {
		super();
		this.color = color;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	@Override
	public double getWidth() {
		double min = Math.min(p3.x, Math.min(p1.x, p2.x));
		double max = Math.max(p3.x, Math.max(p1.x, p2.x));
		return max - min;
	}

	@Override
	public double getHeight() {
		double min = Math.min(p3.y, Math.min(p1.y, p2.y));
		double max = Math.max(p3.y, Math.max(p1.y, p2.y));
		return max - min;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Triangle copy() {
		return new Triangle(this.color, this.p1, this.p2, this.p3);
	}

	@Override
	public void update(double delta) {
	}

	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
		graphics.setColor(this.color);
		int[] xs = { (int) this.p1.x, (int) this.p2.x, (int) this.p3.x };
		int[] ys = { (int) this.p1.y, (int) this.p2.y, (int) this.p3.y };
		graphics.fillPolygon(xs, ys, 3);
	}
	
	// ****************************************************************
	// ** COLLISIONS
	// ****************************************************************

	@Override
	public boolean collides(double x, double y, Appearance ap, double apX,
			double apY) {
		// TODO
		return (Boolean) null;
	}

	@Override
	public boolean collidesCircle(double x, double y, double cx, double cy,
			double cradius) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstTriangle(cx, cy,
				cradius, p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
	}

	@Override
	public boolean collidesRect(double x, double y, double rx, double ry,
			double rwidth, double rheight) {
		return CollisionDetector.INSTANCE.collidesRectAgainstTriangle(rx, ry,
				rwidth, rheight, p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
	}

}
