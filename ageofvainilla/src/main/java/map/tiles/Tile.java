package map.tiles;

import java.util.HashMap;
import java.util.Map;

import com.uqbar.vainilla.appearances.Sprite;

import config.Configuration;

public abstract class Tile {
	
	public static final int WIDTH = Configuration.getValue("tileWidth").intValue();
	public static final int HEIGHT = Configuration.getValue("tileHeight").intValue();
	
	private Sprite sprite;
	
	public static Tile getTile(String key) {
		return TILES.get(key);
	}
	
	public abstract boolean isWalkable();
	
	@SuppressWarnings("serial")
	private static Map<String, Tile> TILES = new HashMap<String, Tile>(){{
        put("G", new Grass());
        put("P", new Plain());
        put("R", new Rock());
        put("W", new Water());
        put("H", new House());
    }};

	public boolean isEmpty() {
		return true;
	}
	
	public boolean isBlocked() {
		return !isWalkable();
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
}
