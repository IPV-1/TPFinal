package components;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import map.tiles.Tile;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.MouseButton;
import components.buttons.BasicAgeButton;
import components.factors.Factor;
import components.units.Flag;
import components.units.Unit;
import components.units.buildings.Builder;

import config.Configuration;

public class MouseHandler extends BasicAgeComponent {
	
	private List<Unit> selected = new ArrayList<Unit>();
	
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
			
	    	if(this.inResourcesMenu()) {
	    		// Si esta arriba del menu superior
	    		// No hacer nada
	    	} else if(this.inControlPanel()) {
	    		// Si esta arriba del menu inferior
	    		// Checkear click en botones
	    		this.checkClickControlPanel();
	    	} else {
	    		// Si esta arriba del mapa
	    		// Seleccionar elemento
	    		this.getElementUnderMouse().seleccionate(this, deltaState);
	    	}
		}
	}

	private void checkClickControlPanel() {
		getScene().getControlPanel().clickedBy(this);
	}

	public Unit getElementUnderMouse() {
		
		Tile tile = getScene().getMap().getTileCamera(this.getX(), this.getY());

		if(! tile.isOccuppied()) {
			return new Flag(this.getX() / Tile.WIDTH, this.getY() / Tile.HEIGHT);
		}
		
		return tile.getOcuppant();
	}


	private boolean inControlPanel() {
		return this.getY() >= Configuration.getDisplayHeight() - getScene().getControlPanel().getHeight();
	}

	private boolean inResourcesMenu() {
		return this.getY() <= getScene().getResourcesMenu().getHeight();
	}
	
	public boolean shouldInteract(Unit unit, DeltaState deltaState) {
		return !this.inControlPanel() &&
				!this.inResourcesMenu() &&
				deltaState.isMouseButtonReleased(MouseButton.RIGHT) &&
				this.isMyUnit(unit) &&
				this.isSelected(unit);
	}
	
	
	/**********************
	 ***    Selection   *** 
	 **********************/
	public void addSelected(Unit unit) {
		this.getSelected().add(unit);
	}

	public List<Unit> getSelected() {
		return selected;
	}
	
	public boolean isSelected(Unit unit) {
		return this.getSelected().contains(unit);
	}
	
	public void singleSelect(Unit unit) {
		this.clearSelected();
		this.addSelected(unit);
	}

	public void clearSelected() {
		for (Unit selected : this.getSelected()) {
			selected.deseleccionate(this);
		}
		
		this.getSelected().clear();
	}
	
	public boolean isClicking(BasicAgeButton button) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				this.getX(), this.getY(), 2,
				button.getX(), button.getY(), button.getWidth(), button.getHeight()
		);
	}

	public void deleteSelected() {
		for(Unit unit : this.getSelected()) {
			unit.removeFromMap();
			unit.destroy();
		}
		
		this.clearSelected();
	}
	
	public boolean shouldInteractSeleted() {
		for(Unit unit : this.getSelected()) {
			if(! this.isMyUnit(unit) || this.isABuilder(unit)) {
				return false;
			}
		}
		
		return true;
	}

	private boolean isMyUnit(Unit unit) {
		return unit.getFactor().equals(Factor.BLUE);
	}
	
	private boolean isABuilder(Unit unit) {
		return unit instanceof Builder;
	}

}
