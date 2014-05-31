package scenes;

import java.awt.Color;

import map.Map;
import map.path.PathFinder;
import map.tiles.Tile;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.space.Coord;

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
	private Unit mockEnemy;
	public MovingUnit initialUnit1 = new MovingUnit(Color.BLACK, 200, 110);
	public MovingUnit initialUnit2 = new MovingUnit(Color.DARK_GRAY, 110, 110);

    public FieldScene(Game game) {
        super();
        this.addComponent(Camera.INSTANCE);
        Map map = new Map(28,21,"map");
        this.map = map;
        this.pathFinder = new PathFinder(map);
        this.addComponent(map);
        
        this.addMovingUnit(initialUnit1);
        this.addMovingUnit(initialUnit2);

		setMockEnemy(new BasicBuilding(Color.RED, 4, 1));
        this.addComponent(this.getMockEnemy());
        this.addComponent(this.getMouse());
        
        this.addComponent(new ResourcesMenu());
    }

	private void addMovingUnit(MovingUnit unit) {
		this.addComponent(unit);
		
		Coord tileCoord = new Coord(unit.getX(), unit.getY()).getTile(Tile.WIDTH);
		this.getMap().occupy(unit, (int)tileCoord.getX(), (int)tileCoord.getY());
		
		//this.getMouse().addSelected(unit);
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
