package components.menus.panels;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Label;
import components.BasicAgeComponent;
import components.units.Unit;
import components.units.resources.Resource;


public class UnitShower extends BasicAgeComponent {
	private Label life;
	private Label attack;
	private ControlPanel panel;

	public UnitShower(Appearance background) {
		setAppearance(background);
		setLife(new Label(new Font("verdana", Font.BOLD, 24), Color.BLUE, " - "));
		setAttack(new Label(new Font("verdana", Font.BOLD, 24), Color.RED, " - "));
	}

	public void render(Graphics2D graphics, Unit unit) {
		super.render(graphics);
		unit.renderInPanel(this, graphics);
	}

	public void renderLife(Unit unit, Graphics2D graphics) {
		String text = String.format("Life: %s", unit.getLifePoint());
		
		renderInfo(text, graphics);
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

	public void renderResource(Resource res, Graphics2D graphics) {
		String text = String.format("%s: %s", res.getResourceName(), res.getAmount());
		
		renderInfo(text, graphics);
	}

	private void renderInfo(String text, Graphics2D graphics) {
		double x, y, z;
		x = getX();
		y = getY();
		z = getZ();
		setX(getX() + 10);
		setY(getY() + 10);
		setZ(getZ() + 1);
		getLife().setText(text);
		getLife().render(this, graphics);
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

	public void setPanel(ControlPanel panel) {
		this.panel = panel;
	}

	public ControlPanel getPanel() {
		return panel;
	}
	
}
