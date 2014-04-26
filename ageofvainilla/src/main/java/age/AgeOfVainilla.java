package age;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import resource.Resource;
import scenes.statics.StartScene;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.appearances.Appearance;
import components.boards.LivesBoard;
import components.boards.ScoreBoard;

public class AgeOfVainilla extends Game {

	private ScoreBoard scoreBoard = new ScoreBoard(15, 3, Color.white);
    private LivesBoard livesBoard = new LivesBoard(615, 3, Color.white);
	private Dimension dimension;
    private HashMap<String, Resource> resources;

	@Override
	protected void initializeResources() {
        this.resources  = new HashMap<String, Resource>();
        this.resources.put("pointer", Resource.getImage("extras/pointer.png"));
		dimension = new Dimension(800, 600);
	}

	@Override
	protected void setUpScenes() {
		this.setCurrentScene(new StartScene(this));
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Age of Vainilla";
	}

	public static void main(String[] args) throws Exception {
		new DesktopGameLauncher(new AgeOfVainilla()).launch();
	}

	public ScoreBoard getScoreBoard() {
		return scoreBoard;
	}

	public LivesBoard getLivesBoard() {
		return livesBoard;
	}

    public Appearance getResource(String key) {
        return this.resources.get(key);
    }

}
