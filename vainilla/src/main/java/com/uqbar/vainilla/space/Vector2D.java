package com.uqbar.vainilla.space;

public class Vector2D {

	private double x;
	private double y;
	private double module;

	protected Vector2D() {
	}

	public Vector2D(double x, double y) {
		this.set(x, y);
	}

	public static Vector2D uVectorFromPI(double multiplier) {
		Vector2D vector = new Vector2D();
		vector.setPI(multiplier);
		return vector;
	}

	public static Vector2D uVectorFromAngle(double angle) {
		Vector2D vector = new Vector2D();
		vector.setAngle(angle);
		return vector;
	}

	public Vector2D asUnitVector() {
		if (! this.isUnitVector()) {
			this.setLocation(this.getX() / this.getModule(),
					this.getY() / this.getModule());
			this.setModule(1);
		}
		return this;
	}

	public void sum(Vector2D v2D) {
		this.set(this.getX() + v2D.getX(), this.getY() + v2D.getY());
	}

	public void multiply(double value) {
		this.setLocation(this.getX() * value, this.getY() * value);
		this.setModule(this.getModule() * value);
	}

	public void invertX() {
		this.setLocation(-this.getX(), this.getY());
	}

	public void invertY() {
		this.setLocation(this.getX(), -this.getY());
	}

	public void invert() {
		this.setLocation(-this.getX(), -this.getY());
	}

	public void set(double x, double y) {
		this.setLocation(x, y);
		this.setModule(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
	}

	/**
	 * Module is not changed
	 */
	protected void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isUnitVector() {
		return this.getModule() == 1;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public void setPI(double multiplier) {
		this.setLocation(Math.cos(Math.PI * multiplier),
				Math.sin(Math.PI * multiplier));
		this.setModule(1);
	}

	public void setAngle(double angle) {
		this.setPI(angle / 180);
	}

	public void setX(double x) {
		this.set(x, this.getY());
	}

	public void setY(double y) {
		this.set(this.getX(), y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getModule() {
		return this.module;
	}

	protected void setModule(double module) {
		this.module = module;
	}

}
