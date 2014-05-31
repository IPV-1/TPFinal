package components.units.panels;
import com.uqbar.vainilla.appearances.*;
import components.menus.DownMenu;

import java.awt.*;
import java.util.List;

public class ControlPanel extends DownMenu {
	//TODO: Make this parametric.
	private UnitShower unitShower;

	public ControlPanel(Appearance appearance, double x, double y) {
		super(appearance, x, y);
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
		getUnitShower().render(graphics);
	}

	public void addButtons(List<BuildingButton> buttons) {
		int nextX = ((int) getX()); // why 10? I don't know.
		int nextY = (int) (getY());
		for (BuildingButton button : buttons) {
			button.setX(nextX);
			button.setY(nextY);
			button.setZ(getZ() + 1);
			nextX += button.getWidth();
			getComponents().add(button);
		}
	}

	public UnitShower getUnitShower() {
		return unitShower;
	}

	public void setUnitShower(UnitShower unitShower) {
		this.unitShower = unitShower;
	}
}
