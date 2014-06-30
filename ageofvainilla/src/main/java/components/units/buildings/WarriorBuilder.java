package components.units.buildings;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import map.tiles.Tile;

import components.factors.Factor;
import components.units.MovingUnit;
import components.units.resources.ResourceType;

import config.Configuration;

public class WarriorBuilder extends Builder {

	public WarriorBuilder() {
		super(1, 1);
		
		this.setAppearance(this.getScaledAppearance(Configuration.getSprite("stand")));
	}
	
	@Override
	public void buildIn(int tileX, int tileY) {
		if (this.canBuild(tileX, tileY)) {
			MovingUnit unit = new MovingUnit(Factor.getMyFactor(), Color.BLACK, tileX*Tile.WIDTH, tileY*Tile.HEIGHT);
			this.getScene().getResourcesMenu().updateResources(this);
			this.getScene().addMovingUnit(unit);
		} else {
			// Do what?
		}
	}
	
	@Override
	public Map<String, Integer> getCost() {
		HashMap<String, Integer> costo = new HashMap<String, Integer>();
		costo.put(ResourceType.GOLD, 20);
		costo.put(ResourceType.LUMBER, 10);
		
		return costo;
	}

}
