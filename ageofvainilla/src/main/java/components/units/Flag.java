package components.units;

import com.uqbar.vainilla.appearances.Invisible;
import com.uqbar.vainilla.space.UnitVector2D;

public class Flag extends Unit {

	public Flag(double x, double y) {
		super(new Invisible(), x, y, new UnitVector2D(1, 1), 0);
	}

	@Override
	public int getLifePoint() {
		return 0;
	}
	
	@Override
	public int getPowerAttack() {
		return 0;
	}
}
