package components.units.states;

import com.uqbar.vainilla.DeltaState;
import components.units.MovingUnit;
import components.units.Unit;
import components.units.resources.Resource;

import config.ResourceType;

public class Farming extends UnitState {
	
	private Resource resource;
	
	public Farming(Unit unit) {
		this.setResource((Resource) unit);
	}

	@Override
	public void update(MovingUnit movingUnit, DeltaState deltaState) {
		movingUnit.getScene().getResourcesMenu().addResource(ResourceType.GOLD, 10);
	}

	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
