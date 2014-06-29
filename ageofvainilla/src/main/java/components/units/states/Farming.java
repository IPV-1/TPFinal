package components.units.states;

import com.uqbar.vainilla.DeltaState;
import components.units.MovingUnit;
import components.units.Unit;
import components.units.resources.Resource;

public class Farming extends UnitState {
	
	private Resource resource;
	
	public Farming(Unit unit) {
		resource = (Resource) unit;
	}

	@Override
	public void update(MovingUnit movingUnit, DeltaState deltaState) {
		if(resource.getAmount() == 0) {
			movingUnit.setState(new Waiting());
		} else {
			resource.addResourceToPlayer();
		}
	}

}
