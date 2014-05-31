package components.units.buildings;

import java.awt.Color;
import java.util.Map;

import com.uqbar.vainilla.appearances.Appearance;
import components.menus.ResourcesMenu;
import map.tiles.Tile;

import com.uqbar.vainilla.appearances.Rectangle;
import components.recursos.TiledComponent;

public class BasicBuilding extends TiledComponent {
	private int widthInTiles;
	private int longInTiles;
	private Map<String, Integer> cost;

	//I let this here for backward compatibility
	public BasicBuilding(Color color, double xPos, double yPos) {
		super(new Rectangle(color, Tile.WIDTH, Tile.WIDTH), xPos, yPos);
	}

	public BasicBuilding(Appearance appearance, int positionX, int positionY, int widthInTiles, int longInTiles) {
		super(appearance, positionX, positionY);
		setWidthInTiles(widthInTiles);
		setLongInTiles(longInTiles);
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

	public boolean enough(ResourcesMenu menu) {
		boolean hasEnough = true;
		for (Map.Entry<String, Integer> cursor : cost.entrySet()) {
			hasEnough &= menu.hasEnoughOf(cursor.getKey(), cursor.getValue());
		}
		return hasEnough;
	}

	public void subtract(ResourcesMenu menu) {
		for (Map.Entry<String, Integer> cursor : cost.entrySet()) {
			menu.subtract(cursor.getKey(), cursor.getValue());
		}
	}


//	@Override
//	public void hasKilled(Unit unit) {}
}
