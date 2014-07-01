package components;

import java.awt.Point;

import com.uqbar.vainilla.DeltaState;

import components.factors.Factor;
import components.units.MovingUnit;
import components.units.Unit;
import config.Configuration;

public class EnemyController extends BasicAgeComponent {

	protected int pc = 0;
	protected Point spawnPoint = new Point(Configuration
			.getValue("enemySpawnX").intValue(), Configuration.getValue(
			"enemySpawnY").intValue());
	protected int pcLimit = Configuration.getValue("enemyDelay").intValue();

	@Override
	public void update(DeltaState deltaState) {
		this.increasePc(deltaState);
		if (this.getPc() > pcLimit) {
			pcLimit -= Math.min(
					pcLimit
							- Configuration.getValue("enemySubstractingDelay")
									.intValue(),
					Configuration.getValue("enemyMinimumDelay").intValue());
			this.createEnemy();
			this.resetPc();
		}
		super.update(deltaState);
	}

	protected void increasePc(DeltaState deltaState) {
		this.setPc((int) (this.getPc() + deltaState.getDelta() * 100));
	}

	protected void resetPc() {
		this.setPc(0);
	}

	protected void createEnemy() {
		Point point = this.getScene().getPathFinder()
				.closestTo(this.getSpawnPoint());
		MovingUnit unit = MovingUnit.getEnemy(point);
		this.getScene().addMovingUnit(unit);
		unit.interact(this.getUnitToAttack());
	}

	public Unit getUnitToAttack() {
		Point point = this.getScene().getPathFinder()
				.closestTo(Factor.BLUE, this.getSpawnPoint());
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
