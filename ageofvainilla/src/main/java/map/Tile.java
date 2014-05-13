package map;

import resource.Resource;

import com.uqbar.vainilla.appearances.Sprite;

public class Tile {

	public final Sprite SPRITE;
	public final String RESOURCE;
	
	public static final Tile GRASS = new Tile("grass.png");
	public static final Tile PLAIN = new Tile("plain.png");
	public static final Tile ROCKS = new Tile("rock.png");
	public static final Tile WATER = new Tile("water.png");
	
	public static final Tile HOUSE = new Tile("rock.png");
	
	public static final int WIDTH = (int) Tile.GRASS.SPRITE.getWidth();
	
	protected Tile(String sprite) {
		this.SPRITE = Resource.fromImage("img/map/tiles/" + sprite);
		this.RESOURCE = sprite;
	}
	
	public static Tile getG()	{	return Tile.GRASS;	}
	public static Tile getP()	{	return Tile.PLAIN;	}
	public static Tile getR()	{	return Tile.ROCKS;	}
	public static Tile getW()	{	return Tile.WATER;	}
	public static Tile getH()	{	return Tile.HOUSE;	}

	public boolean isEmpty() {
		return ! RESOURCE.equals("rock.png");
	}
	
}
