package components.buttons;

import com.uqbar.vainilla.appearances.Appearance;

import components.BasicAgeComponent;
import components.MouseHandler;

public abstract class BasicAgeButton extends BasicAgeComponent {

	public BasicAgeButton() {
		super();
	}

	public BasicAgeButton(Appearance appearance, double x, double y) {
		super(appearance, x, y);
	}

	public BasicAgeButton(double x, double y) {
		super(x, y);
	}

	public abstract void clickedBy(MouseHandler mouse);
	
}
