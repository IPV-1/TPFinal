package components;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import map.tiles.Tile;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.MouseButton;
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
			
	    	if(this.getY() <= getScene().getResourcesMenu().getHeight()) {
	    		// Si esta arriba del menu superior no hacer nada
	    	} else if(this.getY() >= Configuration.getDisplayHeight() - getScene().getControlPanel().getHeight()) {
	    		// Si esta arriba del menu inferior
	    		
	    		// Checkear clik en botones
	    	
	    	} else {
	    		// Si esta arriba del mapa			
	    		this.getElementUnderMouse().seleccionate(this, deltaState);
	    	}
		}
	}

	public Unit getElementUnderMouse() {
		
		Tile tile = getScene().getMap().getTileCamera(this.getX(), this.getY());

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
	
	public boolean isSelected(MovingGameComponent unit) {
		return this.getSelected().contains(unit);
	}
	
	public void singleSelect(MovingUnit unit) {
		this.getSelected().clear();
		this.addSelected(unit);
	}
	
}
