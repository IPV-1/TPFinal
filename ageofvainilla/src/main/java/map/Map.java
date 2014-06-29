package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import map.parser.FileParser;
import map.tiles.Tile;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.space.Coord;
import components.units.Unit;
import components.units.buildings.BasicBuilding;

public class Map extends GameComponent<GameScene> {

	private List<List<Tile>> tiles;
	
	private Sprite field;

	public Map() {
		this.setTiles(FileParser.parse());
		this.setField(this.buildMapSprite());
	}

	@Override
	public void render(Graphics2D graphics) {
		this.getField().renderAt((int)-Camera.INSTANCE.getX(), (int)-Camera.INSTANCE.getY(), graphics);
	}
	
	private Sprite buildMapSprite() {
		int mapWidth = this.getTileWidth() * Tile.WIDTH;
		int mapHeight = this.getTileHeight() * Tile.HEIGHT;
		
		BufferedImage newImage = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics = newImage.createGraphics();
		
		for (int y = 0; y < this.getTileHeight(); y++) {
			for (int x = 0; x < this.getTileWidth(); x++) {
				
				Sprite sprite = this.get(x, y).getSprite();
				int mapX = (int)((x * Tile.WIDTH));
				int mapY = (int)((y * Tile.HEIGHT));
				graphics.drawImage(sprite.scaleTo(Tile.WIDTH, Tile.HEIGHT).getImage(), null, mapX, mapY);
			}
		}
		
		graphics.dispose();

		return new Sprite(newImage);
	}

	public boolean isBlocked(int x, int y) {
		return this.get(x, y).isBlocked();
	}

	public boolean isWalkable(int x, int y) {
		return this.get(x, y).isEmpty();
	}
	
	public Tile get(int x, int y) {
		return this.getTiles().get(y).get(x);
	}

	public List<List<Tile>> getTiles() {
		return this.tiles;
	}

	public int getTileWidth() {
		return this.getTiles().get(0).size();
	}

	public int getTileHeight() {
		return this.getTiles().size();
	}

	public Tile get(Coord tile) {
		return this.get((int) tile.getX(), (int) tile.getY());
	}
	
	public void occupy(Unit unit, int tileX, int tileY) {
		this.get(tileX, tileY).setOcuppant(unit);
	}

	public void setFree(int tileX, int tileY) {
		this.get(tileX, tileY).setFree();
	}

	public Tile getTileCamera(double x, double y) {
		return this.get(Coord.getTileCamera(x, y, Tile.WIDTH));
	}

	public Sprite getField() {
		return field;
	}

	public void setField(Sprite field) {
		this.field = field;
	}

	public boolean canBuild(BasicBuilding building) {
		for(double i=building.getTileX(); i < building.getTileX() + building.getWidthInTiles(); i++) {
			for(double j=building.getTileY(); j < building.getTileY() + building.getHeightInTiles(); j++) {
				if(this.isBlocked((int) i, (int) j)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void freeBuilding(BasicBuilding building) {
		for(double i=building.getTileX(); i < building.getTileX() + building.getWidthInTiles(); i++) {
			for(double j=building.getTileY(); j < building.getTileY() + building.getHeightInTiles(); j++) {
				this.setFree((int)i, (int)j);
			}
		}
	}

	public void occupyBuilding(BasicBuilding building) {
		for(double i=building.getTileX(); i < building.getTileX() + building.getWidthInTiles(); i++) {
			for(double j=building.getTileY(); j < building.getTileY() + building.getHeightInTiles(); j++) {
				this.occupy(building, (int)i, (int)j);
			}
		}
		
	}

	protected void setTiles(List<List<Tile>> tiles) {
		this.tiles = tiles;
	}
	
}
