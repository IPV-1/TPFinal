package map.tiles;

import com.uqbar.vainilla.appearances.Sprite;

import config.Configuration;

public class Water extends BlockedTile {

	@Override
	public Sprite getSprite() {
		return Configuration.getSprite("water");
	}

}
