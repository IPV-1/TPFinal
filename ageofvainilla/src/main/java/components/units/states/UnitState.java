package components.units.states;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;

import components.units.MovingUnit;
import components.units.Unit;
import config.Configuration;

public abstract class UnitState {
	
	public void interact(MovingUnit self, Unit unit) {
		self.setMoveTo(unit);
	}

	public abstract void update(MovingUnit movingUnit, DeltaState deltaState);

	public Appearance getAppearance() {
		return Configuration.getScaledAppearance(Configuration.getSprite("stand"));
	}
	
}
