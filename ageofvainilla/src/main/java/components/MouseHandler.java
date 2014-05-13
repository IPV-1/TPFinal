package components;

import java.awt.geom.Point2D.Double;

import com.uqbar.vainilla.DeltaState;
import components.units.Unit;

public class MouseHandler extends BasicAgeComponent {
	
	public MouseHandler() {
		super();
		this.setX(10);
		this.setY(20);
	}

    @Override
    public void onSceneActivated(){
        this.setAppearance(getGame().getResource("pointer"));
    }

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		
		Double position = deltaState.getCurrentMousePosition();
		this.setX(position.getX());
		this.setY(position.getY());
	}

	public Unit getElementUnderMouse() {
		return getScene().getMockEnemy();
	}
}
