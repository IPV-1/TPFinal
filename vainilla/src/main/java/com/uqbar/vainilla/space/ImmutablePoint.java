package com.uqbar.vainilla.space;

public class ImmutablePoint {

	public final int x;
	public final int y;

	public ImmutablePoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
