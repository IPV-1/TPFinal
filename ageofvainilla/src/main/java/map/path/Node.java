package map.path;

import com.uqbar.vainilla.space.ImmutablePoint;

public class Node {

	public final ImmutablePoint POINT;

	/** movement cost from the starting node to this one */
	protected int g;
	/** estimated movement cost from this node to destination one */
	protected int h;
	/** g + h */
	protected int f;
	protected Node parent;

	public Node(int x, int y) {
		this.POINT = new ImmutablePoint(x, y);
		this.g = 0;
		this.h = 0;
		this.f = 0;
	}

	public void clear() {
		this.g = 0;
		this.setParent(null);
	}

	public boolean hasParent() {
		return this.getParent() != null;
	}

	public void set(int g, int h) {
		this.setH(h);
		this.setG(g);
	}

	public void setG(int g) {
		this.g = g;
		this.setF(g + this.getH());
	}

	@Override
	public String toString() {
		String parent = this.getParent() == null ? "none"
				: this.getParent().POINT.toString();
		return this.POINT + " -" + " G:" + this.getG() + " H:" + this.getH()
				+ " F:" + this.getF() + " parent: " + parent;
	}

	public int getG() {
		return g;
	}

	public int getH() {
		return h;
	}

	protected void setH(int h) {
		this.h = h;
	}

	public int getF() {
		return f;
	}

	protected void setF(int f) {
		this.f = f;
	}

	public Node getParent() {
		return this.parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getX() {
		return this.POINT.x;
	}

	public int getY() {
		return this.POINT.y;
	}

}
