package components.buttons;

import components.MouseHandler;
import components.units.MovingUnit;
import components.units.Unit;

import config.Configuration;

public class StopButton extends BuildingButton {
	
	public StopButton() {
		super(null, Configuration.getSprite("stop"), 0, 1);
	}
	
	@Override
	public void clickedBy(MouseHandler mouse) {
		for(Unit unit : mouse.getSelected()) {
			if(unit instanceof MovingUnit) {
				MovingUnit unidad = (MovingUnit)unit;
				unidad.getPath().lightStop();
//				unidad.setState(new Waiting());
			}
		}
	}

}
