package components.menus.panels;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.appearances.Appearance;
import components.MouseHandler;
import components.buttons.BasicAgeButton;
import components.buttons.BuildingButton;
import components.menus.DownMenu;
import components.units.Flag;
import components.units.Unit;

public class ControlPanel extends DownMenu {
	
	public List<BuildingButton> buttons;
	private UnitShower unitShower;
	
	private final Flag EMPTY = new Flag();
	private Unit selected = EMPTY;

	public ControlPanel(Appearance appearance, double x, double y) {
		super(appearance, x, y);
		setButtons(new ArrayList<BuildingButton>());
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);

		getUnitShower().render(graphics, this.getSelectedUnit());
		
		for (BuildingButton button : getButtons()) {
			button.render(graphics);
		}
		
		if(this.selectedUnitsChange()) {
			removeButtons();
			selected = getSelectedUnit();
			
			addButtons(getUnitButtons());
		}
		
	}
	
	private boolean selectedUnitsChange() {
		return getScene() != null && ! selected.equals(this.getSelectedUnit());
	}

	private void addButtons(List<BuildingButton> buttons) {
		int nextX = (int) getX();
		int nextY = (int) getY();
		
		for (BuildingButton button : buttons) {
			button.setX(nextX);
			button.setY(nextY);
			button.setZ(getZ() + 1);
			nextX += button.getWidth();
		}
		
		this.setButtons(buttons);
	}
	
	private void removeButtons() {
		for (BuildingButton button : getButtons()) {
			button.destroy();
		}
	}

	public UnitShower getUnitShower() {
		return unitShower;
	}

	public void setUnitShower(UnitShower unitShower) {
		this.unitShower = unitShower;
	}

	public List<BuildingButton> getUnitButtons() {
		return getSelectedUnit().getButtons();
	}
	
	public List<BuildingButton> getButtons() {
		return buttons; //new ArrayList<BuildingButton>();
	}

	public void setButtons(List<BuildingButton> buttons) {
		this.buttons = buttons;
	}

	public void clickedBy(MouseHandler mouseHandler) {
		for (BasicAgeButton button : getButtons()) {
			
			if(mouseHandler.isClicking(button)) {
				button.clickedBy(mouseHandler);
			}
		}
	}

	public Unit getSelectedUnit() {
		if(getScene() != null && getScene().getMouse().getSelected().size() == 1) {
			return getScene().getMouse().getSelected().get(0);
		}
		
		return EMPTY;
	}

}
