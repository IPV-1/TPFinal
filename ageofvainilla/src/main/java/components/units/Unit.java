package components.units;

import map.tiles.Tile;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.events.constants.Key;

import components.CameraRelativeComponent;
import components.MouseHandler;
import components.factors.Factor;
import components.interfaces.Selectable;

public abstract class Unit extends CameraRelativeComponent implements Selectable {

	private int lifePoint = 1000;
	private int powerAttack = 0;
	
	private Factor factor;

	public Unit() {
		super();
	}

	public Unit(Appearance appearance, double x, double y) {
		super(appearance, x, y);
	}

	public Unit(double x, double y) {
		super(x, y);
	}
	
	public boolean isEnemy(Unit unit) {
		return this.getFactor().isEnemy(unit.getFactor());
	}
	
	public boolean isResource(Unit unit) {
		return this.getFactor().isResource();
	}

	public void interactedBy(Unit unit) {
		if(this.isEnemy(unit)) {
			this.decrementLife(unit.getPowerAttack());
	
			if(!this.isDead()) {
				unit.decrementLife(this.getPowerAttack());
			} else {
				unit.hasKilled(this);
			}
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
	
	@Override
	public void seleccionate(MouseHandler mouse, DeltaState deltaState) {
		
		if(deltaState.isKeyBeingHold(Key.CTRL) && !mouse.isSelected(this)) {
			mouse.addSelected(this);
		} else {
			mouse.singleSelect(this);
		}
	}
	
	@Override
	public void seleccionate(MouseHandler mouse) {
		mouse.singleSelect(this);
	}

	@Override
	public void deseleccionate(MouseHandler mouse) {
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

	public Factor getFactor() {
		return factor;
	}

	public void setFactor(Factor factor) {
		this.factor = factor;
	}
	
	public void removeFromMap() {
		getScene().getMap().setFree((int)(this.getX() / Tile.WIDTH), (int)(this.getY() / Tile.HEIGHT));
	}

}