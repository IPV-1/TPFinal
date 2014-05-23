package components.boards;

import java.awt.Color;

public class ResourceBoard extends Board {
	
	String label;

	public ResourceBoard(double x, double y, Color color, String label) {
		super(x, y, color);
		this.label = label;
	}

	public ResourceBoard(int x, int y, Color color, String label, int initial) {
		this(x, y, color, label);
		this.setValue(initial);
	}

	@Override
	public String getTitle() {
		return this.label;
	}
	
	@Override
	public int getFontSize() {
		return 20;
	}
	

}
