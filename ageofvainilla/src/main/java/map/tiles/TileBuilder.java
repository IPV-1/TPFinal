package map.tiles;


public class TileBuilder {
	
	Class<? extends Tile> tileClass;
	
	public TileBuilder forTile(Class<? extends Tile> tile) {
		this.tileClass = tile;
		return this;
	}
	
	public Tile build() {
		try {
			return (Tile) tileClass.getConstructors()[0].newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Grass();
	}
		
}
