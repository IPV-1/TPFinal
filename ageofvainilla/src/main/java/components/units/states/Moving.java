package components.units.states;

import com.uqbar.vainilla.DeltaState;

import components.units.MovingUnit;
import components.units.Unit;

public class Moving extends UnitState {
	
	private UnitState nextState;

	public Moving(UnitState state) {
		this.setNextState(state);
	}

	@Override
	public void interact(MovingUnit self, Unit unit) {
		this.setNextState(new Atacking(unit));
	}

	@Override
	public void update(MovingUnit self, DeltaState deltaState) {
		self.getPath().update(deltaState);
		if(!self.getPath().isTraveling()) {
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
