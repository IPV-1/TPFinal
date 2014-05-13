package components;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import map.Tile;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.space.Coord;

import components.units.Flag;
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
		Coord tile = new Coord(this.getX(), this.getY()).getTile(Tile.WIDTH);
		
		System.out.println(getScene().getMap().get(tile).isEmpty());
		
		if(getScene().getMap().get(tile).isEmpty()) {
			return new Flag(this.getX(), this.getY());
		}
		
		return getScene().getMockEnemy();
	}
	
	@Override
	public void render(Graphics2D graphics) {
		this.setX(this.getX() + Camera.INSTANCE.getX());
		this.setY(this.getY() + Camera.INSTANCE.getY());
		super.render(graphics);
		this.setX(this.getX() - Camera.INSTANCE.getX());
		this.setY(this.getY() - Camera.INSTANCE.getY());
	}
}
