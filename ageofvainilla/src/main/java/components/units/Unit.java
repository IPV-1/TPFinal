package components.units;

import scenes.FieldScene;

import com.uqbar.vainilla.MovingGameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.space.UnitVector2D;

public abstract class Unit extends MovingGameComponent<FieldScene> {
	
	private int lifePoint = 1000;
	private int powerAttack = 10;

	public Unit(Appearance rectangle, double xPos, double yPos, UnitVector2D unitVector2D, int speed) {
		super(rectangle, xPos, yPos, unitVector2D, speed);
	}

	public void attack(Unit unit) {
		unit.attackedBy(this);
	}
	
	private void attackedBy(Unit unit) {
		int newLife = this.getLifePoint() - unit.getPowerAttack();
		
		if(newLife <= 0) {
			// estado = muerto
			this.destroy();
		} else {
			this.setLifePoint(newLife);
		}
		
		this.attack(unit);
	}

	public void suicide() {
		this.attack(this);
	}

	public int getLifePoint() {
		return lifePoint;
	}

	public void setLifePoint(int lifePoint) {
		this.lifePoint = lifePoint;
	}

	public int getPowerAttack() {
		return powerAttack;
	}

	public void setPowerAttack(int powerAttack) {
		this.powerAttack = powerAttack;
	}

}