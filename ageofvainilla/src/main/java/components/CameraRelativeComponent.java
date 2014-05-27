package components;

import java.awt.Graphics2D;

import com.uqbar.vainilla.Camera;
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
	
	@Override
	public void render(Graphics2D graphics) {
		if(this.onScreen()) {
			double x = this.getX();
			double y = this.getY();
			this.setX(x - Camera.INSTANCE.getX());
			this.setY(y - Camera.INSTANCE.getY());
			super.render(graphics);
			this.setX(x);
			this.setY(y);
		}
	}
	
	public boolean onScreen() {
		double cx = Camera.INSTANCE.getX();
		double cy = Camera.INSTANCE.getY();
		return this.getY() + this.getHeight() >= cy
				&& this.getX() + this.getWidth() >= cx
					//TODO use Configuration file
				&& this.getY() <= cy + 600//this.getGame().getDisplayHeight()
				&& this.getX() <= cx + 800;//this.getGame().getDisplayWidth();
	}
	
}
