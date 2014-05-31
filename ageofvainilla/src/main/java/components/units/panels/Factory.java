package components.units.panels;


import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;
import map.tiles.Tile;

import java.awt.*;

public class Factory {

	private Builder builder;

	public Factory(Builder builder) {
		this.builder = builder;
	}

	public ControlPanel downPanelFullWith(Game game) {
		int height = Tile.HEIGHT * 3;
		Appearance appearance = new Rectangle(Color.orange, game.getDisplayWidth(), height);
		return getBuilder().withAppearance(appearance).withZ(8).build(0, game.getDisplayHeight() - height);
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}
}
