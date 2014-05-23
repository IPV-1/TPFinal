package components;

import com.uqbar.vainilla.appearances.Appearance;

public class CameraRelativeComponent extends BasicAgeComponent {

	public CameraRelativeComponent() {
		super();
	}

	public CameraRelativeComponent(Appearance appearance, double x, double y) {
		super(appearance, x, y);
	}

	public CameraRelativeComponent(double x, double y) {
		super(x, y);
	}
	
}
