package components.units.panels;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.events.constants.MouseButton;
import components.BasicAgeComponent;
import components.units.Unit;
import components.units.buildings.Builder;

/**
 * TODO: this class should be "clickable" by mouse handler, but that interface doesn't exist.
 */
public class BuildingButton extends BasicAgeComponent{

	private boolean selected;
	private Builder builder;

	public BuildingButton(Builder builder, Appearance appearance, double x, double y) {
		super(appearance, x, y);
		setBuilder(builder);
	}

	public void onSceneActivated(ControlPanel panel){
		setScene(panel.getScene());
	}

	public void update(DeltaState deltaState){
//		if (deltaState.isMouseButtonReleased(MouseButton.RIGHT) && getScene().getMouse().isSelected(this)) {
//			this.interact(getScene().getMouse().getElementUnderMouse());
//		}
	}

	//Helper method for appearance
	public void select(){
		setSelected(true);
	}

	public void unselect(){
		setSelected(false);
	}

	public void buildIn(int posX, int posY){
		getScene().addComponent(getBuilder().build(posX, posY));
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
