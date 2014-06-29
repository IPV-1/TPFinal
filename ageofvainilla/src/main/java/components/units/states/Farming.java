package components.units.states;

import utils.FarmingTimer;

import com.uqbar.vainilla.DeltaState;
import components.units.MovingUnit;
import components.units.Unit;
import components.units.resources.Resource;

public class Farming extends UnitState {
	
	private Resource resource;
	private FarmingTimer timer = new FarmingTimer();
	
	public Farming(Unit unit) {
		resource = (Resource) unit;
		timer.setLimit(1);
	}

	@Override
	public void update(MovingUnit unit, DeltaState deltaState) {
		timer.update(this, unit, deltaState);
	}
	
	public void farm(MovingUnit unit) {
		if(resource.getAmount() == 0) {
			unit.setState(new Waiting());
		} else {
			resource.addResourceToPlayer();
		}
	}

}
