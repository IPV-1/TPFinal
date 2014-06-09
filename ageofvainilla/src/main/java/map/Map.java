package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import map.tiles.Tile;
import resource.Resource;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.space.Coord;
import components.units.Unit;

public class Map extends GameComponent<GameScene> {

	protected final int xTiles;
	protected final int yTiles;
	protected Tile[][] tiles;
	
	private Sprite field;

	public Map(int width, int height, String file) {
		this.xTiles = width;
		this.yTiles = height;
		tiles = new Tile[yTiles][xTiles];
		
		this.parseMap(file);
		
		this.setField(this.buildMapSprite());
	}

	private void parseMap(String file) {
		BufferedReader br = null;
		try {
			String line;
			InputStream input = Resource.class.getResourceAsStream(file);
			br = new BufferedReader(new InputStreamReader(input));
			for (int y = 0; (line = br.readLine()) != null; y++) {
				this.process(line, y);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	protected void process(String line, int y) {
		Scanner scanner = new Scanner(line);
		for (int x = 0; scanner.hasNext(); x++) {
			this.set(Tile.getTile(scanner.next()), x, y);
		}
		scanner.close();
	}

	@Override
	public void render(Graphics2D graphics) {
		this.getField().renderAt((int)-Camera.INSTANCE.getX(), (int)-Camera.INSTANCE.getY(), graphics);
	}
	
	private Sprite buildMapSprite() {
		int mapWidth = this.getTiles()[0].length * Tile.WIDTH;
		int mapHeight = this.getTiles().length * Tile.HEIGHT;
		
		BufferedImage newImage = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics = newImage.createGraphics();
		
		for (int y = 0; y < this.getTiles().length; y++) {
			for (int x = 0; x < this.getTiles()[0].length; x++) {
				
				Sprite sprite = this.get(x, y).getSprite();
				int mapX = (int)((x * Tile.WIDTH));
				int mapY = (int)((y * Tile.HEIGHT));
				graphics.drawImage(sprite.getImage(), null, mapX, mapY);
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
		return this.getTiles()[y][x];
	}

	public void set(Tile tile, int x, int y) {
		this.getTiles()[y][x] = tile;
	}

	public Tile[][] getTiles() {
		return this.tiles;
	}

	public int getxTiles() {
		return xTiles;
	}

	public int getyTiles() {
		return yTiles;
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
	
}
