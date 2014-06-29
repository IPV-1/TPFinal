package components;

import java.awt.Point;

import com.uqbar.vainilla.DeltaState;

import components.factors.Factor;
import components.units.MovingUnit;
import components.units.Unit;

public class EnemyController extends BasicAgeComponent {
	
	protected int pc = 0;
	protected Point spawnPoint = new Point(10, 2);
	protected final int PC_LIMIT = 200;
	
	@Override
	public void update(DeltaState deltaState) {
		this.increasePc();
		if(this.getPc() == PC_LIMIT) {
			this.createEnemy();
			this.resetPc();
		}
		super.update(deltaState);
	}
	
	protected void increasePc() {
		this.setPc(this.getPc() + 1);
	}
	
	protected void resetPc() {
		this.setPc(0);
	}
	
	protected void createEnemy() {
		Point point = this.getScene().getPathFinder().closestTo(this.getSpawnPoint());
		MovingUnit unit = MovingUnit.getEnemy(point);
		this.getScene().addMovingUnit(unit);
		unit.interact(this.getUnitToAttack());
	}
	
	protected Unit getUnitToAttack() {
		Point point = this.getScene().getPathFinder().closestTo(Factor.BLUE, this.getSpawnPoint());
		return this.getScene().getMap().get(point.x, point.y).getOcuppant();
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}
	
	public Point getSpawnPoint() {
		return this.spawnPoint;
	}

}
