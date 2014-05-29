package scenes;

import java.awt.Color;

import map.Map;
import map.path.PathFinder;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;
import components.MouseHandler;
import components.menus.ResourcesMenu;
import components.units.MovingUnit;
import components.units.Unit;
import components.units.buildings.BasicBuilding;

public class FieldScene extends GameScene {

	//private String backgroundPath = "board.png";
	
	protected Map map;
	protected PathFinder pathFinder;

	private MouseHandler mouse = new MouseHandler();
	private Unit mockEnemy = new BasicBuilding(Color.RED, 4, 1);

    public FieldScene(Game game) {
        super();
        this.addComponent(Camera.INSTANCE);
        Map map = new Map(28,21,"map");
        this.map = map;;
        this.pathFinder = new PathFinder(map);
        this.addComponent(map);
        this.addComponent(new MovingUnit(Color.BLACK, 10, 10));
        this.addComponent(this.getMockEnemy());
        this.addComponent(this.getMouse());
        
        this.addComponent(new ResourcesMenu());
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
    
    public Map getMap() {
    	return this.map;
    }
    
    public void setMap(Map map) {
    	this.map = map;
    }
    
    public PathFinder getPathFinder() {
    	return this.pathFinder;
    }

}
