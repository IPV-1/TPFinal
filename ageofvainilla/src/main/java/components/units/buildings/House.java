package components.units.buildings;

import java.util.HashMap;
import java.util.Map;

import components.units.resources.ResourceType;

import config.Configuration;

public class House extends Builder {

	public House() {
		super(2, 2);
		
		this.setAppearance(this.getScaledAppearance(Configuration.getSprite("house")));
	}
	
	@SuppressWarnings("serial")
	@Override
	public Map<String, Integer> getCost() {
		return new HashMap<String, Integer>() {{
			put(ResourceType.GOLD, 20);
			put(ResourceType.LUMBER, 10);
		}};
	}

}
