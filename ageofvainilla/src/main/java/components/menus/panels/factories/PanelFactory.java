package components.menus.panels.factories;


import java.awt.Color;

import map.tiles.Tile;

import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import components.menus.panels.BuildingButton;
import components.menus.panels.ControlPanel;
import components.menus.panels.UnitShower;
import components.units.buildings.Builder;
import components.units.buildings.House;

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
		
		Builder buildingBuilder = new Builder(1, 1);
		Appearance buttonAp = new Circle(Color.red, 40);
		getBuilder().withButton(new BuildingButton(buildingBuilder, buttonAp, 0, 1));
		
		Builder buildingBuilder2 = new Builder(2, 2);
		Appearance buttonAp2 = new Circle(Color.blue, 40);
		getBuilder().withButton(new BuildingButton(buildingBuilder2, buttonAp2, 0, 1));
		
		Builder buildingBuilder3 = new House();
		Appearance buttonAp3 = new Circle(Color.GREEN, 40);
		getBuilder().withButton(new BuildingButton(buildingBuilder3, buttonAp3, 0, 1));
		
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