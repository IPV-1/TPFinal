package components.boards;

import java.awt.Color;

public class ScoreBoard extends Board {

	public ScoreBoard(double x, double y, Color color) {		
		super(x, y, color);
		this.reset();
	}

	@Override
	public String getLabel() {
		return "Score";
	}

}
