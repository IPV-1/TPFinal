package components.units.buildings;


import com.uqbar.vainilla.appearances.Appearance;

public class Builder {
	private Appearance appearance;

	public Builder(Appearance appearance) {
		setAppearance(appearance);
	}

	public BasicBuilding build(int x, int y, int widthInTiles, int longInTiles) {
		return new BasicBuilding(getAppearance().copy(), x, y, widthInTiles, longInTiles);
	}


	public Appearance getAppearance() {
		return appearance;
	}

	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}
}
