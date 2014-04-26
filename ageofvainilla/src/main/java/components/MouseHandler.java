package components;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;

public class MouseHandler extends GameComponent<GameScene> {
	
	private final Appearance MOUSE = new Rectangle(Color.red, 5, 10);
	
	public MouseHandler() {
		super();
		this.setAppearance(MOUSE);
		this.setX(10);
		this.setY(20);
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		
		Double position = deltaState.getCurrentMousePosition();
		this.setX(position.getX());
		this.setY(position.getY());
	}
}
