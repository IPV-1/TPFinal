package components.units;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

import map.Tile;
import map.path.Path;

import scenes.FieldScene;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.MovingGameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.Coord;
import com.uqbar.vainilla.space.ImmutablePoint;
import com.uqbar.vainilla.space.UnitVector2D;

public class Unit extends MovingGameComponent<FieldScene> {

	public static final int SPEED = 200;
//	private boolean moving = false;
//	private Coord moveTo = new Coord(0, 0);
//	private Circle breakMove;
	
//	protected List<ImmutablePoint> path;
	protected Path path2 = new Path(this);

	public Unit(Color color, double xPos, double yPos) {
		super(new Rectangle(color, 10, 12), xPos, yPos, new UnitVector2D(1, 1), 0);
	}

	@Override
	public void update(DeltaState deltaState) {
//		if(deltaState.isMouseButtonReleased(MouseButton.RIGHT)) {
//			Point2D.Double d = deltaState.getCurrentMousePosition();
//			double x = d.x + Camera.INSTANCE.getX();
//			double y = d.y + Camera.INSTANCE.getY();
//			x = x / Tile.WIDTH;
//			y = y / Tile.HEIGHT;
//			List<ImmutablePoint> l = this.getScene().getPathFinder().findPath(
//					(int)this.getX() / Tile.WIDTH, (int)this.getY() / Tile.HEIGHT, (int)x, (int)y);
//			this.setPath(l);
//			if(!l.isEmpty()) {
//				this.moveTo(l.get(l.size() - 1));
//			}
//		}
//
//		if(moving) {
//			this.checkBreak();
//		}
		this.getPath2().update(deltaState);
		super.update(deltaState);
	}
	
	public Path getPath2() {
		return path2;
	}

//	private void updateBreakMove() {
//		breakMove = new Circle(this.getMoveTo().getX() + 2, this.getMoveTo().getY() + 2, 4);
//	}

//	public void checkBreak() {
//		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(this.breakMove, this.getRect())) {
//			this.setX(this.getMoveTo().getX());
//			this.setY(this.getMoveTo().getY());
//			this.getPath().remove(this.getPath().size() - 1);
//			if(this.getPath().isEmpty()) {
//				moving = false;
//				this.setSpeed(0);
//			} else {
//				this.moveTo(this.getPath().get(this.getPath().size() - 1));
//			}
//		}
//	}
	
//	public void moveTo(double x, double y) {
//		this.setMoveTo(x, y);
//		this.updateBreakMove();
//		this.setSpeed(SPEED);
//		moving = true;
//	}
	
//	public void moveTo(ImmutablePoint point) {
//		this.moveTo(point.x * Tile.WIDTH, point.y * Tile.HEIGHT);
//	}

//	public Coord getMoveTo() {
//		return moveTo;
//	}

//	public void setMoveTo(double x, double y) {
//		this.getMoveTo().set(x, y);
//		this.getDirection().set(x - this.getX(), y - this.getY());
//	}
	
//	public List<ImmutablePoint> getPath() {
//		return this.path;
//	}
//	
//	public void setPath(List<ImmutablePoint> path) {
//		this.path = path;
//	}

}
