package components.units.states;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import components.units.MovingUnit;
import components.units.Unit;

public class Waiting extends UnitState {

	@Override
	public void interact(MovingUnit self, Unit unit) {
		if(self.collides((GameComponent)unit)) {
			self.attack(unit);
		} else {
			self.setMoveTo(unit);
		}
	}

	@Override
	public void update(MovingUnit movingUnit, DeltaState deltaState) {
	}

}
