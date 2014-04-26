package com.uqbar.vainilla.colissions;

import static java.awt.geom.Point2D.distanceSq;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.space.Vector2D;

public class CollisionDetector {

	public static final CollisionDetector INSTANCE = new CollisionDetector();

	protected CollisionDetector() {
	}

	/**
	 * Determina si un c�rculo colisiona con un rect�ngulo.
	 * 
	 * @param cx
	 *            - Coordenada x de la esquina superior izquierda del c�rculo
	 *            (centroX - radio)
	 * @param cy
	 *            - Coordenada y de la esquina superior izquierda del c�rculo
	 *            (centroY - radio)
	 * @param cradius
	 *            - Radio del c�rculo
	 * @param rx
	 *            - Coordenada x de la esquina superior izquierda del rect�ngulo
	 * @param ry
	 *            - Coordenada y de la esquina superior izquierda del rect�ngulo
	 * @param rwidth
	 *            - Ancho del rect�ngulo
	 * @param rheight
	 *            - Alto del rect�ngulo
	 * @return Verdadero si colisionan, falso si no.
	 */
	public boolean collidesCircleAgainstRect(double cx, double cy,
			double cradius, double rx, double ry, double rwidth, double rheight) {
		Bounds circleBounds = new Bounds(cx, cy, cradius * 2, cradius * 2);
		Bounds rectBounds = new Bounds(rx, ry, rwidth, rheight);
		double rectLeft = rectBounds.getLeft();
		double rectRight = rectBounds.getRight();
		double rectTop = rectBounds.getTop();
		double rectBottom = rectBounds.getBottom();
		double extendedTargetTop = rectTop - cradius;
		double extendedTargetRight = rectRight + cradius;
		double extendedTargetBottom = rectBottom + cradius;
		double extendedTargetLeft = rectLeft - cradius;
		double radiusSq = cradius * cradius;
		double centerX = circleBounds.getCenterX();
		double centerY = circleBounds.getCenterY();

		if (centerX >= rectLeft) {
			if (centerX <= rectRight) {
				return centerY > extendedTargetTop
						&& centerY < extendedTargetBottom;
			}
			if (centerY >= rectTop) {
				if (centerY <= rectBottom) {
					return centerX < extendedTargetRight;
				}
				return distanceSq(centerX, centerY, rectRight, rectBottom) < radiusSq;
			}
			return distanceSq(centerX, centerY, rectRight, rectTop) < radiusSq;
		}

		if (centerY >= rectTop) {
			if (centerY <= rectBottom) {
				return centerX > extendedTargetLeft;
			}
			return distanceSq(centerX, centerY, rectLeft, rectBottom) < radiusSq;
		}

		return distanceSq(centerX, centerY, rectLeft, rectTop) < radiusSq;
	}

	public boolean collidesRectAgainstRect(double x1, double y1, double width1,
			double height1, double x2, double y2, double width2, double height2) {
		double right1 = x1 + width1;
		double right2 = x2 + width2;
		double bottom1 = y1 + height1;
		double bottom2 = y2 + height2;

		return (x1 <= x2 && x2 < right1 || x2 <= x1 && x1 < right2)
				&& (y1 <= y2 && y2 < bottom1 || y2 <= y1 && y1 < bottom2);
	}

	public boolean collidesCircleAgainstCircle(double x1, double y1,
			double radius1, double x2, double y2, double radius2) {
		double radiusSum = radius1 + radius2;
		double distanceSq = distanceSq(x1, y1, x2, y2);

		return distanceSq < radiusSum * radiusSum;
	}

	public boolean collidesPointAgainstTriangle(double x, double y,
			double vertexX1, double vertexY1, double vertexX2, double vertexY2,
			double vertexX3, double vertexY3) {

		double total = this.triangleArea(vertexX1, vertexY1, vertexX2,
				vertexY2, vertexX3, vertexY3);

		double t1 = this.triangleArea(x, y, vertexX2, vertexY2, vertexX3,
				vertexY3);
		double t2 = this.triangleArea(x, y, vertexX1, vertexY1, vertexX2,
				vertexY2);
		double t3 = this.triangleArea(x, y, vertexX1, vertexY1, vertexX3,
				vertexY3);

		return (t1 + t2 + t3) == total;
	}

	public double triangleArea(double vertexX1, double vertexY1,
			double vertexX2, double vertexY2, double vertexX3, double vertexY3) {
		double a = vertexX1 - vertexX3;
		double b = vertexY1 - vertexY3;
		double c = vertexX2 - vertexX3;
		double d = vertexY2 - vertexY3;

		return 0.5 * Math.abs((a * d) - (b * c));
	}

	public boolean collidesCircleAgainstTriangle(double cx, double cy,
			double cradius, double vertexX1, double vertexY1, double vertexX2,
			double vertexY2, double vertexX3, double vertexY3) {
		// TODO
		return (Boolean) null;
	}

	public boolean collidesRectAgainstTriangle(double rx, double ry,
			double rwidth, double rheight, double vertexX1, double vertexY1,
			double vertexX2, double vertexY2, double vertexX3, double vertexY3) {
		// TODO
		return (Boolean) null;
	}

	public boolean collidesTriangleAgainstTriangle(double t1X1, double t1Y1,
			double t1X2, double t1Y2, double t1X3, double t1Y3, double t2X1,
			double t2Y1, double t2X2, double t2Y2, double t2X3, double t2Y3) {
		// TODO
		return (Boolean) null;
	}

	public boolean collidesCircleAgainstArc(double cx, double cy,
			double cradius, double ax, double ay, double aradius,
			double asangle, double aangle) {
		boolean collides = false;
		if (CollisionDetector.INSTANCE.collidesCircleAgainstCircle(cx, cy,
				cradius, ax, ay, aradius)) {
			collides = aangle >= 360;
			if (!collides) {
				double module = aradius * 1.414214;
				Vector2D uvs = Vector2D.uVectorFromAngle(asangle);
				Vector2D uvm;
				if (aangle > 180) {
					uvm = Vector2D.uVectorFromAngle(asangle - (360 - aangle)
							/ 2);
				} else {
					uvm = Vector2D.uVectorFromAngle(asangle + aangle / 2);
				}
				Vector2D uve = Vector2D.uVectorFromAngle(asangle + aangle);
				uvs.multiply(module);
				uvm.multiply(module);
				uve.multiply(module);

				double px, py, sx, sy, mx, my, ex, ey;
				px = ax + aradius;
				py = ay + aradius;
				sx = px + uvs.getX();
				sy = py - uvs.getY();
				mx = px + uvm.getX();
				my = py - uvm.getY();
				ex = px + uve.getX();
				ey = py - uve.getY();

				collides = CollisionDetector.INSTANCE
						.collidesCircleAgainstTriangle(cx, cy, cradius, px, py,
								sx, sy, mx, my)
						|| CollisionDetector.INSTANCE
								.collidesCircleAgainstTriangle(cx, cy, cradius,
										px, py, mx, my, ex, ey);
				if (aangle < 180) {
					collides = !collides;
				}
			}
		}
		return collides;
	}

	public boolean collidesRectAgainstArc(double rx, double ry, double rwidth,
			double rheight, double ax, double ay, double aradius,
			double asangle, double aangle) {
		// TODO reflection?
		return (Boolean) null;
	}

	public boolean collidesTriangleAgainstArc(double t1X1, double t1Y1,
			double t1X2, double t1Y2, double t1X3, double t1Y3, double ax,
			double ay, double aradius, double asangle, double aangle) {
		// TODO reflection?
		return (Boolean) null;
	}

	public boolean collidesCircleAgainstRect(Circle c, Rectangle r) {
		return this.collidesCircleAgainstRect(c.getX(), c.getY(), c.getRadio(),
				r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}

	public boolean isHorizontalCollision(Bounds r1, Bounds r2) {
		double tamX = r2.getRight() - r1.getLeft();
		double tamY = r2.getBottom() - r1.getTop();

		if (r1.getLeft() <= r2.getLeft() && r2.getLeft() < r1.getRight()) {
			tamX = r1.getRight() - r2.getLeft();
		}

		if (r1.getTop() <= r2.getTop() && r2.getTop() < r1.getBottom()) {
			tamY = r1.getBottom() - r2.getTop();
		}

		return tamY < tamX;
	}

	public boolean collidesCircleAgainstRect(GameComponent<?> circle,
			GameComponent<?> rectangle) throws RuntimeException {
		if (circle.getAppearance().getClass() != com.uqbar.vainilla.appearances.Circle.class
				|| rectangle.getAppearance().getClass() != com.uqbar.vainilla.appearances.Rectangle.class) {
			throw new RuntimeException("Invalid parameter");
		}
		return this.collidesCircleAgainstRect(circle.getX(), circle.getY(),
				circle.getAppearance().getWidth() / 2, rectangle.getX(),
				rectangle.getY(), rectangle.getAppearance().getWidth(),
				rectangle.getAppearance().getHeight());
	}

}