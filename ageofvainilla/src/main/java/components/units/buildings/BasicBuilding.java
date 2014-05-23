package components.units.buildings;

import java.awt.Color;

import map.tiles.Tile;

import com.uqbar.vainilla.appearances.Rectangle;
import components.recursos.TiledComponent;

public class BasicBuilding extends TiledComponent {
	public BasicBuilding(Color color, double xPos, double yPos) {
		super(new Rectangle(color, Tile.WIDTH, Tile.WIDTH), xPos, yPos);
	}

//	@Override
//	public void hasKilled(Unit unit) {}
}
