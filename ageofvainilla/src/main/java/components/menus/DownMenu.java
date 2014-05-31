package components.menus;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import components.BasicAgeComponent;

public class DownMenu extends BasicAgeComponent {
	
	private List<BasicAgeComponent> components = new ArrayList<BasicAgeComponent>();

	public DownMenu(Appearance appearance, int y) {
		super(appearance, 0, y);
	}

	public DownMenu(Appearance appearance, double x, double y) {
		super(appearance,x,y);
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

	public List<BasicAgeComponent> getComponents() {
		return components;
	}

	public void setComponents(List<BasicAgeComponent> components) {
		this.components = components;
	}
}
