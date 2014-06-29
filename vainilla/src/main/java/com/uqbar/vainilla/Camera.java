package com.uqbar.vainilla;

import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.space.Coord;

public class Camera extends GameComponent<GameScene> {
	
	public static final Camera INSTANCE = new Camera();
	
	public static final double X = 250, Y = 250;
	private double xUpperLimit, yUpperLimit, xLowerLimit, yLowerLimit;
	private double screenWidth, screenHeight;
	
	protected Camera(){}
	
	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyBeingHold(Key.UP)) {
			this.setY(this.getY() - Y * deltaState.getDelta());
		}
		if(deltaState.isKeyBeingHold(Key.DOWN)) {
			this.setY(this.getY() + Y * deltaState.getDelta());
		}
		if(deltaState.isKeyBeingHold(Key.LEFT)) {
			this.setX(this.getX() - X * deltaState.getDelta());
		}
		if(deltaState.isKeyBeingHold(Key.RIGHT)) {
			this.setX(this.getX() + X * deltaState.getDelta());
		}
		this.limit();
		super.update(deltaState);
	}
	
	protected void limit() {
		this.setX(Math.max(this.getxLowerLimit(), this.getX()));
		this.setX(Math.min(this.getxUpperLimit() - this.getScreenWidth(), this.getX()));
		this.setY(Math.max(this.getyLowerLimit(), this.getY()));
		this.setY(Math.min(this.getyUpperLimit() - this.getScreenHeight(), this.getY()));
	}
	
	public void setLimits(double xUpper, double yUpper, double xLower, double yLower, double width, double height) {
		this.setxUpperLimit(xUpper);
		this.setyUpperLimit(yUpper);
		this.setxLowerLimit(xLower);
		this.setyLowerLimit(yLower);
		this.setScreenWidth(width);
		this.setScreenHeight(height);
	}

	public Coord getCoord() {
		return new Coord(X, Y);
	}

	protected double getxUpperLimit() {
		return xUpperLimit;
	}

	protected void setxUpperLimit(double xUpperLimit) {
		this.xUpperLimit = xUpperLimit;
	}

	protected double getyUpperLimit() {
		return yUpperLimit;
	}

	protected void setyUpperLimit(double yUpperLimit) {
		this.yUpperLimit = yUpperLimit;
	}

	protected double getxLowerLimit() {
		return xLowerLimit;
	}

	protected void setxLowerLimit(double xLowerLimit) {
		this.xLowerLimit = xLowerLimit;
	}

	protected double getyLowerLimit() {
		return yLowerLimit;
	}

	protected void setyLowerLimit(double yLowerLimit) {
		this.yLowerLimit = yLowerLimit;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected void setScreenWidth(double screenWidth) {
		this.screenWidth = screenWidth;
	}

	protected double getScreenHeight() {
		return screenHeight;
	}

	protected void setScreenHeight(double screenHeight) {
		this.screenHeight = screenHeight;
	}

}
