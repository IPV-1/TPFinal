package components.units.panels.factories;


import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import components.units.buildings.Builder;
import components.units.panels.BuildingButton;
import components.units.panels.ControlPanel;
import components.units.panels.UnitShower;
import components.units.panels.factories.PanelBuilder;
import components.units.panels.factories.UnitShowerFactory;
import map.tiles.Tile;

import java.awt.*;

public class PanelFactory {

	private PanelBuilder builder;

	public PanelFactory(PanelBuilder builder) {
		this.builder = builder;
	}

	public ControlPanel downPanelFullWith(Game game) {
		// TODO: this method is painful, so much.
		// Isn't  worse colours?
		int height = Tile.HEIGHT * 3;
		Appearance appearance = new Rectangle(Color.orange, game.getDisplayWidth(), height);
		Appearance buildingAppearance = new Rectangle(Color.RED, Tile.WIDTH, Tile.WIDTH);
		Builder buildingBuilder = new Builder(buildingAppearance, Tile.WIDTH, Tile.WIDTH);
		Appearance buttonAp = new Circle(Color.red, 40);
		for (int i = 0; i < 2; i++) {
			getBuilder().withButton(new BuildingButton(buildingBuilder, buttonAp, 0, 1));
		}
		UnitShower unitShower = UnitShowerFactory.createRectangle(game, 200, game.getDisplayHeight() - height, game.getDisplayWidth() - 200, game.getDisplayHeight() - height);
		ControlPanel panel = getBuilder().withAppearance(appearance).withZ(8).withShower(unitShower).build(0, game.getDisplayHeight() - height);
		unitShower.setPanel(panel);
		return panel;
	}

	public PanelBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(PanelBuilder builder) {
		this.builder = builder;
	}
}
