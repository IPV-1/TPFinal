package components.units.buildings;

import java.util.HashMap;
import java.util.Map;

import config.Configuration;
import config.ResourceType;

public class House extends Builder {

	public House() {
		super(2, 2);
		
		this.setAppearance(this.getScaledAppearance(Configuration.getSprite("house")));
	}
	
	@Override
	public Map<String, Integer> getCost() {
		HashMap<String, Integer> costo = new HashMap<String, Integer>();
		costo.put(ResourceType.GOLD, 20);
		costo.put(ResourceType.LUMBER, 10);
		
		return costo;
	}

}
