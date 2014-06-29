package map.parser;

import java.util.List;

import map.tiles.Tile;

public interface MapParser {

	public List<List<Tile>> parse();
	
}
