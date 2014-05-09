package components.units;

import java.awt.Color;
import java.awt.geom.Point2D;

import scenes.FieldScene;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.MovingGameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.Coord;
import com.uqbar.vainilla.space.UnitVector2D;

public class Unit extends MovingGameComponent<FieldScene> {

	public static final int SPEED = 200;
	private boolean moving = false;
	private Coord moveTo = new Coord(0, 0);
	private Circle breakMove;

	public Unit(Color color, double xPos, double yPos) {
		super(new Rectangle(color, 10, 12), xPos, yPos, new UnitVector2D(1, 1), 0);
	}

	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isMouseButtonReleased(MouseButton.RIGHT)) {
			Point2D.Double d = deltaState.getCurrentMousePosition();
			double x = d.x + Camera.INSTANCE.getX();
			double y = d.y + Camera.INSTANCE.getY();
			this.setMoveTo(x, y);
			this.updateBreakMove();
			this.setSpeed(SPEED);
			moving = true;
		}

		if(moving) {
			this.checkBreak();
		}
		super.update(deltaState);
	}

	private void updateBreakMove() {
		breakMove = new Circle(this.getMoveTo().getX() + 2, this.getMoveTo().getY() + 2, 4);
	}

	public void checkBreak() {
		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(this.breakMove, this.getRect())) {
			moving = false;
			this.setSpeed(0);
		}
	}

	public Coord getMoveTo() {
		return moveTo;
	}

	public void setMoveTo(double x, double y) {
		this.getMoveTo().set(x, y);
		this.getDirection().set(x - this.getX(), y - this.getY());
	}

}