package scenes;

import java.awt.Color;

import map.Map;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;
import components.MouseHandler;
import components.buildings.BasicBuilding;
import components.units.MovingUnit;
import components.units.Unit;

public class FieldScene extends GameScene {

	//private String backgroundPath = "board.png";
	
	private MouseHandler mouse = new MouseHandler();
	private Unit mockEnemy = new BasicBuilding(Color.RED, 100, 10);

    public FieldScene(Game game) {
        super();
        this.setGame(game);
        this.addComponent(Camera.INSTANCE);
        this.addComponent(new Map("map"));
        this.addComponent(new MovingUnit(Color.BLACK, 10, 10));
        this.addComponent(this.getMockEnemy());
        this.addComponent(this.getMouse());
    }

	public MouseHandler getMouse() {
		return mouse;
	}

	public void setMouse(MouseHandler mouse) {
		this.mouse = mouse;
	}

	public Unit getMockEnemy() {
		return mockEnemy;
	}

	public void setMockEnemy(Unit mockEnemy) {
		this.mockEnemy = mockEnemy;
	}
    
//	private GameComponent<?> getBackground() {
//        return new GameComponent<GameScene>(Resource.getSprite(backgroundPath), 0, 0);
//	}

}
