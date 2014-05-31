package components.units.buildings;


import com.uqbar.vainilla.appearances.Appearance;

public class Builder {
	private Appearance appearance;
	private int widthInTiles;
	private int longInTiles;

	public Builder(Appearance appearance, int widthInTiles, int longInTiles) {
		setAppearance(appearance);
		setWidthInTiles(widthInTiles);
		setLongInTiles(longInTiles);
	}

	public BasicBuilding build(int x, int y) {
		return new BasicBuilding(getAppearance().copy(), x, y, getWidthInTiles(), getLongInTiles());
	}


	public Appearance getAppearance() {
		return appearance;
	}

	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}

	public int getWidthInTiles() {
		return widthInTiles;
	}

	public void setWidthInTiles(int widthInTiles) {
		this.widthInTiles = widthInTiles;
	}

	public int getLongInTiles() {
		return longInTiles;
	}

	public void setLongInTiles(int longInTiles) {
		this.longInTiles = longInTiles;
	}
}
