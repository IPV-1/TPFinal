package components.boards;

import java.awt.Color;

public class ResourceBoardFactory {
	
	private static int x;
	private static String label;
	
	
	public ResourceBoard build() {
		return new ResourceBoard(x, 1, Color.WHITE, label, 100);
	}

	public ResourceBoardFactory withX(int ex) {
		x = ex;
		return this;
	}

	public ResourceBoardFactory withLabel(String string) {
		label = string;
		return this;
	}
}
