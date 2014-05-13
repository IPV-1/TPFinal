package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import resource.Resource;
import utility.Commons;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;

public class Map extends GameComponent<GameScene> {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	protected Tile[][] tiles = new Tile[(HEIGHT / Tile.WIDTH) + 1][(WIDTH / Tile.WIDTH) + 1];

	public Map() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = Tile.GRASS;
			}
		}
	}

	public Map(String file) {
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
			tiles[y][x] = (Tile) Commons.invokeMethodFromClass(Tile.class,
					"get" + scanner.next());
		}
		scanner.close();
	}

	@Override
	public void render(Graphics2D graphics) {
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				tiles[y][x].SPRITE.renderAt((int) x * Tile.WIDTH, (int) y * Tile.WIDTH,
						graphics);
			}
		}
	}

}
