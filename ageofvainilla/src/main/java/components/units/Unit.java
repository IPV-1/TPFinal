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
		this.decrementLife(unit.getPowerAttack());

		if(!this.isDead()) {
			unit.decrementLife(this.getPowerAttack());
		} else {
			unit.hasKilled(this);
		}
	}

	public void hasKilled(Unit unit){}

	public void suicide() {
		this.attack(this);
	}

	public void decrementLife(int points) {
		this.setLifePoint(this.getLifePoint() - points);
		System.out.println(getLifePoint());

		if(isDead()) {
			// estado = muerto
			this.destroy();
			System.out.println("me destrui");
		}
	}

	public boolean isDead() {
		return this.getLifePoint() <= 0;
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