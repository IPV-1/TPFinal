package components.factors;

import components.units.Unit;
import components.units.states.Attacking;
import components.units.states.Farming;
import components.units.states.UnitState;
import components.units.states.Waiting;

public abstract class Factor {
	
	public static final Factor RED = new RedFactor();
	public static final Factor BLUE = new BlueFactor();
	public static final Factor RES = new ResourceFactor();
	public static final Factor NEU = new NeutralFactor();
	
	protected Factor() {
	}
	
	public boolean isEnemy() {
		return false;
	}
	
	public boolean isAlly() {
		return false;
	}
	
	public boolean isEnemy(Factor factor){
		return false;
	}
	
	protected boolean isEnemyOfRedFactor() {
		return false;
	}
	
	protected boolean isEnemyOfBlueFactor() {
		return false;
	}
	
	public boolean isResource() {
		return false;
	}
	
	public boolean isNeutral() {
		return false;
	}

	public UnitState nextState(Unit unit) {
		if(this.isEnemy(unit.getFactor())) {
			return new Attacking(unit);
		} else if(unit.getFactor().isResource()) {
			return new Farming(unit);
		}
		
		return new Waiting();
	}

	public static Factor getMyFactor() {
		return Factor.BLUE;
	}

}
