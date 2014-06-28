package components.units.buildings;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;

import map.tiles.Tile;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.space.Coord;
import components.MouseHandler;
import components.units.Unit;

public class Builder extends Unit {
	private int widthInTiles;
	private int longInTiles;
	
	public Builder(int widthInTiles, int heightInTiles) {
		super(0, 0);
		Appearance buildingAppearance = new Rectangle(Color.RED, Tile.WIDTH * widthInTiles, Tile.WIDTH * heightInTiles);
		setAppearance(buildingAppearance);
		setWidthInTiles(widthInTiles);
		setLongInTiles(heightInTiles);
	}

	protected Builder(Appearance appearance, int widthInTiles, int longInTiles) {
		super(appearance, 0, 0);
		setWidthInTiles(widthInTiles);
		setLongInTiles(longInTiles);
	}

	public BasicBuilding build(double x, double y) {
		BasicBuilding b = new BasicBuilding(getAppearance().copy(), x, y, getWidthInTiles(), getLongInTiles());
		b.setCost(new HashMap<String, Integer>());
		return b;
	}
	
	public void buildIn(double  tileX, double tileY) {
		BasicBuilding building = this.build(tileX, tileY);
		if (this.canBuild(building)) {
		
			getScene().getMap().occupy(building, (int)tileX, (int)tileY);
			getScene().addComponent(building);
			getScene().getResourcesMenu().updateResources(building);
		} else {
			// Do what?
		}
	}

	private boolean canBuild(BasicBuilding building) {
		return getScene().getResourcesMenu().canBuild(building) &&
				getScene().getMap().canBuild(building);
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
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		
		if (getScene().getMouse().shouldInteract(this, deltaState)) {
			Coord tile = Coord.getTileCamera(getScene().getMouse().getX(), getScene().getMouse().getY(), Tile.WIDTH);
			buildIn(tile.getX(), tile.getY());
		}
		
		Double position = deltaState.getCurrentMousePosition();
		this.setX(position.getX());
		this.setY(position.getY());
	}
	
	@Override
	public void seleccionate(MouseHandler mouse) {
		super.seleccionate(mouse);
		
		mouse.singleSelect(this);
		mouse.getScene().addComponent(this);
	}

	@Override
	public void deseleccionate(MouseHandler mouse) {
		super.deseleccionate(mouse);
		
		mouse.getScene().removeComponent(this);
	}
	
	@Override
	public void render(Graphics2D graphics) {
		this.getAppearance().render(this, graphics);
	}
	
}
