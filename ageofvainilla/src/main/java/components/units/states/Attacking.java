package components.units.states;

import com.uqbar.vainilla.DeltaState;
import components.units.MovingUnit;
import components.units.Unit;

public class Attacking extends UnitState {
	
	Unit enemy;
	
	public Attacking(Unit enemy) {
		this.enemy = enemy;
	}

	@Override
	public void interact(MovingUnit self, Unit unit) {
	}

	@Override
	public void update(MovingUnit movingUnit, DeltaState deltaState) {
		movingUnit.attack(this.enemy);
	}

}
