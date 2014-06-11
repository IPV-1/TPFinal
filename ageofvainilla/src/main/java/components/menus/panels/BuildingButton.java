package components.menus.panels;

import com.uqbar.vainilla.appearances.Appearance;
import components.MouseHandler;
import components.buttons.BasicAgeButton;
import components.units.buildings.Builder;

public class BuildingButton extends BasicAgeButton {

	private Builder builder;

	public BuildingButton(Builder builder, Appearance appearance, double x, double y) {
		super(appearance, x, y);
		setBuilder(builder);
	}

	public void onSceneActivated(ControlPanel panel) {
		setScene(panel.getScene());
	}

	@Override
	public void clickedBy(MouseHandler mouse) {
		// Set builder building selected
		this.getBuilder().seleccionate(mouse);
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

}
