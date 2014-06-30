package components.buttons;

import components.units.buildings.WarriorBuilder;

import config.Configuration;

public class WarriorButton extends BuildingButton {
	
	public WarriorButton() {
		super(new WarriorBuilder(), Configuration.getScaledAppearance(Configuration.getSprite("stand"), 40, 40), 0, 1);
	}

}
