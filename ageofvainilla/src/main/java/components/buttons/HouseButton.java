package components.buttons;

import components.units.buildings.House;

import config.Configuration;

public class HouseButton extends BuildingButton {
	
	public HouseButton() {
		super(new House(), Configuration.getSprite("house").scaleTo(40, 40), 0, 1);
	}

}
