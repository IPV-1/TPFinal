package components.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.appearances.Rectangle;

import components.BasicAgeComponent;
import config.Configuration;

public class ResourcesMenu extends BasicAgeComponent {
	
	private List<BasicAgeComponent> components = new ArrayList<BasicAgeComponent>();

	public ResourcesMenu() {
		super(0, 0);
		this.setAppearance(new Rectangle(Color.BLACK, Configuration.getDisplayWidth(), 30));
		
		Label text = new Label(new Font("SansSerif", Font.PLAIN, 25), Color.WHITE, "GOLD: 100");
		BasicAgeComponent oro = new BasicAgeComponent(text, 1, 1);
		components.add(oro);
		
		Label textLumber = new Label(new Font("SansSerif", Font.PLAIN, 25), Color.WHITE, "LUMBER: 100");
		BasicAgeComponent lumber = new BasicAgeComponent(textLumber, 150, 1);
		components.add(lumber);
		
		Label textRock = new Label(new Font("SansSerif", Font.PLAIN, 25), Color.WHITE, "ROCK: 100");
		BasicAgeComponent rock = new BasicAgeComponent(textRock, 350, 1);
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
