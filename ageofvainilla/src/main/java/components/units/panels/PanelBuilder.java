package components.units.panels;


import com.uqbar.vainilla.appearances.Appearance;

import java.util.ArrayList;
import java.util.List;

public class PanelBuilder {
	private Appearance appearance;
	private int z;
	private List<BuildingButton> buttons = new ArrayList<BuildingButton>();

	public ControlPanel build(int x, int y) {
		ControlPanel panel = new ControlPanel(getAppearance(), x, y);
		panel.setZ(getZ());
		panel.addButtons(getButtons());
		return panel;
	}

	public PanelBuilder withButton(BuildingButton button){
		getButtons().add(button);
		return this;
	}

	public PanelBuilder withAppearance(Appearance appearance) {
		setAppearance(appearance);
		return this;
	}

	public PanelBuilder withZ(int z) {
		setZ(z);
		return this;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getZ() {
		return z;
	}

	public List<BuildingButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<BuildingButton> buttons) {
		this.buttons = buttons;
	}
}
