package components.units;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Invisible;

import components.MouseHandler;
import components.factors.Factor;
import components.recursos.TiledComponent;

public class Flag extends TiledComponent {

	public Flag(double x, double y) {
		super(new Invisible(), x, y);
		this.factor = Factor.NEU;
	}

	@Override
	public int getLifePoint() {
		return 0;
	}
	
	@Override
	public int getPowerAttack() {
		return 0;
	}

	@Override
	public void seleccionate(MouseHandler mouseHandler, DeltaState deltaState) {
		super.seleccionate(mouseHandler, deltaState);
		
		mouseHandler.clearSelected();
	}
	
}
