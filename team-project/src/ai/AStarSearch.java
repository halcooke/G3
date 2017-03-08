package ai;

import java.util.*;

public class AStarSearch {

	public AStarSearch() {

	}

	public Node[][] generateNodeMap(int x, int y) {
		Node[][] nodeMap = new Node[x][y];
		for (int i = 0; i < x; i++) { // x
			for (int j = 0; j < y; j++) { // y
				Node node = new Node(i, j);
				nodeMap[i][j] = node;
			}
		}

		return nodeMap;
	}

	public Stack<String> AStar(Node[][] nodeMap, Node start, Node end) {

		ArrayList<Node> nodes = new ArrayList<Node>();
		Stack<String> solutionDirections = new Stack<String>();
		ArrayList<Node> frontier = new ArrayList<Node>();
		ArrayList<Node> explored = new ArrayList<Node>();

		start = nodeMap[start.getX()][start.getY()];
		end = nodeMap[end.getX()][end.getY()];
		start.setStart(true);
		end.setEnd(true);

		nodeMap = setNeighbours(nodeMap);

		// Select the neighbours for the start node
		for (Node node : start.getNeighbours()) {
			node.setCurrentDistance(1);
			node.calculateHeuristic(end);
			node.calculateTotalCost();
		}

		// Add start node to explored and set its values
		explored.add(start);
		start.setExplored(true);
		start.setHasParent(true);
		
		System.out.println(end.getX() +" Y :"+ end.getY());

		// Add the neighbours of the start node to the frontier
		frontier = addNeighbours(nodeMap, start, explored, frontier);

		// Set the lowest cost to something high
		int lowestCost = 5000;

		// Boolean to be used for the search
		boolean done = false;
		
		//printMap(nodeMap, true);
		
		// Main Code Block
		while (!done) {

			// Sets the neighbours - check if necessary
			// setNeighbours(nodeMap);

			// Sets the F values for each node in the frontier
			for (Node node : frontier) {
				// Check if the current node is the start or end and if its
				// distance has already been set
				if ((node.getStart() == false) && (node.getEnd() == false && (node.getCurrentDistance() == 0))) {
					Node node2 = node.getParent();
					node.setCurrentDistance(node2.getCurrentDistance() + 1);
					if (node.getHeuristic() == 0) {
						node.calculateHeuristic(end);
					}
					node.calculateTotalCost();
				}
			}

			// Initialises the current node
			Node current = null;

			// Sets current to the lowest F value
			for (Node node : frontier) {
				if (current == null || node.getTotalCost() < current.getTotalCost()) {
					current = node;
				}
			}
			
			
			// Checks if the current node is the solution node

			if (current.equals(end)) {
				done = true;

				while (current.getStart() != true) {
					Node parent = current.getParent();
					current.setDirection(setDirection(current.getX(), current.getY(), parent.getX(), parent.getY()));

					// nodes.add(current);
					solutionDirections.push(current.getDirection());
					current.setSolutionNode(true);
					current = current.getParent();
				}
				//printMap(nodeMap, true);
				done = true;
				break;
			}

			// Gets the lowest cost of the neighbours in the frontier
			for (Node Neighbour : frontier) {
				if (Neighbour.getTotalCost() < lowestCost) {
					lowestCost = Neighbour.getTotalCost();
				}
			}

			// Finds the node in the frontier with the lowest cost and sets it
			// as the new choice of node

			for (Node Neighbour : frontier) {
				if (Neighbour.getTotalCost() == lowestCost) {
					frontier.remove(current);
					current.setExplored(true);
					Node node = Neighbour;
					current = node;
					explored.add(node);
					frontier = addNeighbours(nodeMap, current, explored, frontier);

					break;
				}
			}

			lowestCost = 5000;

		}

		return solutionDirections;

	}

	private Node[][] setNeighbours(Node[][] map) {
		for (int i = 0; i < map[0].length; i++) { // y
			for (int j = 0; j < map.length; j++) { // x
				Node node = map[j][i];
				ArrayList<Node> Neighbours = new ArrayList<Node>();
				// South
				if ((i + 1) < map[0].length) {
					Neighbours.add(map[j][i + 1]);
				}
				// North
				if ((i - 1) >= 0) {
					Neighbours.add(map[j][i - 1]);
				}
				// East
				if ((j + 1) < map.length) {
					Neighbours.add(map[j + 1][i]);

				}
				// West
				if ((j - 1) >= 0) {
					Neighbours.add(map[j - 1][i]);
				}
				// South-East
				if (((i + 1) < map[0].length) && ((j + 1) < map.length)) {
					Neighbours.add(map[j + 1][i + 1]);
				}
				// South-West
				if (((i + 1) < map[0].length) && ((j - 1) >= 0)) {
					Neighbours.add(map[j - 1][i + 1]);
				}
				// North-East
				if (((i - 1) >= 0) && ((j + 1) < map.length)) {
					Neighbours.add(map[j + 1][i - 1]);
				}
				// North-West
				if (((j - 1) >= 0) && ((i - 1) >= 0)) {
					Neighbours.add(map[j - 1][i - 1]);
				}
				// TESTING CODE
				// System.out.println(Neighbours);

				node.setNeighbours(Neighbours);
			}
		}
		return map;
	}

	// Check if explored here is really necessary
	private ArrayList<Node> addNeighbours(Node[][] map, Node node, ArrayList<Node> explored, ArrayList<Node> frontier) {
		ArrayList<Node> Neighbours = new ArrayList<Node>();
		for (int i = 0; i < node.getNeighbours().size(); i++) {
			Neighbours = node.getNeighbours();
			if (Neighbours.get(i).getHasParent() == false) {
				Neighbours.get(i).setParent(node);
				frontier.add(Neighbours.get(i));
			}
		}
		return frontier;
	}

	private String setDirection(int nodex, int nodey, int parentx, int parenty) {
		String direction = "";
		if ((parentx == nodex) && (nodey == parenty + 1)) {
			direction = "down";
		} else if ((parentx == nodex) && (nodey == parenty - 1)) {
			direction = "up";
		} else if ((nodey == parenty) && (nodex == parentx + 1)) {
			direction = "right";
		} else if ((nodey == parenty) && (nodex == parentx - 1)) {
			direction = "left";
		} else if ((nodey == parenty - 1) && (nodex == parentx - 1)) {
			direction = "leftup";
		} else if ((nodey == parenty - 1) && (nodex == parentx + 1)) {
			direction = "rightup";
		} else if ((nodex == parentx - 1) && (nodey == parenty + 1)) {
			direction = "leftdown";
		} else if ((nodex == parentx + 1) && (nodey == parenty + 1)) {
			direction = "rightdown";
		}

		return direction;
	}

	public void printMap(Node[][] nodeMap, boolean solution) {
		if (solution != true) {
			for (int j = 0; j < nodeMap[0].length; j++) {
				for (int i = 0; i < nodeMap.length; i++) {
					if (nodeMap[i][j].getObstacle() == true) {
						System.out.println("O");
					} else if (nodeMap[i][j].getEnd() == false && nodeMap[i][j].getStart() == false
							&& nodeMap[i][j].getHasParent() == false && nodeMap[i][j].getExplored() == false) {
						System.out.print("X");
					} else if (nodeMap[i][j].getStart() == true) {
						System.out.print("A");
					} else if (nodeMap[i][j].getEnd() == true) {
						System.out.print("B");
					} else if (nodeMap[i][j].getExplored() == true) {
						System.out.print("J");
					} else if (nodeMap[i][j].getHasParent() == true && nodeMap[i][j].getExplored() == false) {
						System.out.print("C");
					}

				}
				System.out.println();
			}
		} else {
			for (int j = 0; j < nodeMap[0].length; j++) {
				for (int i = 0; i < nodeMap.length; i++) {
					if (nodeMap[i][j].getObstacle()) {
						System.out.print("#"); // obstacle
					} else if (!nodeMap[i][j].getEnd() && !nodeMap[i][j].getStart()
							&& !nodeMap[i][j].getSolutionNode()) {
						System.out.print("  ");
					} else if (nodeMap[i][j].getStart()) {
						System.out.print("A ");
					} else if (nodeMap[i][j].getEnd()) {
						System.out.print("B ");
					} else if (nodeMap[i][j].getSolutionNode()) {
						System.out.print("C ");
					}
				}
				System.out.println();
			}

		}

	}

}
