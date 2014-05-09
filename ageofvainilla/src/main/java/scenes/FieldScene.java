package scenes;

import java.awt.Color;

import map.Map;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

import components.MouseHandler;
import components.units.Unit;

public class FieldScene extends GameScene {

	//private String backgroundPath = "board.png";

    public FieldScene(Game game) {
        super();
        this.setGame(game);
        this.addComponent(Camera.INSTANCE);
        this.addComponent(new Map("map"));
        this.addComponent(new Unit(Color.BLACK, 10, 10));
        this.addComponent(new MouseHandler());
    }
    
//	private GameComponent<?> getBackground() {
//        return new GameComponent<GameScene>(Resource.getSprite(backgroundPath), 0, 0);
//	}

}
