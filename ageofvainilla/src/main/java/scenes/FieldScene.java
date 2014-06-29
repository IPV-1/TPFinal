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
import components.menus.panels.factories.PanelBuilder;
import components.menus.panels.factories.PanelFactory;
import components.recursos.ResourcesBuilder;
import components.units.MovingUnit;
import components.units.buildings.Builder;
import config.Configuration;

public class FieldScene extends GameScene {

	//private String backgroundPath = "board.png";
	
	protected Map map;
	protected PathFinder pathFinder;
	private ResourcesMenu resourcesMenu;
	private ControlPanel controlPanel;
	private EnemyController enemyController = new EnemyController();

	private MouseHandler mouse = new MouseHandler();
	public MovingUnit initialUnit2 = MovingUnit.getAlly(3, 3);
	public MovingUnit initialUnit1 = MovingUnit.getAlly(8, 3);

    public FieldScene(Game game) {
        super();
        this.addComponent(Camera.INSTANCE);
        Map map = new Map("map");
        this.map = map;
        this.pathFinder = new PathFinder(map);
        this.addComponent(map);
        
        this.addMovingUnit(initialUnit1);
        this.addMovingUnit(initialUnit2);

        this.addComponent(this.getMouse());
        
        addResourceMenu(new ResourcesMenu());
		//Comment this out for seeing control panel
		addControlPanel(game);
		
		if(Configuration.getBoolean("withEnemyIA?")) {
			this.addComponent(this.getEnemyController());
		}
		
		Builder.initialBuild(this, 4, 1);
		
		ResourcesBuilder.initialBuild(this);
		
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
	
	public double getTerrainWidth() {
		return getGame().getDisplayWidth();
	}
	
	public double getTerrainHeight() {
		return getGame().getDisplayHeight() - getResourcesMenu().getHeight() - getControlPanel().getHeight();
	}

}
