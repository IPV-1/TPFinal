package utils;

import java.awt.Color;

public enum ColorCSS {

	BROWN(new Color(0xA52A2A)),
	GREEN(Color.GREEN),
	YELLOW(Color.YELLOW),
	BLUE(Color.BLUE);
	
	private Color color;

	private ColorCSS(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public Integer getRGB() {
		return this.getColor().getRGB();
	}
	
}
