package components.recursos;

import map.tiles.Tile;

import com.uqbar.vainilla.appearances.Appearance;
import components.units.Unit;

public class TiledComponent extends Unit {

	public TiledComponent(Appearance appearance, double tileX, double tileY) {
		super(appearance, (int)tileX * Tile.WIDTH, (int)tileY * Tile.HEIGHT);
	}
	
	public TiledComponent(double tileX, double tileY) {
		super((int)tileX * Tile.WIDTH, (int)tileY * Tile.HEIGHT);
	}

}
