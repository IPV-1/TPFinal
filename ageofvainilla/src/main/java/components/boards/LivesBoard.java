package components.boards;

import java.awt.Color;

public class LivesBoard extends Board {
	
	private final int MAX_LIVES = 3;
	
	public LivesBoard(double x, double y, Color color) {		
		super(x, y, color);
		this.reset();
	}

	public void die() {
		this.add(-1);
	}

	@Override
	public void reset() {
		this.setValue(MAX_LIVES);
	}

	public boolean lose() {
		return this.getValue() <= 0;
	}

	@Override
	public String getLabel() {
		return "Lives";
	}
	
}
