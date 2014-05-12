package components.buildings;

import java.awt.Color;

import map.Tile;

import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.space.UnitVector2D;

import components.units.Unit;

public class BasicBuilding extends Unit {
	public BasicBuilding(Color color, double xPos, double yPos) {
		super(new Rectangle(color, Tile.WIDTH, Tile.WIDTH), xPos, yPos, new UnitVector2D(1, 1), 0);
	}
}
