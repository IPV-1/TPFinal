package components.menus.panels.factories;


import java.awt.Color;

import com.uqbar.vainilla.appearances.Rectangle;
import components.menus.panels.UnitShower;

public class UnitShowerFactory {

	public static UnitShower createRectangle(int width, int height, int posX, int posY){
		UnitShower unitShower = new UnitShower(new Rectangle(Color.DARK_GRAY, width, height));
		unitShower.setX(posX);
		unitShower.setY(posY);
		return unitShower;
	}
}
