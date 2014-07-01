package components.units.states;

import map.path.PathFinder;

import com.uqbar.vainilla.DeltaState;
import components.units.MovingUnit;
import components.units.Unit;

public class Attacking extends UnitState {

	Unit enemy;

	public Attacking(Unit enemy) {
		this.enemy = enemy;
	}

	@Override
	public void update(MovingUnit movingUnit, DeltaState deltaState) {
		PathFinder pf = movingUnit.getScene().getPathFinder();
		if (pf.tileDistance(movingUnit.getXTile(), movingUnit.getYTile(),
				this.enemy.getXTile(), this.enemy.getYTile()) > 1) {
				movingUnit.setState(new Moving(new Attacking(this.enemy)));
				movingUnit.getPath().setDestiny(this.enemy.getXTile(), this.enemy.getYTile());
		} else {
			movingUnit.attack(this.enemy, deltaState);
		}
	}

}
