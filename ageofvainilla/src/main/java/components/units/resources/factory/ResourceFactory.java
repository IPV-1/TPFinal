package components.units.resources.factory;


import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Circle;
import components.units.resources.Resource;
import map.tiles.Tile;

import java.awt.*;

public class ResourceFactory {


	public static Resource createGold(int tileX, int tileY, int amount) {
		Appearance appearance = new Circle(Color.YELLOW, Tile.WIDTH);
		return new Resource(appearance, tileX, tileY, amount, "GOLD");
	}

}
