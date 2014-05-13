package scenes;

import java.awt.Color;

import map.Map;
import map.Tile;
import map.path.PathFinder;

import com.uqbar.vainilla.Camera;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

import components.MouseHandler;
import components.units.Unit;

public class FieldScene extends GameScene {

	//private String backgroundPath = "board.png";
	
	protected Map map;
	protected PathFinder pathFinder;
	
    public FieldScene(Game game) {
        super();
        this.addComponent(Camera.INSTANCE);
        Map map = new Map(28,21,"map");
        this.map = map;;
        this.pathFinder = new PathFinder(map);
        this.addComponent(map);
        this.addComponent(new Unit(Color.BLACK, Tile.WIDTH, Tile.HEIGHT));
        this.addComponent(new MouseHandler());
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
