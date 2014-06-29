package utils;

import com.uqbar.vainilla.DeltaState;
import components.units.MovingUnit;
import components.units.states.Farming;

public class FarmingTimer extends Timer {

	public void update(Farming farming, MovingUnit unit, DeltaState deltaState) {
		if(this.getTime() > this.getLimit()) {
			farming.farm(unit);
			this.reset();
		} else {
			this.addTime(deltaState.getDelta());
		}
	}
	
}
