package scenes;

import scenes.statics.LoseScene;
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
	
	private int allies = 0;

    public FieldScene(Game game) {
        super();
        this.setGame(game);
        
        this.addComponent(Camera.INSTANCE);
        Map map = new Map();
        this.map = map;
        this.pathFinder = new PathFinder(map);
        this.addComponent(map);
        
        
        this.addMovingUnit(initialUnit1);
        this.addMovingUnit(initialUnit2);

        this.addComponent(this.getMouse());
        
		
		if(Configuration.getBoolean("withEnemyIA?")) {
			this.addComponent(this.getEnemyController());
		}
		
		addResourceMenu(new ResourcesMenu());
		//Comment this out for seeing control panel
		addControlPanel();
		
		Builder.initialBuild(this, 4, 1);
		
		ResourcesBuilder.initialBuild(this);
		
		Camera.INSTANCE.setLimits(this.getTerrainUpperX(), this.getTerrainUpperY(),
				this.getTerrainLowerX(), this.getTerrainLowerY(), 
				this.getGame().getDisplayWidth(), this.getGame().getDisplayHeight());
    }


	public void addResourceMenu(ResourcesMenu menu){
		setResourcesMenu(menu);
		addComponent(menu);
	}


	private void addControlPanel() {
		PanelFactory panelFactory = new PanelFactory(new PanelBuilder());
		ControlPanel panel = panelFactory.downPanelFullWith();
		this.setControlPanel(panel);
		addComponent(panel);
	}

	public void addMovingUnit(MovingUnit unit) {
		this.addComponent(unit);
		
		Coord tileCoord = new Coord(unit.getX(), unit.getY()).getTile(Tile.WIDTH);
		this.getMap().occupy(unit, (int)tileCoord.getX(), (int)tileCoord.getY());
		
		if(unit.getFactor().isAlly()) {
			this.increaseAlly();
		}
		
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
	
	public int getAllies() {
		return allies;
	}
	
	public void setAllies(int allies) {
		this.allies = allies;
	}
	
	public void increaseAlly() {
		this.setAllies(this.getAllies() + 1);
	}
	
	public void decreaseAlly() {
		this.setAllies(this.getAllies() - 1);
		if(this.getAllies() == 0) {
			this.lose();
		}
	}
	
	public void lose() {
		this.getGame().setCurrentScene(new LoseScene(this.getGame()));
	}
	    
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
	
	public double getTerrainLowerX() {
		return 0;
	}
	
	public double getTerrainUpperX() {
		return this.getMap().getTileWidth() * Tile.WIDTH;
	}
	
	public double getTerrainLowerY() {
		return -getResourcesMenu().getHeight();
	}
	
	public double getTerrainUpperY() {
		return this.getMap().getTileHeight() * Tile.HEIGHT + getControlPanel().getHeight();
	}

}
