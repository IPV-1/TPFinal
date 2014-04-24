package components;

import java.awt.Color;

import resource.Resource;
import scenes.FieldScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.MovingGameComponent;
import com.uqbar.vainilla.UnitVector2D;
import com.uqbar.vainilla.sound.Sound;

public class Ball extends MovingGameComponent<FieldScene> {

	public static final int INITIAL_SPEED = 200;
	protected static final Sound HIT_SOUND = Resource.getSound("ball_hit.wav");
	protected static final Sound LOSE_SOUND = Resource.getSound("ball_lose.wav");
	protected static final float VOLUME = 0.3f;
	

	public Ball(Color color, double xPos, double yPos, UnitVector2D direction) {
		super(Resource.getSprite("ball.png"), xPos, yPos, direction.getX(),
				direction.getY(), INITIAL_SPEED);
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
	}

}
