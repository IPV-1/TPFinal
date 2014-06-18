package components.units;

import java.awt.Color;

import map.path.Path;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.space.UnitVector2D;
import components.MouseHandler;
import components.MovingGameComponent;
import components.units.states.Atacking;
import components.units.states.Moving;
import components.units.states.UnitState;
import components.units.states.Waiting;

public class MovingUnit extends MovingGameComponent {

	public static final int SPEED = 200;

	protected UnitState state = new Waiting();
	
	protected final Path path = new Path(this);
	
	public MovingUnit(Appearance rectangle, double xPos, double yPos, UnitVector2D unitVector2D, int speed) {
		super(rectangle, xPos, yPos, unitVector2D, speed);
	}

	public MovingUnit(Color color, double xPos, double yPos) {
		this(new Rectangle(color, 10, 12), xPos, yPos, new UnitVector2D(1, 1), 0);//SPEED);
	}

	@Override
	public void update(DeltaState deltaState) {

		if (getMouse().shouldInteract(this, deltaState)) {
			this.interact(getMouse().getElementUnderMouse());
		}

		getState().update(this, deltaState);
		
		super.update(deltaState);
	}
	
	@Override
	public void hasKilled(Unit unit) {
		this.setState(new Waiting());
	}

	@Override
	public void seleccionate(MouseHandler mouseHandler, DeltaState deltaState) {
		super.seleccionate(mouseHandler, deltaState);
		
		if(deltaState.isKeyBeingHold(Key.CTRL) && !mouseHandler.isSelected(this)) {
			mouseHandler.addSelected(this);
		} else {
			mouseHandler.singleSelect(this);
		}
	}

	public void interact(Unit unit) {
		this.getState().interact(this, unit);
	}
	
	public void attack(Unit unit) {
		unit.interactedBy(this);
	}

	public void setMoveTo(Unit unit) {
		this.setState(new Moving(new Atacking(unit)));
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Path getPath() {
		return path;
	}
	
}
