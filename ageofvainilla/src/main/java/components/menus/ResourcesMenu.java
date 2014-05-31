package components.menus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Rectangle;
import components.BasicAgeComponent;
import components.boards.ResourceBoard;
import components.boards.ResourceBoardFactory;

import components.units.buildings.BasicBuilding;
import config.Configuration;

public class ResourcesMenu extends BasicAgeComponent {

	private List<ResourceBoard> components = new ArrayList<ResourceBoard>();

	public ResourcesMenu() {
		super(0, 0);
		this.setAppearance(new Rectangle(Color.BLACK, Configuration.getDisplayWidth(), 30));

		ResourceBoard gold = new ResourceBoardFactory().withX(15).withLabel("GOLD").build();
		components.add(gold);

		ResourceBoard lumber = new ResourceBoardFactory().withX(155).withLabel("LUMBER").build();
		components.add(lumber);

		ResourceBoard food = new ResourceBoardFactory().withX(355).withLabel("FOOD").build();
		components.add(food);

		ResourceBoard rock = new ResourceBoardFactory().withX(555).withLabel("ROCK").build();
		components.add(rock);

	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);

		for (BasicAgeComponent component : components) {
			component.update(deltaState);
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);

		for (BasicAgeComponent component : components) {
			component.render(graphics);
		}
	}

	public boolean canBuild(BasicBuilding building) {
		return building.enough(this);
	}

	public void updateResources(BasicBuilding building) {
		building.subtract(this);
	}


	/**
	 * These methods are not my best code, but i didn't want to modify
	 * the current implementation of this class.
	 */
	public boolean hasEnoughOf(String key, Integer value) {
		for (ResourceBoard component : components) {
			if (component.getTitle().equals(key) && component.getValue() > value) {
				return true;
			}
		}
		return false;
	}

	public void subtract(String key, Integer value) {
		for (ResourceBoard component : components) {
			if (component.getTitle().equals(key)) {
				component.setValue(component.getValue() - value);
			}
		}
	}
}
