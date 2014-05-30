package components.units;

import java.awt.Color;

import map.path.Path;
import scenes.FieldScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.UnitVector2D;
import components.MouseHandler;
import components.MovingGameComponent;
import components.units.states.Atacking;
import components.units.states.Moving;
import components.units.states.UnitState;
import components.units.states.Waiting;

public class MovingUnit extends MovingGameComponent<FieldScene> {

	public static final int SPEED = 200;
//	private Coord moveTo;
//	private Circle breakMove;
	private UnitState state = new Waiting();
	
	protected final Path path = new Path(this);
	
	public MovingUnit(Appearance rectangle, double xPos, double yPos, UnitVector2D unitVector2D, int speed) {
		super(rectangle, xPos, yPos, unitVector2D, speed);
	}

	public MovingUnit(Color color, double xPos, double yPos) {
		super(new Rectangle(color, 10, 12), xPos, yPos, new UnitVector2D(1, 1), 0);//SPEED);
	}

	@Override
	public void update(DeltaState deltaState) {

		if (deltaState.isMouseButtonReleased(MouseButton.RIGHT) && getMouse().isSelected(this)) {
			this.interact(getMouse().getElementUnderMouse());
		}

		getState().update(this, deltaState);
		
		/* Mover la camara con la' flechitas :D */
//		double x = d.x + Camera.INSTANCE.getX();
//		double y = d.y + Camera.INSTANCE.getY();
		
		super.update(deltaState);
	}
	
	@Override
	public void hasKilled(Unit unit) {
		this.setState(new Waiting());
	}

	public void interact(Unit unit) {
		this.getState().interact(this, unit);
	}

	private MouseHandler getMouse() {
		return this.getScene().getMouse();
	}
	
	public void attack(Unit unit) {
		unit.interactedBy(this);
	}

//	public void updateBreakMove() {
//		setBreakMove(new Circle(this.getMoveTo().getX(), this.getMoveTo().getY(), this.getWidth()));
//	}

//	public Coord getMoveTo() {
//		return moveTo;
//	}
	
	public void setMoveTo(Unit unit) {
		
//		/* Aca deberia ir lo que mueve relativo a la camara? */
//		//this.moveTo = unit.getCoord().add(Camera.INSTANCE.getCoord()); //unit.getCoord();
//		this.moveTo = unit.getCoord();
//		this.setDirection(this.getCoord().getDirectionTo(moveTo));
//		this.updateBreakMove();
		this.setState(new Moving(new Atacking(unit)));
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

//	public Circle getBreakMove() {
//		return breakMove;
//	}
//
//	public void setBreakMove(Circle breakMove) {
//		this.breakMove = breakMove;
//	}
	
	public Path getPath() {
		return path;
	}
	
}
