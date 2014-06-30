package components.units.states;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import components.units.MovingUnit;
import components.units.Unit;

import config.Configuration;

public class Moving extends UnitState {
	
	private UnitState nextState;

	public Moving(UnitState state) {
		this.setNextState(state);
	}
	
	@Override
	public Appearance getAppearance() {
		return Configuration.getScaledAnimation("walking_unit", 29, 29);
	}

	@Override
	public void interact(MovingUnit self, Unit unit) {
		this.setNextState(new Attacking(unit));
	}

	@Override
	public void update(MovingUnit self, DeltaState deltaState) {
		self.getPath().update(deltaState);
		if(!self.getPath().isTravelingToDestiny()) {
			self.setState(getNextState());
		}
	}
	
	public UnitState getNextState() {
		return nextState;
	}

	public void setNextState(UnitState nextState) {
		this.nextState = nextState;
	}

}
