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
	private int heightInTiles;
	private Map<String, Integer> cost;

	//I let this here for backward compatibility
	public BasicBuilding(Color color, double xPos, double yPos) {
		super(new Rectangle(color, Tile.WIDTH, Tile.WIDTH), xPos, yPos);
	}

	public BasicBuilding(Appearance appearance, double positionX, double positionY, int widthInTiles, int heightInTiles) {
		super(appearance, positionX, positionY);
		setWidthInTiles(widthInTiles);
		setHeightInTiles(heightInTiles);
	}

	public int getHeightInTiles() {
		return heightInTiles;
	}

	public void setHeightInTiles(int heightInTiles) {
		this.heightInTiles = heightInTiles;
	}

	public int getWidthInTiles() {
		return widthInTiles;
	}

	public void setWidthInTiles(int widthInTiles) {
		this.widthInTiles = widthInTiles;
	}

	public boolean enough(ResourcesMenu menu) {
		boolean hasEnough = true;
		for (Map.Entry<String, Integer> cursor : getCost().entrySet()) {
			hasEnough &= menu.hasEnoughOf(cursor.getKey(), cursor.getValue());
		}
		return hasEnough;
	}

	public void subtract(ResourcesMenu menu) {
		for (Map.Entry<String, Integer> cursor : getCost().entrySet()) {
			menu.subtract(cursor.getKey(), cursor.getValue());
		}
	}

	public Map<String, Integer> getCost() {
		return cost;
	}

	public void setCost(Map<String, Integer> cost) {
		this.cost = cost;
	}

}
