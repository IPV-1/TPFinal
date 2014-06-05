package components.menus.panels;

import com.uqbar.vainilla.appearances.*;
import components.menus.DownMenu;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends DownMenu {
	//TODO: Make this parametric.
	public List<BuildingButton> buttons;
	private UnitShower unitShower;

	public ControlPanel(Appearance appearance, double x, double y) {
		super(appearance, x, y);
		setButtons(new ArrayList<BuildingButton>());
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
		getUnitShower().render(graphics);
	}

	@Override
	public void onSceneActivated() {
		super.onSceneActivated();
		for (BuildingButton b : getButtons()){
			b.onSceneActivated(this);
		}

	}


	public void addButton(BuildingButton button) {
		getComponents().add(button);
		getButtons().add(button);
	}

	public void addButtons(List<BuildingButton> buttons) {
		int nextX = ((int) getX()); // why 10? I don't know.
		int nextY = (int) (getY());
		for (BuildingButton button : buttons) {
			button.setX(nextX);
			button.setY(nextY);
			button.setZ(getZ() + 1);
			nextX += button.getWidth();
			addButton(button);
		}
	}

	public UnitShower getUnitShower() {
		return unitShower;
	}

	public void setUnitShower(UnitShower unitShower) {
		this.unitShower = unitShower;
	}

	public List<BuildingButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<BuildingButton> buttons) {
		this.buttons = buttons;
	}
}
