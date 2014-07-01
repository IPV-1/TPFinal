package components.buttons;

import java.awt.Point;

import com.uqbar.vainilla.space.Coord;

import map.path.PathFinder;
import map.tiles.Tile;

import components.MouseHandler;
import components.units.Unit;
import components.units.buildings.WarriorBuilder;

import config.Configuration;

public class WarriorButton extends BuildingButton {
	
	public WarriorButton() {
		super(new WarriorBuilder(), Configuration.getSprite("stand"), 0, 1);
	}
	
	@Override
	public void clickedBy(MouseHandler mouse) {
		Unit build = mouse.getSingleSelect();
		Coord coord = Coord.getTileCamera(build.getX() + build.getWidth() / 2, build.getY() + build.getHeight(), Tile.WIDTH);
		int xTile = (int)coord.getX();
		int yTile = (int)coord.getY();
		
		Point createPos = new PathFinder(mouse.getScene().getMap()).closestTo(xTile, yTile);
		
		mouse.getScene().addComponent(this.getBuilder());
		
		this.getBuilder().buildIn((int)createPos.getX(), (int)createPos.getY());
		
		mouse.getScene().removeComponent(this.getBuilder());
	}

}
