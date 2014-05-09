package com.uqbar.vainilla;

import com.uqbar.vainilla.events.constants.Key;

public class Camera extends GameComponent<GameScene> {
	
	public static final Camera INSTANCE = new Camera();
	
	public static final double X = 250, Y = 250;
	
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
		super.update(deltaState);
	}

}
