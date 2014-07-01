package components.buttons;

import com.uqbar.vainilla.appearances.Appearance;
import components.MouseHandler;
import components.units.buildings.Builder;

import config.Configuration;

public class BuildingButton extends BasicAgeButton {

	private Builder builder;

	public BuildingButton(Builder builder, Appearance appearance, double x, double y) {
		super(Configuration.getScaledDownButton(appearance), x, y);
		setBuilder(builder);
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
