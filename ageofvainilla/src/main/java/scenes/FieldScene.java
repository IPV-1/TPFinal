package scenes;

import java.awt.Color;

import map.Map;

import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

import components.MouseHandler;
import components.buildings.BasicBuilding;
import components.units.MovingUnit;

public class FieldScene extends GameScene {

	//private String backgroundPath = "board.png";

    public FieldScene(Game game) {
        super();
        this.setGame(game);
        this.addComponent(new Map("map"));
        this.addComponent(new MovingUnit(Color.BLACK, 10, 10));
        this.addComponent(new BasicBuilding(Color.RED, 100, 10));
        this.addComponent(new MouseHandler());
    }
    
//	private GameComponent<?> getBackground() {
//        return new GameComponent<GameScene>(Resource.getSprite(backgroundPath), 0, 0);
//	}

}
