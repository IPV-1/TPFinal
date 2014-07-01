package map.path;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.space.ImmutablePoint;
import components.factors.Factor;
import components.units.Unit;

import map.Map;

public class PathFinder {

	public static final int ORTHOGONAL_COST = 10;
	private static final int DIAGONAL_COST = 14;

	protected Node[][] nodes;
	protected Map map;

	protected List<Node> openList = new ArrayList<Node>();
	protected List<Node> closedList = new ArrayList<Node>();
	
	protected int limit;

	public PathFinder(Map map) {
		super();
		this.setMap(map);
		this.setLimit(Math.max(map.getTileWidth(), map.getTileHeight()));
	}

	protected void setMap(Map map) {
		this.map = map;
		this.setNodes(new Node[map.getTileHeight()][map.getTileWidth()]);
		for (int y = 0; y < this.getNodes().length; y++) {
			for (int x = 0; x < this.getNodes()[0].length; x++) {
				if (map.isWalkable(x, y)) {
					this.set(new Node(x, y), x, y);
				}
			}
		}
	}

	public void findPath(int xFrom, int yFrom, int xTo, int yTo,
			List<ImmutablePoint> path) {
		// Calculating x,y to go
		if (xFrom != xTo || yFrom != yTo) {
			int xT = xTo, yT = yTo;
			if (this.getMap().isBlocked(xT, yT)) {
				Point p = this.closestTo(xTo, yTo);
				if(p != null) {
					xT = p.x;
					yT = p.y;
				}
			}
			if (this.getH(xT, yT, xTo, yTo) < this.getH(xFrom, yFrom, xTo, yTo)) {
				this.findPathToCalculated(xFrom, yFrom, xT, yT, path);
			}
		}
	}
	
	public Point closestTo(Point p) {
		return this.closestTo(p.x, p.y);
	}

	public Point closestTo(int x, int y) {
		return this.closestTo(null, x, y);
	}
	
	public Point closestTo(Factor factor, Point p) {
		return this.closestTo(factor, p.x, p.y);
	}

	public Point closestTo(Factor factor, int xTo, int yTo) {
		if(this.tileFound(factor, xTo, yTo)) {
			return new Point(xTo, yTo);
		}
		int xT = xTo, yT = yTo;
		int y = yT - 1;
		int x = xT;
		search: for (int m = 0;; m++) {
			if(m > this.getLimit()) {
				return null;
			}
			y = yT - (m + 1);
			x = xT - m;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j <= m * 2; j++) {
					if (this.tileFound(factor, x, y)) {
						break search;
					}
					x++;
				}
				y = yT + (m + 1);
				x = xT - m;
			}
			x = xT - (m + 1);
			y = yT - (m + 1);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j <= (m + 1) * 2; j++) {
					if (this.tileFound(factor, x, y)) {
						break search;
					}
					y++;
				}
				x = xT + (m + 1);
				y = yT - (m + 1);
			}
		}
		return new Point(x, y);
	}
	
	protected boolean tileFound(Factor factor, int xTo, int yTo) {
		boolean found = false;
		if(this.insideMap(xTo, yTo)) {
			if(factor == null && !this.getMap().isBlocked(xTo, yTo)) {
				found = true;
			} else {
				Unit u = this.getMap().get(xTo, yTo).getOcuppant();
				found = u != null && u.getFactor().equals(factor);
			}
		}
		return found;
	}

	protected void findPathToCalculated(int xFrom, int yFrom, int xTo, int yTo,
			List<ImmutablePoint> path) {
		Node current = this.get(xFrom, yFrom);
		current.clear();
		this.getOpenList().clear();
		this.getClosedList().clear();
		this.getOpenList().add(current);

		boolean found = false;
		while (!this.getOpenList().isEmpty()) {
			current = this.lowest(this.getOpenList());
			this.getOpenList().remove(current);
			this.getClosedList().add(current);
			if (current.getX() == xTo && current.getY() == yTo) {
				found = true;
				break;
			}
			Node node;
			int cost = DIAGONAL_COST;
			for (int y = current.getY() - 1; y <= current.getY() + 1; y++) {
				for (int x = current.getX() - 1; x <= current.getX() + 1; x++) {
					if (this.insideMap(x, y)
							&& !this.getMap().isBlocked(x, y)
							&& !this.getClosedList().contains(
									node = this.get(x, y))
							&& !(cost == DIAGONAL_COST && !(this.notBlocked(x,
									current.getY()) && this.notBlocked(
									current.getX(), y)))) {
						int g = current.getG() + cost;
						if (!this.getOpenList().contains(node)) {
							this.getOpenList().add(node);
							node.setParent(current);
							node.set(g, this.getH(x, y, xTo, yTo));
						} else if (g < node.getG()) {
							node.setParent(current);
							node.setG(g);
						}
					}
					cost = cost == DIAGONAL_COST ? ORTHOGONAL_COST
							: DIAGONAL_COST;
				}
			}
		}
		if (found) {
			while (current.hasParent()) {
				path.add(current.POINT);
				current = current.getParent();
			}
			path.add(current.POINT);
		}
	}

	public boolean notBlocked(int x, int y) {
		return this.insideMap(x, y) && !this.getMap().isBlocked(x, y);
	}

	protected Node lowest(List<Node> nodes) {
		Node node = nodes.get(0);
		for (int i = 1; i < nodes.size(); i++) {
			if (node.getF() > nodes.get(i).getF()) {
				node = nodes.get(i);
			}
		}
		return node;
	}

	/**
	 * Manhattan method
	 */
	public int getH(int xFrom, int yFrom, int xTo, int yTo) {
		return ORTHOGONAL_COST
				* (Math.abs(xFrom - xTo) + Math.abs(yFrom - yTo));
	}
	
	public int tileDistance(int xFrom, int yFrom, int xTo, int yTo) {
		return this.getH(xFrom, yFrom, xTo, yTo) / PathFinder.ORTHOGONAL_COST;
	}

	protected boolean insideMap(int x, int y) {
		return x >= 0 && y >= 0 && y < this.getNodes().length
				&& x < this.getNodes()[0].length;
	}

	protected Node get(int x, int y) {
		return this.getNodes()[y][x];
	}

	protected void set(Node node, int x, int y) {
		this.getNodes()[y][x] = node;
	}

	protected Node[][] getNodes() {
		return this.nodes;
	}

	protected void setNodes(Node[][] nodes) {
		this.nodes = nodes;
	}

	public Map getMap() {
		return this.map;
	}

	protected List<Node> getOpenList() {
		return openList;
	}

	protected List<Node> getClosedList() {
		return closedList;
	}

	protected int getLimit() {
		return limit;
	}

	protected void setLimit(int limit) {
		this.limit = limit;
	}

}
