package components.units;

import com.uqbar.vainilla.appearances.Appearance;
import components.CameraRelativeComponent;

public abstract class Unit extends CameraRelativeComponent {

	private int lifePoint = 1000;
	private int powerAttack = 10;

	public Unit() {
		super();
	}

	public Unit(Appearance appearance, double x, double y) {
		super(appearance, x, y);
	}

	public Unit(double x, double y) {
		super(x, y);
	}

	public void interactedBy(Unit unit) {
		this.decrementLife(unit.getPowerAttack());

		if(!this.isDead()) {
			unit.decrementLife(this.getPowerAttack());
		} else {
			unit.hasKilled(this);
		}
	}

	public void hasKilled(Unit unit){}

	public void decrementLife(int points) {
		this.setLifePoint(this.getLifePoint() - points);

		if(isDead()) {
			// estado = muerto
			this.destroy();
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