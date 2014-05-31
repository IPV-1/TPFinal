package components;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import map.tiles.Tile;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.Coord;
import components.units.Flag;
import components.units.MovingUnit;
import components.units.Unit;

import config.Configuration;

public class MouseHandler extends BasicAgeComponent {
	
	private List<MovingUnit> selected = new ArrayList<MovingUnit>();
	
	public MouseHandler() {
		super(Configuration.getSprite("pointer"), 0, 0);
		setZ(10);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		
		Double position = deltaState.getCurrentMousePosition();
		this.setX(position.getX());
		this.setY(position.getY());
		
		if(deltaState.isMouseButtonReleased(MouseButton.LEFT)) {
			this.getElementUnderMouse().seleccionate(this, deltaState);
		}
	}

	public Unit getElementUnderMouse() {
		// TODO: get tile position teniendo en cuenta la camara
		Coord tileC = new Coord(this.getX(), this.getY()).getTile(Tile.WIDTH);
		
		Tile tile = getScene().getMap().get(tileC);

		if(! tile.isOccuppied()) {
			return new Flag(this.getX(), this.getY());
		}
		
		return tile.getOcuppant();
	}
	
	/**********************
	 ***    Selection   *** 
	 **********************/
	public void addSelected(MovingUnit unit) {
		this.getSelected().add(unit);
	}

	public List<MovingUnit> getSelected() {
		return selected;
	}

	public void setSelected(List<MovingUnit> selected) {
		this.selected = selected;
	}
	
	public boolean isSelected(MovingUnit unit) {
		return this.getSelected().contains(unit);
	}
	
	public void singleSelect(MovingUnit unit) {
		this.getSelected().clear();
		this.addSelected(unit);
	}
	
}
