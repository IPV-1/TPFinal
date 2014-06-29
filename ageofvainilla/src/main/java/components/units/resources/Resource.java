package components.units.resources;

import java.awt.Graphics2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;

import components.factors.Factor;
import components.menus.panels.UnitShower;
import components.recursos.TiledComponent;

public class Resource extends TiledComponent {
	private int amount;
	private String resourceName;

	public Resource(Appearance appearance, double tileX, double tileY, int amount, String resourceName) {
		super(appearance, tileX, tileY);
		setResourceName(resourceName);
		setAmount(amount);
		this.setFactor(Factor.RES);
	}
	
	public void update(DeltaState deltaState) {
		if (getAmount() <= 0) {
			this.removeFromMap();
			destroy();
		}
	}

	public void addResourceToPlayer() {
		int subtracted = subtractResource(10);  //Why 10? I dont know.
		getScene().getResourcesMenu().addResource(getResourceName(), subtracted);
	}

	private int subtractResource(int quantity) {
		int subtracted;
		if (getAmount() >= quantity) {
			setAmount(getAmount() - quantity);
			subtracted = quantity;
		}else {
			subtracted = getAmount();
			setAmount(0);
		}
		return subtracted;

	}
	
	@Override
	public void renderInPanel(UnitShower panel, Graphics2D graphics) {
		panel.renderResource(this, graphics);
	} 

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
}
