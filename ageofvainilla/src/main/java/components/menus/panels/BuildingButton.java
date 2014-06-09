package components.menus.panels;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;

import components.MouseHandler;
import components.buttons.BasicAgeButton;
import components.units.buildings.BasicBuilding;
import components.units.buildings.Builder;

/**
 * TODO: this class should be "clickable" by mouse handler, but that interface doesn't exist.
 */
public class BuildingButton extends BasicAgeButton {

	private boolean selected;
	private Builder builder;

	public BuildingButton(Builder builder, Appearance appearance, double x, double y) {
		super(appearance, x, y);
		setBuilder(builder);
	}

	public void onSceneActivated(ControlPanel panel) {
		setScene(panel.getScene());
	}

	public void update(DeltaState deltaState) {
		/**
		 * Something like that?
		 */
//		if (deltaState.isMouseButtonReleased(MouseButton.RIGHT) && getScene().getMouse().isSelected(this)) {
//			this.interact(getScene().getMouse().getElementUnderMouse());
//		}
	}

	public void buildIn(int posX, int posY) {
		BasicBuilding building = getBuilder().build(posX, posY);
		if (getScene().getResourcesMenu().canBuild(building)) {
			getScene().addComponent(building);
			getScene().getResourcesMenu().updateResources(building);
		} else {
			// Do what?
		}
	}
	
	@Override
	public void clickedBy(MouseHandler mouse) {
		// Set builder building selected
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public boolean isSelected() {
		/**
		 * Should be:
		 * return getScene().getMouse().isSelected(this);
		 */
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
