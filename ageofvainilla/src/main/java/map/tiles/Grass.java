package map.tiles;

import com.uqbar.vainilla.appearances.Sprite;

import config.Configuration;

public class Grass extends WalkableTile {

	@Override
	public Sprite getSprite() {
		return Configuration.getSprite("grass");
	}

}
