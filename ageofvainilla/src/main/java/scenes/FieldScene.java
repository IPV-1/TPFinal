package scenes;

import map.Map;
import map.path.PathFinder;
import map.tiles.Tile;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.space.Coord;

import components.EnemyController;
import components.MouseHandler;
import components.menus.ResourcesMenu;
import components.menus.panels.ControlPanel;
import components.units.MovingUnit;
import components.units.buildings.BasicBuilding;
import components.units.buildings.Builder;
import components.units.panels.factories.PanelBuilder;
import components.units.panels.factories.PanelFactory;
import components.units.resources.factory.ResourceFactory;

public class FieldScene extends GameScene {

	//private String backgroundPath = "board.png";
	
	protected Map map;
	protected PathFinder pathFinder;
	private ResourcesMenu resourcesMenu;
	private ControlPanel controlPanel;
	private EnemyController enemyController = new EnemyController();

	private MouseHandler mouse = new MouseHandler();
	public MovingUnit initialUnit2 = MovingUnit.getAlly(3, 3);
	public MovingUnit initialUnit1 = MovingUnit.getEnemy(6, 3);

    public FieldScene(Game game) {
        super();
        this.addComponent(Camera.INSTANCE);
        Map map = new Map(28,21,"map");
        this.map = map;
        this.pathFinder = new PathFinder(map);
        this.addComponent(map);
        
        this.addMovingUnit(initialUnit1);
        this.addMovingUnit(initialUnit2);

		BasicBuilding building = new Builder(1, 1).build(4, 1);
        this.addComponent(building);
        this.addComponent(ResourceFactory.createGold(4,2, 200));
        this.addComponent(this.getMouse());
        
        addResourceMenu(new ResourcesMenu());
		//Comment this out for seeing control panel
		addControlPanel(game);
		
		this.addComponent(this.getEnemyController());
    }


	public void addResourceMenu(ResourcesMenu menu){
		setResourcesMenu(menu);
		addComponent(menu);
	}


	private void addControlPanel(Game game) {
		PanelFactory panelFactory = new PanelFactory(new PanelBuilder());
		ControlPanel panel = panelFactory.downPanelFullWith(game);
		this.setControlPanel(panel);
		addComponent(panel);
	}

	public void addMovingUnit(MovingUnit unit) {
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

	public ResourcesMenu getResourcesMenu() {
		return resourcesMenu;
	}

	public void setResourcesMenu(ResourcesMenu resourcesMenu) {
		this.resourcesMenu = resourcesMenu;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public EnemyController getEnemyController() {
		return enemyController;
	}


	public void setEnemyController(EnemyController enemyController) {
		this.enemyController = enemyController;
	}
	
}
