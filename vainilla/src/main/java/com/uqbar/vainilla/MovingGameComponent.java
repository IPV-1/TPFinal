package com.uqbar.vainilla;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.space.Coord;
import com.uqbar.vainilla.space.Vector2D;

/**
 * GameComponent with velocity (speed and vector)
 */
public class MovingGameComponent<SceneType extends GameScene> extends GameComponent<SceneType> {

	protected Vector2D vector;
	protected double speed;

	public MovingGameComponent() {
		super();
		this.setVector(new Vector2D(1, 1));
		this.speed = 0;
	}

	public MovingGameComponent(double xPos, double yPos, Vector2D direction, double speed) {
		super(xPos, yPos);
		this.setVector(direction);
		this.speed = speed;
	}

	public MovingGameComponent(Appearance appearance, double xPos, double yPos,
			Vector2D direction, double speed) {
		super(appearance, xPos, yPos);
		this.vector = direction;
		this.speed = speed;
	}

	@Override
	public void update(DeltaState deltaState) {
		double xPosition = this.getX() + this.getVector().getX()
				* this.getSpeedFactor(deltaState);
		double yPosition = this.getY() + this.getVector().getY()
				* this.getSpeedFactor(deltaState);
		this.setX(xPosition);
		this.setY(yPosition);

		super.update(deltaState);
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
	
	public Vector2D getVector() {
		return this.vector;
	}
	
	public void setVector(Vector2D vector) {
		this.vector = vector;
	}
	
	public Coord getCoord() {
		return new Coord(this.getX(), this.getY());
	}

}
