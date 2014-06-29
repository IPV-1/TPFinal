package components.buttons;

import components.units.buildings.Wall;

import config.Configuration;

public class WallButton extends BuildingButton {
	
	public WallButton() {
		super(new Wall(), Configuration.getSprite("wall").scaleTo(40, 40), 0, 1);
	}

}
