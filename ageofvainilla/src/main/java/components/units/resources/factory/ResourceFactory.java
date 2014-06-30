package components.units.resources.factory;


import components.units.resources.Resource;
import components.units.resources.ResourceType;

import config.Configuration;

public class ResourceFactory {


	public static Resource createGold(int tileX, int tileY, int amount) {
		return new Resource(Configuration.getSprite("gold_res"), tileX, tileY, amount, ResourceType.GOLD);
	}
	
	public static Resource createRock(int tileX, int tileY, int amount) {
		return new Resource(Configuration.getSprite("rock_res"), tileX, tileY, amount, ResourceType.ROCK);
	}
	
	public static Resource createLumber(int tileX, int tileY, int amount) {
		return new Resource(Configuration.getSprite("lumber_res"), tileX, tileY, amount, ResourceType.LUMBER);
	}
	
	public static Resource createFood(int tileX, int tileY, int amount) {
		return new Resource(Configuration.getSprite("food_res"), tileX, tileY, amount, ResourceType.FOOD);
	}

}
