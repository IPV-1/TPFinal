package components.units;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import map.tiles.Tile;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.events.constants.Key;
import components.CameraRelativeComponent;
import components.MouseHandler;
import components.buttons.BuildingButton;
import components.buttons.DeleteButton;
import components.factors.Factor;
import components.interfaces.Selectable;
import components.menus.panels.UnitShower;

import config.Configuration;

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

	public void interactedBy(Unit unit, DeltaState deltaState) {
		if (this.isEnemy(unit) && !unit.isDead() && !this.isDead()) {
			this.decrementLife((int) (unit.getPowerAttack()
					* deltaState.getDelta() * 32));

			if (!this.isDead()) {
				unit.decrementLife((int) (this.getPowerAttack()
						* deltaState.getDelta() * 32));
				if (unit.isDead()) {
					this.hasKilled(unit);
				}
			} else {
				unit.hasKilled(this);
			}
		} else {
			unit.hasKilled(this);
		}
	}

	public void hasKilled(Unit unit) {
	}

	public void setFree() {
		this.getScene().getMap().setFree(this.getXTile(), this.getYTile());
	}

	public void decrementLife(int points) {
		this.setLifePoint(this.getLifePoint() - points);

		if (isDead()) {
			// estado = muerto
			this.removeFromMap();
			this.destroy();
			if (this.getFactor().isAlly()) {
				this.getScene().decreaseAlly();
			}
		}
	}

	public boolean isMoving() {
		return this.getX() / Tile.WIDTH % 1 != 0
				|| this.getY() / Tile.HEIGHT % 1 != 0;
	}

	public boolean isDead() {
		return this.getLifePoint() <= 0;
	}

	public int getXTile() {
		return (int) (this.getX() / Tile.WIDTH);
	}

	public int getYTile() {
		return (int) (this.getY() / Tile.HEIGHT);
	}

	@Override
	public void seleccionate(MouseHandler mouse, DeltaState deltaState) {

		if (deltaState.isKeyBeingHold(Key.CTRL) && !mouse.isSelected(this)) {
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
		getScene().getMap().setFree((int) (this.getX() / Tile.WIDTH),
				(int) (this.getY() / Tile.HEIGHT));
	}

	public void renderInPanel(UnitShower panel, Graphics2D graphics) {
		panel.renderLife(this, graphics);
		panel.renderAttack(this, graphics);
	}

	public Double getFarmSpeed() {
		return Configuration.getValue("farmPerMinute_unit");
	}

	public Appearance getScaledAppearance(Appearance appearance) {
		return Configuration.getScaledAppearance(appearance);
	}

	@SuppressWarnings("serial")
	public List<BuildingButton> getButtons() {
		return new ArrayList<BuildingButton>() {{
			add(new DeleteButton());
		}};
	}

}