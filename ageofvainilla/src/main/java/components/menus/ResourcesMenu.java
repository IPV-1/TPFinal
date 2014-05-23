package components.menus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Rectangle;
import components.BasicAgeComponent;
import components.boards.ResourceBoard;

import config.Configuration;

public class ResourcesMenu extends BasicAgeComponent {
	
	private List<BasicAgeComponent> components = new ArrayList<BasicAgeComponent>();

	public ResourcesMenu() {
		super(0, 0);
		this.setAppearance(new Rectangle(Color.BLACK, Configuration.getDisplayWidth(), 30));
		
		ResourceBoard gold = new ResourceBoard(15, 1, Color.WHITE, "GOLD", 100);
		components.add(gold);
		
		ResourceBoard lumber = new ResourceBoard(155, 1, Color.WHITE, "LUMBER", 100);
		components.add(lumber);
		
		ResourceBoard food = new ResourceBoard(355, 1, Color.WHITE, "FOOD", 100);
		components.add(food);
		
		ResourceBoard rock = new ResourceBoard(555, 1, Color.WHITE, "ROCK", 100);
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
}
