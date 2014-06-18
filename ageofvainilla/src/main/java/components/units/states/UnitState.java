package components.units.states;

import com.uqbar.vainilla.DeltaState;

import components.units.MovingUnit;
import components.units.Unit;

public abstract class UnitState {
	
	public abstract void interact(MovingUnit self, Unit unit);

	public abstract void update(MovingUnit movingUnit, DeltaState deltaState);
	
}
