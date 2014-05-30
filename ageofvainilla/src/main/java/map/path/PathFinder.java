package map.path;

import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.space.ImmutablePoint;

import map.Map;

public class PathFinder {

	private static final int ORTHOGONAL_COST = 10;
	private static final int DIAGONAL_COST = 14;

	protected Node[][] nodes;
	protected Map map;

	protected List<Node> openList = new ArrayList<Node>();
	protected List<Node> closedList = new ArrayList<Node>();

	public PathFinder(Map map) {
		super();
		this.setMap(map);
	}

	protected void setMap(Map map) {
		this.map = map;
		this.setNodes(new Node[map.getyTiles()][map.getxTiles()]);
		for (int y = 0; y < this.getNodes().length; y++) {
			for (int x = 0; x < this.getNodes()[0].length; x++) {
				if (map.isWalkable(x, y)) {
					this.set(new Node(x, y), x, y);
				}
			}
		}
	}

	public void findPath(int xFrom, int yFrom, int xTo, int yTo, List<ImmutablePoint> path) {
		// TODO binary heaps
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
									node = this.get(x, y))) {
						int g = current.getG() + cost;
						if (!this.getOpenList().contains(node)) {
							this.getOpenList().add(node);
							node.setParent(current);
							node.set(g, this.getH(x, y, xTo, yTo));
						} else if (g < node.getG()) {
							node.setParent(current);
							node.setG(g);
							// TODO reorder f
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

}
