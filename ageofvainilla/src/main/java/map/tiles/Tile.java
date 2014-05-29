package map.tiles;

import java.util.HashMap;
import java.util.Map;

import com.uqbar.vainilla.appearances.Sprite;
import components.units.Unit;

import config.Configuration;

public abstract class Tile {
	
	public static final int WIDTH = Configuration.getValue("tileWidth").intValue();
	public static final int HEIGHT = Configuration.getValue("tileHeight").intValue();
	
	private Sprite sprite;
	private Unit ocuppant = null;
	
	public static Tile getTile(String key) {
		return TILES.get(key).build();
	}
	
	public boolean isEmpty() {
		return ! this.isOccuppied();
	}
	
	@SuppressWarnings("serial")
	private static Map<String, TileBuilder> TILES = new HashMap<String, TileBuilder>(){{
        put("G", new TileBuilder().forTile(Grass.class));
        put("P", new TileBuilder().forTile(Plain.class));
        put("M", new TileBuilder().forTile(Mountain.class));
        put("W", new TileBuilder().forTile(Water.class));
        put("H", new TileBuilder().forTile(House.class));
    }};
	
	public boolean isBlocked() {
		return !isEmpty();
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public boolean isOccuppied() {
		return this.getOcuppant() != null;
	}

	public Unit getOcuppant() {
		return ocuppant;
	}

	public void setOcuppant(Unit ocuppant) {
		this.ocuppant = ocuppant;
	}

	public void setFree() {
		this.setOcuppant(null);
	}
	
}
