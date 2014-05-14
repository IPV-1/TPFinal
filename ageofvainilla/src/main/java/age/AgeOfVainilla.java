package age;

import java.awt.Color;
import java.awt.Dimension;

import scenes.statics.StartScene;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import components.boards.LivesBoard;
import components.boards.ScoreBoard;

import config.Configuration;

public class AgeOfVainilla extends Game {

	private ScoreBoard scoreBoard = new ScoreBoard(15, 3, Color.white);
    private LivesBoard livesBoard = new LivesBoard(615, 3, Color.white);
    
    private final String CONFIG_FILE = "application.xml";
    
    public AgeOfVainilla() {
    	super();
    	Configuration.LOAD(CONFIG_FILE);
	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension((int) Configuration.getDisplayWidth(),
			(int) Configuration.getDisplayHeight());
	}

	@Override
	protected void setUpScenes() {
		this.setCurrentScene(new StartScene(this));
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

	@Override
	protected void initializeResources() {
	}

}
