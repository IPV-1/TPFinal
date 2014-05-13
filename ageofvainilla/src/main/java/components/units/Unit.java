package components.units;

import java.awt.Color;

import scenes.FieldScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.MovingGameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.MouseButton;
import com.uqbar.vainilla.space.Coord;
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
			System.out.println("me destrui");
		} else {
			this.setLifePoint(newLife);
			System.out.println(newLife);
			this.attack(unit);
		}
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