package map.path;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import map.Map;
import map.tiles.Tile;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.colissions.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.ImmutablePoint;
import components.MovingGameComponent;
import components.units.MovingUnit;

public class Path {

	protected final MovingGameComponent component;
	protected List<ImmutablePoint> points = new ArrayList<ImmutablePoint>();
	protected int currentStep = 0;
	
	protected Point destiny = new Point();

	// TODO to remove
	private Circle currentBreak;

	public Path(MovingGameComponent component) {
		this.component = component;
	}
	
	public void update(DeltaState deltaState) {
		if (deltaState.isMouseButtonReleased(MouseButton.RIGHT)
				&& this.getComponent().getMouse()
						.isSelected(this.getComponent())) {
			Point2D.Double d = deltaState.getCurrentMousePosition();
			int x = (int) ((d.x + Camera.INSTANCE.getX()) / Tile.WIDTH);
			int y = (int) ((d.y + Camera.INSTANCE.getY()) / Tile.HEIGHT);
			this.setDestiny(x, y);
		}
		if (this.isTravelingToDestiny()) {
			this.checkBreak();
		}
	}
	
	public void setDestiny(int x, int y) {
		this.getDestiny().setLocation(x, y);
		if(!this.isTravelingToDestiny()) {
			this.setPathTo(x, y);
		}
	}
	
	public boolean isTravelingToDestiny() {
		return this.getPoints().size() > 1;
		//this.getComponent().getX() * Tile.WIDTH != this.getDestiny().x ||
				//this.getComponent().getY() * Tile.HEIGHT != this.getDestiny().y;
	}

	public void update2(DeltaState deltaState) {
		if (deltaState.isMouseButtonReleased(MouseButton.RIGHT)
				&& this.getComponent().getMouse()
						.isSelected(this.getComponent())) {
			Point2D.Double d = deltaState.getCurrentMousePosition();
			int x = (int) ((d.x + Camera.INSTANCE.getX()) / Tile.WIDTH);
			int y = (int) ((d.y + Camera.INSTANCE.getY()) / Tile.HEIGHT);
			this.setPathTo(x, y);
		}
		if (this.isTraveling()) {
			this.checkBreak();
		}
	}
	
	protected void setPathTo(int x, int y) {
		List<ImmutablePoint> l = new ArrayList<ImmutablePoint>();
		this.getComponent()
				.getScene()
				.getPathFinder()
				.findPath((int) this.getComponent().getX() / Tile.WIDTH,
						(int) this.getComponent().getY() / Tile.HEIGHT, x,
						y, l);
		List<ImmutablePoint> ll = new ArrayList<ImmutablePoint>();
		for (int j = 0, i = l.size() - 1; i >= 0; j++, i--) {
			ll.add(j, l.get(i));
		}
		this.setCurrentStep(0);
		// TODO reuse the same list. first it will be cleaned then
		this.setPoints(ll);
		if (ll.size() > 1) {
			this.setCurrentStep(1);
			this.moveTo(ll.get(1));// ll.size() - 1));
		} else {
			this.stop();
		}
	}

	public void moveTo(ImmutablePoint point) {
		int x = point.x * Tile.WIDTH;
		int y = point.y * Tile.HEIGHT;
		this.getComponent()
				.getDirection()
				.set(x - this.getComponent().getX(),
						y - this.getComponent().getY());
		this.getComponent().setSpeed(MovingUnit.SPEED); // TODO change state
														// instead
		this.currentBreak = new Circle(x + 2, y + 2, 4);
		
		Map map = this.getComponent().getScene().getMap();
		map.occupy(this.getComponent(), this.getMoveTo().x, this.getMoveTo().y);
	}

	public void checkBreak() {
		if (CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				this.currentBreak, this.getComponent().getRect())) {
			this.getComponent().setX(this.getMoveTo().x * Tile.WIDTH);
			this.getComponent().setY(this.getMoveTo().y * Tile.HEIGHT);
			
			Map map = this.getComponent().getScene().getMap();
			map.setFree(this.getPreviousPoint().x, this.getPreviousPoint().y);
			this.setCurrentStep(this.getCurrentStep() + 1);
			this.setPathTo(this.getDestiny().x, this.getDestiny().y);
			
			/*
			
//			ImmutablePoint destination = this.destination();
			
//			boolean destinyOcuppied = this.getComponent().getScene().getMap()
//					.isBlocked(destination.x, destination.y);
				this.applyChangesOnMap();
//				if(destinyOcuppied) {
//					this.moveTo(destination);
//				} else {
					this.setCurrentStep(this.getCurrentStep() + 1);
					if (!this.isTraveling()) {
						this.getComponent().setSpeed(0);
					} else {
						this.moveTo(this.getMoveTo());
					}
				}
//			}
//			else {
//				ImmutablePoint destination = this.destination();
////				this.moveTo(destination);
//				if(!this.getComponent().getScene().getMap()
//						.isBlocked(destination.x, destination.y)) {
//					this.moveTo(destination);
//				} else {
//					this.setPathToClosestFreeTileFrom(this.getMoveTo().x, this.getMoveTo().y);
//				}
//			} */
		}
	}
	
//	protected void setPathToClosestFreeTileFrom(int xTo, int yTo) {
//		//TODO
//		Point p = this.getComponent()
//				.getScene()
//				.getPathFinder().closestTo(xTo, yTo);
//		this.setPathTo(p.x, p.y);
//	}

	protected void applyChangesOnMap() {
		Map map = this.getComponent().getScene().getMap();
		map.setFree(this.getPreviousPoint().x, this.getPreviousPoint().y);
		map.occupy(this.getComponent(), this.getMoveTo().x, this.getMoveTo().y);
	}

	public ImmutablePoint getMoveTo() {
		return this.getPoints().get(this.getCurrentStep());
	}

	public ImmutablePoint getPreviousPoint() {
		return this.getPoints().get(this.getCurrentStep() - 1);
	}
	
	public ImmutablePoint destination() {
		return this.getPoints().get(this.getPoints().size() - 1);
	}

	public boolean isTraveling() {
		return this.getCurrentStep() < this.getPoints().size();
	}

	public void stop() {
		// TODO component should move to a valid tile position
		this.setCurrentStep(this.getPoints().size());
		this.getComponent().setSpeed(0);
	}

	public MovingGameComponent getComponent() {
		return this.component;
	}

	protected List<ImmutablePoint> getPoints() {
		return this.points;
	}

	protected void setPoints(List<ImmutablePoint> points) {
		this.points = points;
	}

	protected int getCurrentStep() {
		return this.currentStep;
	}

	protected void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}
	
	public Point getDestiny() {
		return destiny;
	}

}
