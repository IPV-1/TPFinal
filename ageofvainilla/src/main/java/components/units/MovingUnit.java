package components.units;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.Coord;
import com.uqbar.vainilla.space.UnitVector2D;

public class MovingUnit extends Unit {

	public static final int SPEED = 200;
	private boolean moving = false;
	private Coord moveTo;
	private Circle breakMove;

	public MovingUnit(Color color, double xPos, double yPos) {
		super(new Rectangle(color, 10, 12), xPos, yPos, new UnitVector2D(1, 1), SPEED);
	}

	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isMouseButtonReleased(MouseButton.RIGHT)) {
			this.setMoveTo(new Coord(deltaState.getCurrentMousePosition()));
			this.updateBreakMove();
			moving = true;
		}

		if(moving) {
			super.update(deltaState);
			this.checkBreak();
		}
	}

	private void updateBreakMove() {
		breakMove = new Circle(this.getMoveTo().getX(), this.getMoveTo().getY(), this.getWidth());
	}

	public void checkBreak() {
		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(this.breakMove, this.getRect())) {
			moving = false;
		}
	}

	public Coord getMoveTo() {
		return moveTo;
	}

	public void setMoveTo(Coord moveTo) {
		this.moveTo = moveTo;
		this.setDirection(this.getCoord().getDirectionTo(moveTo));
	}
}
