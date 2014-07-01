package components.units.buildings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.buttons.BuildingButton;
import components.buttons.WarriorButton;
import components.units.resources.ResourceType;

import config.Configuration;

public class Center extends Builder {

	public Center() {
		super(8, 6);
		
		this.setAppearance(this.getScaledAppearance(Configuration.getSprite("center")));
		
		this.setLifePoint(10000);
	}
	
	@SuppressWarnings("serial")
	@Override
	public Map<String, Integer> getCost() {
		return new HashMap<String, Integer>() {{
			put(ResourceType.LUMBER, 10);
		}};
	}
	
	@Override
	public List<BuildingButton> getButtons() {
		List<BuildingButton> buttons = new ArrayList<BuildingButton>();
		buttons.add(new WarriorButton());
		return buttons;
	}

}
