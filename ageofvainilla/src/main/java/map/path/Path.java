package map.path;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import map.Map;
import map.tiles.Tile;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.ImmutablePoint;
import components.MovingGameComponent;
import components.units.MovingUnit;
import components.units.Unit;

public class Path {

	protected final MovingGameComponent component;
	protected List<ImmutablePoint> points = new ArrayList<ImmutablePoint>();
	protected int currentStep = 0;

	protected Point destiny = new Point();
	protected Unit following;

	private Direction xDir;
	private Direction yDir;

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
		if (!this.isTravelingToDestiny()) {
			this.setPathTo(x, y);
		}
	}

	public void setDestiny(Point p) {
		this.setDestiny(p.x, p.y);
	}

	public boolean isTravelingToDestiny() {
		return this.getPoints().size() > 1;
	}

	protected void setPathTo(int x, int y) {
		List<ImmutablePoint> l = new ArrayList<ImmutablePoint>();
		int xFrom = (int) this.getComponent().getX() / Tile.WIDTH;
		int yFrom = (int) this.getComponent().getY() / Tile.HEIGHT;
		this.getComponent().getScene().getPathFinder()
				.findPath(xFrom, yFrom, x, y, l);
		List<ImmutablePoint> ll = new ArrayList<ImmutablePoint>();
		for (int j = 0, i = l.size() - 1; i >= 0; j++, i--) {
			ll.add(j, l.get(i));
		}
		this.setCurrentStep(0);
		this.setPoints(ll);
		if (ll.size() > 1) {
			this.setCurrentStep(1);
			this.setxDir(Direction.getX(xFrom, ll.get(1).x));
			this.setyDir(Direction.getY(yFrom, ll.get(1).y));
			this.moveTo(ll.get(1));
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
		this.getComponent().setSpeed(MovingUnit.SPEED);

		Map map = this.getComponent().getScene().getMap();
		map.occupy(this.getComponent(), this.getMoveTo().x, this.getMoveTo().y);
	}

	public void checkBreak() {
		if (this.reachedTarget()) {
			this.getComponent().setX(this.getMoveTo().x * Tile.WIDTH);
			this.getComponent().setY(this.getMoveTo().y * Tile.HEIGHT);

			Map map = this.getComponent().getScene().getMap();
			map.setFree(this.getPreviousPoint().x, this.getPreviousPoint().y);
			this.setCurrentStep(this.getCurrentStep() + 1);
			if (this.isFollowing()) {
				this.setDestiny(this.getFollowing().getXTile(), this
						.getFollowing().getYTile());
			}
			this.setPathTo(this.getDestiny().x, this.getDestiny().y);
		}
	}

	public boolean reachedTarget() {
		return this.getxDir().reached(this.getComponent().getX(),
				this.getMoveTo().x * Tile.WIDTH)
				&& this.getyDir().reached(this.getComponent().getY(),
						this.getMoveTo().y * Tile.HEIGHT);
	}

	public void setFree() {
		Map map = this.getComponent().getScene().getMap();
		if (this.getComponent().isMoving()) {
			map.setFree(this.getPreviousPoint().x, this.getPreviousPoint().y);
			map.setFree(this.getMoveTo().x, this.getMoveTo().y);
		} else {
			map.setFree(this.getComponent().getXTile(), this.getComponent()
					.getYTile());
		}
	}

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
		this.setCurrentStep(this.getPoints().size());
		this.getComponent().setSpeed(0);
		this.stopFollowing();
	}

	public void lightStop() {
		if(this.isTravelingToDestiny()) {
			this.setDestiny(this.getMoveTo().x, this.getMoveTo().y);
		}
	}

	public boolean isFollowing() {
		return this.getFollowing() != null;
	}

	public void stopFollowing() {
		this.setFollowing(null);
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

	public Unit getFollowing() {
		return following;
	}

	public void setFollowing(Unit following) {
		this.following = following;
	}

	public Direction getxDir() {
		return xDir;
	}

	public void setxDir(Direction xDir) {
		this.xDir = xDir;
	}

	public Direction getyDir() {
		return yDir;
	}

	public void setyDir(Direction yDir) {
		this.yDir = yDir;
	}

}
