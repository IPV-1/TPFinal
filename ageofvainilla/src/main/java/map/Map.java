package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import map.tiles.Tile;

import resource.Resource;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.space.Coord;

public class Map extends GameComponent<GameScene> {

	protected final int xTiles;
	protected final int yTiles;
	protected Tile[][] tiles;

	public Map(int width, int height, String file) {
		this.xTiles = width;
		this.yTiles = height;
		tiles = new Tile[yTiles][xTiles];
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

//	protected void process(String line, int y) {
//		Scanner scanner = new Scanner(line);
//		for (int x = 0; scanner.hasNext(); x++) {
//			this.set(
//					(Tile) Commons.invokeMethodFromClass(Tile.class, "get"
//							+ scanner.next()), x, y);
//		}
//		scanner.close();
//	}
	
	protected void process(String line, int y) {
		Scanner scanner = new Scanner(line);
		for (int x = 0; scanner.hasNext(); x++) {
			this.set(Tile.getTile(scanner.next()), x, y);
		}
		scanner.close();
	}

	@Override
	public void render(Graphics2D graphics) {
		for (int y = 0; y < this.getTiles().length; y++) {
			for (int x = 0; x < this.getTiles()[0].length; x++) {
				this.get(x, y).getSprite().renderAt(x * Tile.WIDTH, y * Tile.HEIGHT,
						graphics);
			}
		}
	}

	public boolean isBlocked(int x, int y) {
		return this.get(x, y).isBlocked();
	}

	public boolean isWalkable(int x, int y) {
		return this.get(x, y).isWalkable();
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

}
