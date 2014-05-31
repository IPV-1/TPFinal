package components.units.panels;


import com.uqbar.vainilla.appearances.Appearance;
import components.menus.DownMenu;

import java.util.List;

public class ControlPanel extends DownMenu {

	public ControlPanel(Appearance appearance, double x, double y) {
		super(appearance, x, y);
	}

	public void addButtons(List<BuildingButton> buttons) {
		int nextX = ((int) getX()) + 10; // why 10? I don't know.
		int nextY = (int) (getY()) + 10;
		for (BuildingButton button : buttons) {
			button.setX(nextX);
			button.setY(nextY);
			button.setZ(getZ() + 1);
			nextX += button.getWidth() + 10;
			getComponents().add(button);
		}
	}
}
