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
	
	@Override
	public Map<String, Integer> getCost() {
		HashMap<String, Integer> costo = new HashMap<String, Integer>();
		costo.put(ResourceType.ROCK, 20);
		
		return costo;
	}

}
