package components.buttons;

import components.units.buildings.WarriorBuilder;

import config.Configuration;

public class WarriorButton extends BuildingButton {
	
	public WarriorButton() {
		super(new WarriorBuilder(), Configuration.getSprite("stand"), 0, 1);
	}

}
