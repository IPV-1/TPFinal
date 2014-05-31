package components.units.panels;


import com.uqbar.vainilla.appearances.Appearance;

public class Builder {
	private Appearance appearance;
	private int z;

	public ControlPanel build(int x, int y) {
		ControlPanel panel = new ControlPanel(getAppearance(), x, y);
		panel.setZ(getZ());
		return panel;
	}

	public Builder withAppearance(Appearance appearance) {
		setAppearance(appearance);
		return this;
	}

	public Builder withZ(int z) {
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
}
