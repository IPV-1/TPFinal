package com.uqbar.vainilla;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.space.Coord;
import com.uqbar.vainilla.space.UnitVector2D;

/**
 * GameComponent with velocity (speed and unit vector)
 */
public class MovingGameComponent<SceneType extends GameScene> extends GameComponent<SceneType> {

	protected UnitVector2D direction;
	protected double speed;

	public MovingGameComponent() {
		super();
		this.setDirection(new UnitVector2D(1, 1));
		this.speed = 0;
	}

	public MovingGameComponent(double xPos, double yPos, UnitVector2D direction, double speed) {
		super(xPos, yPos);
		this.setDirection(direction);
		this.speed = speed;
	}

	public MovingGameComponent(Appearance appearance, double xPos, double yPos,
			UnitVector2D direction, double speed) {
		super(appearance, xPos, yPos);
		this.direction = direction;
		this.speed = speed;
	}

	@Override
	public void update(DeltaState deltaState) {
		this.move(deltaState);

		super.update(deltaState);
	}

	public void move(DeltaState deltaState) {
		double xPosition = this.getX() + this.getDirection().getX()
				* this.getSpeedFactor(deltaState);
		double yPosition = this.getY() + this.getDirection().getY()
				* this.getSpeedFactor(deltaState);
		this.setX(xPosition);
		this.setY(yPosition);
	}

	public double getSpeedFactor(DeltaState deltaState) {
		return this.getSpeed() * deltaState.getDelta();
	}

    public void center() {
		this.setX(this.getScene().getGame().getDisplayWidth() / 2 - (this.getWidth() / 2));
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public UnitVector2D getDirection() {
		return this.direction;
	}

	public void setDirection(UnitVector2D direction) {
		this.direction = direction;
	}

	public Coord getCoord() {
		return new Coord(this.getX(), this.getY());
	}

}