package map.tiles;

import com.uqbar.vainilla.appearances.Sprite;

import config.Configuration;

public class House extends BlockedTile {

	@Override
	public Sprite getSprite() {
		return Configuration.getSprite("house");
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}

}
