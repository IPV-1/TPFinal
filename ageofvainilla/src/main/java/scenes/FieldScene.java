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
import components.factors.Factor;
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

	private MouseHandler mouse = new MouseHandler();
	public MovingUnit initialUnit1 = new MovingUnit(Factor.BLUE, Color.BLACK, 200, 110);
	public MovingUnit initialUnit2 = new MovingUnit(Factor.RED, Color.DARK_GRAY, 110, 110);

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
}
