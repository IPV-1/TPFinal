package components.units.buildings;

import java.util.HashMap;
import java.util.Map;

import components.units.resources.ResourceType;

import config.Configuration;

public class Wall extends Builder {

	public Wall() {
		super(1, 1);
		
		this.setAppearance(this.getScaledAppearance(Configuration.getSprite("wall")));
		
		this.setLifePoint(5000);
	}
	
	@SuppressWarnings("serial")
	@Override
	public Map<String, Integer> getCost() {
		return new HashMap<String, Integer>() {{
			put(ResourceType.ROCK, 20);
		}};
	}

}
