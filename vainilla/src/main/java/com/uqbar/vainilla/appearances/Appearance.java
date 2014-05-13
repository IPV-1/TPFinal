package com.uqbar.vainilla.appearances;

import java.awt.Graphics2D;
import com.uqbar.vainilla.GameComponent;

public interface Appearance {

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public double getWidth();

	public double getHeight();

	public <T extends Appearance> T copy();
	
	// ****************************************************************
	// ** COLLISIONS
	// ****************************************************************
	public boolean collides(double x, double y, Appearance ap, double apX,
			double apY);

	public boolean collidesCircle(double x, double y, double cx, double cy,
			double cradius);

	public boolean collidesRect(double x, double y, double rx, double ry,
			double rwidth, double rheight);

	//TODO
//	public boolean collidesTriangle(double x, double y, double tx, double ty,
//			Triangle triangle);

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	public void update(double delta);

	public void render(GameComponent<?> component, Graphics2D graphics);

}