package components.units.resources.factory;


import components.units.resources.Resource;

import config.Configuration;

public class ResourceFactory {


	public static Resource createGold(int tileX, int tileY, int amount) {
		return new Resource(Configuration.getSprite("gold_res"), tileX, tileY, amount, "GOLD");
	}

}
