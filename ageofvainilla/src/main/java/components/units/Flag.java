package components.units;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Invisible;

import components.MouseHandler;
import components.buttons.BuildingButton;
import components.factors.Factor;
import components.menus.panels.UnitShower;
import components.recursos.TiledComponent;

public class Flag extends TiledComponent {

	public Flag(double x, double y) {
		super(new Invisible(), x, y);
		this.setFactor(Factor.NEU);
	}

	public Flag() {
		this(0, 0);
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
	
	@Override
	public void renderInPanel(UnitShower panel, Graphics2D graphics) {
	}
	
	@Override
	public List<BuildingButton> getButtons() {
		return new ArrayList<BuildingButton>();
	}
	
}
