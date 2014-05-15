package map.tiles;

import com.uqbar.vainilla.appearances.Sprite;

import config.Configuration;

public class Plain extends WalkableTile {

	@Override
	public Sprite getSprite() {
		return Configuration.getSprite("plain");
	}

}
