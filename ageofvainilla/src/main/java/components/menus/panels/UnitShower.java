package components.menus.panels;


import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Label;
import components.BasicAgeComponent;
import components.units.Unit;

import java.awt.*;

public class UnitShower extends BasicAgeComponent {
	private Label life;
	private Label attack;
	private Unit unit;
	private ControlPanel panel;

	public UnitShower(Appearance background) {
		setAppearance(background);
		setLife(new Label(new Font("verdana", Font.BOLD, 30), Color.BLUE, " - "));
		setAttack(new Label(new Font("verdana", Font.BOLD, 30), Color.RED, " - "));
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
		if (getPanel().getScene().getMouse().getSelected().size() == 1) {
			Unit unit = getPanel().getScene().getMouse().getSelected().get(0);
			renderLife(unit, graphics);
			renderAttack(unit, graphics);
		}
	}

	public void renderLife(Unit unit, Graphics2D graphics) {
		double x, y, z;
		x = getX();
		y = getY();
		z = getZ();
		setX(getX() + 10);
		setY(getY() + 10);
		setZ(getZ() + 1);
		getLife().setText(String.format("Life: %s", unit.getLifePoint()));
		getLife().render(this, graphics);
		setZ((int) z);
		setX(x);
		setY(y);
	}


	public void renderAttack(Unit unit, Graphics2D graphics) {
		double x, y, z;
		x = getX();
		y = getY();
		z = getZ();
		setX(getX() + 10);
		setY(getY() + 10 + getLife().getHeight() + 10);
		setZ(getZ() + 1);
		getAttack().setText(String.format("Attack: %s", unit.getPowerAttack()));
		getAttack().render(this, graphics);
		setZ((int) z);
		setX(x);
		setY(y);
	}

	public Label getLife() {
		return life;
	}

	public void setLife(Label life) {
		this.life = life;
	}

	public Label getAttack() {
		return attack;
	}

	public void setAttack(Label attack) {
		this.attack = attack;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public void setPanel(ControlPanel panel) {
		this.panel = panel;
	}

	public ControlPanel getPanel() {
		return panel;
	}
}
