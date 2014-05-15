package map.tiles;


public class BlockedTile extends Tile {

	@Override
	public boolean isWalkable() {
		return false;
	}

}
