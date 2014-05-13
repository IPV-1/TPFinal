package components.units;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.Circle;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.Coord;
import com.uqbar.vainilla.space.UnitVector2D;
import components.MouseHandler;
import components.units.states.Atacking;
import components.units.states.Moving;
import components.units.states.UnitState;
import components.units.states.Waiting;

public class MovingUnit extends Unit {

	public static final int SPEED = 200;
	private Coord moveTo;
	private Circle breakMove;
	private UnitState state = new Waiting();

	public MovingUnit(Color color, double xPos, double yPos) {
		super(new Rectangle(color, 10, 12), xPos, yPos, new UnitVector2D(1, 1), SPEED);
	}

	@Override
	public void update(DeltaState deltaState) {

		getState().update(this, deltaState);

		if (deltaState.isMouseButtonReleased(MouseButton.RIGHT)) {
			this.interact(getMouse().getElementUnderMouse());
		}
		
		/* Mover la camara con la' flechitas :D */
//		double x = d.x + Camera.INSTANCE.getX();
//		double y = d.y + Camera.INSTANCE.getY();
	}

	public void interact(Unit unit) {
		this.getState().interact(this, unit);
	}

	private MouseHandler getMouse() {
		return this.getScene().getMouse();
	}

	public void updateBreakMove() {
		setBreakMove(new Circle(this.getMoveTo().getX(), this.getMoveTo().getY(), this.getWidth()));
	}

	public Coord getMoveTo() {
		return moveTo;
	}

	public void setMoveTo(Unit unit) {
		this.moveTo = unit.getCoord();
		this.setDirection(this.getCoord().getDirectionTo(moveTo));
		this.updateBreakMove();
		this.setState(new Moving(new Atacking(unit)));
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Circle getBreakMove() {
		return breakMove;
	}

	public void setBreakMove(Circle breakMove) {
		this.breakMove = breakMove;
	}
}
