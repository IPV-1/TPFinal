package components.units;

import java.awt.Color;
import java.awt.Point;

import map.path.Path;
import map.tiles.Tile;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.space.UnitVector2D;
import components.MouseHandler;
import components.MovingGameComponent;
import components.factors.Factor;
import components.units.states.Attacking;
import components.units.states.Moving;
import components.units.states.UnitState;
import components.units.states.Waiting;

public class MovingUnit extends MovingGameComponent {

	public static final int SPEED = 200;

	private UnitState state = new Waiting();
	
	private final Path path = new Path(this);
	
	public MovingUnit(Factor factor, Appearance rectangle, double xPos, double yPos, UnitVector2D unitVector2D, int speed) {
		super(rectangle, xPos, yPos, unitVector2D, speed);
		this.setFactor(factor);
	}

	public MovingUnit(Factor factor, Color color, double xPos, double yPos) {
		this(factor, new Rectangle(color, 10, 12), xPos, yPos, new UnitVector2D(1, 1), 0);//SPEED);
	}
	
	public static MovingUnit getAlly(int xTile, int yTile) {
		return new MovingUnit(Factor.BLUE, Color.BLUE, xTile * Tile.WIDTH, yTile * Tile.HEIGHT);
	}
	
	public static MovingUnit getEnemy(int xTile, int yTile) {
		return new MovingUnit(Factor.RED, Color.RED, xTile * Tile.WIDTH, yTile * Tile.HEIGHT);
	}
	
	public static MovingUnit getEnemy(Point point) {
		return MovingUnit.getEnemy(point.x, point.y);
	}

	@Override
	public void update(DeltaState deltaState) {

		if (getMouse().shouldInteract(this, deltaState)) {
			this.interact(getMouse().getElementUnderMouse());
		}

		getState().update(this, deltaState);
		
		super.update(deltaState);
	}
	
	@Override
	public void hasKilled(Unit unit) {
		this.setState(new Waiting());
	}

	@Override
	public void seleccionate(MouseHandler mouseHandler, DeltaState deltaState) {
		super.seleccionate(mouseHandler, deltaState);
		
		if(deltaState.isKeyBeingHold(Key.CTRL) && !mouseHandler.isSelected(this)) {
			mouseHandler.addSelected(this);
		} else {
			mouseHandler.singleSelect(this);
		}
	}

	public void interact(Unit unit) {
		this.getState().interact(this, unit);
		this.getPath().setDestiny((int) (unit.getX() / Tile.WIDTH), (int) (unit.getY() / Tile.HEIGHT));
	}
	
	public void attack(Unit unit) {
		unit.interactedBy(this);
	}

	public void setMoveTo(Unit unit) {
		this.setState(new Moving(new Attacking(unit)));
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Path getPath() {
		return path;
	}
	
}
