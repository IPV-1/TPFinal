package map.tiles;

import com.uqbar.vainilla.appearances.Sprite;

import config.Configuration;

public class Rock extends BlockedTile {

	@Override
	public Sprite getSprite() {
		return Configuration.getSprite("rock");
	}

}
