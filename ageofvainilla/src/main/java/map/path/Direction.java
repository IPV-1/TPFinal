package map.path;

public enum Direction {
	
	CON_X(false), PRO_X(true), CON_Y(false), PRO_Y(true);
	
	boolean adds;
	
	private Direction(boolean adds) {
		this.adds = adds;
	}
	
	public static Direction getX(int from, int to) {
		return from <= to ? PRO_X : CON_X;
	}
	
	public static Direction getY(int from, int to) {
		return from <= to ? PRO_Y : CON_Y;
	}
	
	public boolean reached(double pos, double to) {
		return this.adds() ? pos >= to : pos <= to;
	}
	
	public boolean adds() {
		return this.adds;
	}

}
