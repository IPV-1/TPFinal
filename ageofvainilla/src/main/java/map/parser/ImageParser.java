package map.parser;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import map.tiles.Tile;
import resource.Resource;
import utils.ColorCSS;
import config.Configuration;

public class ImageParser implements MapParser {
	
	private List<List<Tile>> resultado = new ArrayList<List<Tile>>();
	
	@SuppressWarnings("serial")
	private Map<Integer, String> COLOR_HASH = new HashMap<Integer, String>() {{
        put(ColorCSS.GREEN.getRGB(),  "G");
        put(ColorCSS.YELLOW.getRGB(), "P");
        put(ColorCSS.BROWN.getRGB(),  "M");
        put(ColorCSS.BLUE.getRGB(),   "W");
    }};

	public List<List<Tile>> parse() {
		
		String file = Configuration.getString("map_img");
		
		InputStream input = null;
		try {
			input = Resource.class.getResourceAsStream(file);
			BufferedImage image = ImageIO.read(input);
			for (int y = 0; y < image.getHeight(); y++) {
				createRow(y, image.getWidth(), image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	private void createRow(int y, int width, BufferedImage image) {
		for(int x = 0; x < width; x++) {
			int pixel = image.getRGB(x, y);
			
			String pixelString = "G"; 
			
			if(COLOR_HASH.containsKey(pixel)) {
				pixelString = COLOR_HASH.get(pixel);
			}
			
			set(Tile.getTile(pixelString), x, y);
		}
	}
	
	private void set(Tile tile, int x, int y) {
		if(resultado.size() == y) {
			resultado.add(new ArrayList<Tile>());
		}
		
		resultado.get(y).add(tile);
	}

}
