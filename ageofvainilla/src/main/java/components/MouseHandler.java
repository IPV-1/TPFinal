package components;

import java.awt.geom.Point2D.Double;

import map.tiles.Tile;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.space.Coord;
import components.units.Flag;
import components.units.Unit;

import config.Configuration;

public class MouseHandler extends BasicAgeComponent {
	
	public MouseHandler() {
		super(Configuration.getSprite("pointer"), 0, 0);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		
		Double position = deltaState.getCurrentMousePosition();
		this.setX(position.getX());
		this.setY(position.getY());
	}

	public Unit getElementUnderMouse() {
		Coord tileC = new Coord(this.getX(), this.getY()).getTile(Tile.WIDTH);
		
		Tile tile = getScene().getMap().get(tileC);

		if(! tile.isOccuppied()) {
			return new Flag(this.getX(), this.getY());
		}
		
		return tile.getOcuppant();
	}
	
}
