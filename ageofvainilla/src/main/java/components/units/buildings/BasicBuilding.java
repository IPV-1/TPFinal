package components.units.buildings;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Appearance;
import map.tiles.Tile;

import com.uqbar.vainilla.appearances.Rectangle;
import components.recursos.TiledComponent;

public class BasicBuilding extends TiledComponent {
	private int widthInTiles;
	private int longInTiles;

	//I let this here for backward compatibility
	public BasicBuilding(Color color, double xPos, double yPos) {
		super(new Rectangle(color, Tile.WIDTH, Tile.WIDTH), xPos, yPos);
		setZ(4); //Well, this shouldn't be here
	}

	public BasicBuilding(Appearance appearance, int positionX, int positionY, int widthInTiles, int longInTiles){
		super(appearance,positionX, positionY);
		setWidthInTiles(widthInTiles);
		setLongInTiles(longInTiles);
		setZ(4);
	}

	public int getLongInTiles() {
		return longInTiles;
	}

	public void setLongInTiles(int longInTiles) {
		this.longInTiles = longInTiles;
	}

	public int getWidthInTiles() {
		return widthInTiles;
	}

	public void setWidthInTiles(int widthInTiles) {
		this.widthInTiles = widthInTiles;
	}

//	@Override
//	public void hasKilled(Unit unit) {}
}
