package components.units.panels.factories;


import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.appearances.Rectangle;

import components.menus.panels.UnitShower;

import java.awt.*;

public class UnitShowerFactory {

	public static UnitShower createRectangle(Game game, int width, int height, int posX, int posY){
		UnitShower unitShower = new UnitShower(new Rectangle(Color.DARK_GRAY, width, height));
		unitShower.setX(posX);
		unitShower.setY(posY);
		return unitShower;
	}
}
