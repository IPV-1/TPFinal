package map.path;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import scenes.FieldScene;

import map.Tile;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.MovingGameComponent;
import com.uqbar.vainilla.colissions.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.ImmutablePoint;

public class Path {

	protected final MovingGameComponent<FieldScene> component;
	protected List<ImmutablePoint> points = new ArrayList<ImmutablePoint>();
	protected int currentStep = 0;

	// TODO to remove
	private Circle currentBreak;

	public Path(MovingGameComponent<FieldScene> component) {
		this.component = component;
	}

	public void update(DeltaState deltaState) {
		if (deltaState.isMouseButtonReleased(MouseButton.RIGHT)) {
			Point2D.Double d = deltaState.getCurrentMousePosition();
			int x = (int) ((d.x + Camera.INSTANCE.getX()) / Tile.WIDTH);
			int y = (int) ((d.y + Camera.INSTANCE.getY()) / Tile.HEIGHT);
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
			//TODO reuse one same list. first it will be cleaned then
			this.setPoints(ll);
			if (!ll.isEmpty()) {
				this.moveTo(ll.get(0));// ll.size() - 1));
			} else {
				this.stop();
			}
		}
		if (this.isTraveling()) {
			this.checkBreak();
		}
	}

	public void moveTo(ImmutablePoint point) {
		int x = point.x * Tile.WIDTH;
		int y = point.y * Tile.HEIGHT;
		this.getComponent()
				.getDirection()
				.set(x - this.getComponent().getX(),
						y - this.getComponent().getY());
		this.getComponent().setSpeed(200); // TODO resume proper speed
		this.currentBreak = new Circle(x + 2, y + 2, 4);
	}

	public void checkBreak() {
		if (CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				this.currentBreak, this.getComponent().getRect())) {
			this.getComponent().setX(this.getMoveTo().x * Tile.WIDTH);
			this.getComponent().setY(this.getMoveTo().y * Tile.HEIGHT);
			this.setCurrentStep(this.getCurrentStep() + 1);
			if (!this.isTraveling()) {
				this.getComponent().setSpeed(0);
			} else {
				this.moveTo(this.getMoveTo());
			}
		}
	}

	public ImmutablePoint getMoveTo() {
		return this.getPoints().get(this.getCurrentStep());
	}

	protected boolean isTraveling() {
		return this.getCurrentStep() < this.getPoints().size();
	}

	public void stop() {
		//TODO component should move to a valid tile position
		this.setCurrentStep(this.getPoints().size());
		this.getComponent().setSpeed(0);
	}

	public MovingGameComponent<FieldScene> getComponent() {
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

}
