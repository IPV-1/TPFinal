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
		return TILES.get(key).build();
	}
	
	public abstract boolean isWalkable();
	
	@SuppressWarnings("serial")
	private static Map<String, TileBuilder> TILES = new HashMap<String, TileBuilder>(){{
        put("G", new TileBuilder().forTile(Grass.class));
        put("P", new TileBuilder().forTile(Plain.class));
        put("M", new TileBuilder().forTile(Mountain.class));
        put("W", new TileBuilder().forTile(Water.class));
        put("H", new TileBuilder().forTile(House.class));
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
