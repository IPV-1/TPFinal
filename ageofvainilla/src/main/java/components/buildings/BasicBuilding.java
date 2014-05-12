package components.buildings;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.space.UnitVector2D;

import components.units.Unit;

public class BasicBuilding extends Unit {
	public BasicBuilding(Color color, double xPos, double yPos) {
		super(new Rectangle(color, 10, 12), xPos, yPos, new UnitVector2D(1, 1), 0);
	}
}
