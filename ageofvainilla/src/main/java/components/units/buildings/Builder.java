package components.units.buildings;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;
import java.util.Map;

import map.tiles.Tile;
import scenes.FieldScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.space.Coord;
import components.MouseHandler;
import components.units.Unit;

public class Builder extends Unit {
	private int widthInTiles;
	private int heightInTiles;
	
	public Builder(int widthInTiles, int heightInTiles) {
		super(0, 0);
		Appearance buildingAppearance = new Rectangle(Color.RED, Tile.WIDTH * widthInTiles, Tile.WIDTH * heightInTiles);
		setAppearance(buildingAppearance);
		setWidthInTiles(widthInTiles);
		setHeightInTiles(heightInTiles);
	}

	protected Builder(Appearance appearance, int widthInTiles, int longInTiles) {
		this(0, 0);
		this.setAppearance(appearance);
	}

	public BasicBuilding build(int tileX, int tileY) {
		return new BasicBuilding(this, tileX, tileY);
	}
	
	public Map<String, Integer> getCost() {
		return 	new HashMap<String, Integer>();
	}

	public void buildIn(int tileX, int tileY) {
		BasicBuilding building = this.build(tileX, tileY);
		if (this.canBuild(building)) {
			effectiveBuildIn(this.getScene(), building);
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

	public int getHeightInTiles() {
		return heightInTiles;
	}

	public void setHeightInTiles(int heightInTiles) {
		this.heightInTiles = heightInTiles;
	}
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		
		if (getScene().getMouse().shouldInteract(this, deltaState)) {
			Coord tile = Coord.getTileCamera(getScene().getMouse().getX(), getScene().getMouse().getY(), Tile.WIDTH);
			buildIn((int)tile.getX(), (int)tile.getY());
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

	public Appearance getBuildingAppearance() {
		return this.getAppearance().copy();
	}
	
	public Appearance getScaledAppearance(Appearance appearance) {
		Appearance app = appearance.copy();
		
		if(app instanceof Sprite) {
			app = ((Sprite)app).scaleTo(Tile.WIDTH * getWidthInTiles(), Tile.HEIGHT * getHeightInTiles());
		}
		
		return app;
	}
	
	public static void effectiveBuildIn(FieldScene scene, BasicBuilding building) {
		scene.getMap().occupyBuilding(building);
		scene.addComponent(building);
		scene.getResourcesMenu().updateResources(building);
	}

	public static void initialBuild(FieldScene scene, int tileX, int tileY) {
		effectiveBuildIn(scene, new Builder(1, 1).build(tileX, tileY));
	}
	
}
