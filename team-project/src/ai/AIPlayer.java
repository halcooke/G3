package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import gamegraphics.Board;
import gamegraphics.GameObject;
import gamegraphics.MathHandler;
import gamegraphics.Obstacle;
import gamegraphics.Board;

public class AIPlayer extends gamegraphics.Character {

	boolean walking;
	private Stack<String> currentMoveset;
	private Stack<String> newMoveset;
	private Node[][] fullMap;
	private boolean FirstRun;
	private ArrayList<int[]> allObstacles;
	float targetAng = 0.0f;
	Image character = still;
	int frames = 0;
	String bump;
	private float currentPlayerX;
	private float currentPlayerY;
	private FutureTask<Stack<String>> future;
	private boolean isDone;

	public AIPlayer(float x, float y, int width, int height, int type, float playerX, float playerY) {
		super(x, y, width, height, type);
		this.currentPlayerX = playerX;
		this.currentPlayerY = playerY;
		this.FirstRun = true;
		this.isDone = true;
		// AStarSearch search = new AStarSearch();
		// Node[][] nodeMap = search.generateNodeMap(Board.BOARDWIDTH,
		// Board.BOARDHEIGHT);
		// this.fullMap = nodeMap;

		// TODO Auto-generated constructor stub
	}

	public AIPlayer(int type) {
		super(type);
		this.FirstRun = true;

		// AI();
	}

	/**
	 * Given a list of obstacles returns a list of all coordinates which lie
	 * within them
	 * 
	 * @param obstacles
	 *            A list of obstacles
	 */
	public void getObstacleCoordinates(Obstacle[] obstacles) {
		ArrayList<int[]> fullList = new ArrayList<int[]>();
		for (Obstacle obstacle : obstacles) {
			if (obstacle.getL()) {
				fullList.addAll(getGameObjectCoordinates(obstacle.getLeft()));
			}
			if (obstacle.getR()) {
				fullList.addAll(getGameObjectCoordinates(obstacle.getRight()));
			}
			if (obstacle.getD()) {
				fullList.addAll(getGameObjectCoordinates(obstacle.getBottom()));
			}
			if (obstacle.getU()) {
				fullList.addAll(getGameObjectCoordinates(obstacle.getTop()));
			}
		}
		System.out.println(fullList.size());
		this.allObstacles = fullList;
		/*
		 * for (int[] obstacle : fullList){ System.out.println("X : " +
		 * obstacle[0] + " Y : " + obstacle[1]); }
		 */
		System.out.println("Obstacle size : " + this.allObstacles.size());
	}

	/**
	 * Gets the coordinates of every node which resides within a given game
	 * object
	 * 
	 * @param wall
	 *            A GameObject for which the coordinates within must be found
	 * @return A list of coordinates within the given GameObject
	 */
	public ArrayList<int[]> getGameObjectCoordinates(GameObject wall) {
		ArrayList<int[]> fullCoordinates = new ArrayList<int[]>();
		float x = wall.getX();
		float y = wall.getY();
		//if ((x < Board.BOARDWIDTH) && (y < Board.BOARDHEIGHT)) {
			int height = wall.getHeight();
			int width = wall.getWidth();

			for (int i = (int) x; i < ((int) x + width); i++) {
				for (int j = (int) y; j < ((int) y + height); j++) {
					int[] coord = new int[2];
					coord[0] = i - (width/2);
					coord[1] = j - (height/2);
					fullCoordinates.add(coord);
				}
			}
			return fullCoordinates;
		/*
		} else {
			return fullCoordinates;
		}
		*/

	}

	// TODO Auto-generated constructor stub

	public void AI(float playerX, float playerY) {
		// Behaviours
		// Need to implement behaviours

		follow(playerX, playerY);
		
		
		//Add in a dodge - if the ai is within x of a projectile work out direction and move 
	}

	/**
	 * Sets the obstacles on the map using the current set of obstacle
	 * coordinates
	 * 
	 * @param nodeMap
	 *            The current map used
	 * @return The map with the obstacles nodes set
	 */
	public Node[][] setObstacles(Node[][] nodeMap) {
		int count = 0;
		while (count < this.allObstacles.size()) {
			int[] coords = allObstacles.get(count);
			if ((coords[1] < Board.BOARDHEIGHT) && (coords[0] < Board.BOARDWIDTH)) {
				nodeMap[coords[0]][coords[1]].setObstacle(true);
				nodeMap[coords[0]][coords[1]].setHeuristic(10000);
			}
			count++;
		}
		return nodeMap;
	}

	/**
	 * Creates a future to be used to background path find and return a move set
	 * 
	 * @param aiX
	 *            The AI's predicted x
	 * @param aiY
	 *            The Ai's predicted y
	 * @return A new move set between the predicted x and y and the players x
	 *         and y
	 */
	public Stack<String> moveSetUpdate(int aiX, int aiY) {
		final int playerX = (int) this.currentPlayerX;
		final int playerY = (int) this.currentPlayerY;
		UpdateMoveset newMoveset = new UpdateMoveset((int) playerX, (int) playerY, this.fullMap, aiX, aiY);
		Stack<String> updatedMoveset = new Stack<String>();
		// Try to make this accessible from move and cut the current movelist
		// down to 10 or some shit

		if (isDone) {
			future = new FutureTask<Stack<String>>(newMoveset);
			future.run();
			isDone = false;
		}
		try {
			updatedMoveset = future.get();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		if (updatedMoveset != null) {
			isDone = true;
		} else {
			isDone = false;
		}
		return updatedMoveset;
	}

	/**
	 * Follows the enemy player by making the AI player move as well as
	 * re-searching when the player moves far enough that previous search would
	 * be inaccurate
	 * 
	 * @param playerX
	 *            The players x coordinate
	 * @param playerY
	 *            The players y coordinate
	 */
	public void follow(float playerX, float playerY) {

		if (this.FirstRun == true) {

			AStarSearch newSearch = new AStarSearch();
			Node[][] nodeMap = newSearch.generateNodeMap(Board.BOARDWIDTH, Board.BOARDHEIGHT);
			 nodeMap = setObstacles(nodeMap);
			Node start = new Node((int) this.x, (int) this.y);
			Node end = new Node(208,163);
			this.currentMoveset = newSearch.AStar(nodeMap, start, end);
			this.FirstRun = false;

		}

		if (((Math.abs(playerX - currentPlayerX) > 40) || (Math.abs(playerY - currentPlayerY) > 40))) {
			if (playerX < Board.BOARDWIDTH) {
				this.currentPlayerX = playerX;
			}
			if (playerY < Board.BOARDHEIGHT) {
				this.currentPlayerY = playerY;
			}
			if (isDone) {
				System.out.println("working");
				int[] aiXY = futureCoordinates((int) this.x, (int) this.y, 30);
				isDone = false;
				moveSetUpdate(aiXY[0], aiXY[1]);
			}
		}
		if (this.currentMoveset.isEmpty()) {
			AStarSearch newSearch = new AStarSearch();
			Node[][] nodeMap = newSearch.generateNodeMap(Board.BOARDWIDTH, Board.BOARDHEIGHT);
			nodeMap = setObstacles(nodeMap);
			Node start = new Node((int) this.x, (int) this.y);
			Node end = new Node((int) this.currentPlayerX, (int) this.currentPlayerY);
			this.currentMoveset = newSearch.AStar(nodeMap, start, end);
			
		} else {
			String move = this.currentMoveset.pop();
			if (move.equals("up")) {
				y -= 1f;
			}
			if (move.equals("down")) {
				y += 1f;
			}
			if (move.equals("left")) {
				x -= 1f;
			}
			if (move.equals("right")) {
				x += 1f;
			}
			if (move.equals("rightup")) {
				x += 1f;
				y -= 1f;
			}
			if (move.equals("leftup")) {
				x -= 1f;
				y -= 1f;
			}
			if (move.equals("rightdown")) {
				x += 1f;
				y += 1f;
			}
			if (move.equals("leftdown")) {
				x -= 1f;
				y += 1f;
			}
		}

	}

	/**
	 * Culls the current move set down to a certain length and gets the AI's
	 * positions after the move set has been completed
	 * 
	 * To be used in conjunction with threading to increase performance
	 * 
	 * @param aiX
	 *            The current x coordinate of the AI
	 * @param aiY
	 *            The current y coordinate of the AI
	 * @param cullLength
	 *            The length of the current move set you wish to keep
	 * @return The predicted position of the AI after the remaining move set is
	 *         completed
	 */
	public int[] futureCoordinates(int aiX, int aiY, int cullLength) {
		Stack<String> newStack = new Stack<String>();
		if (cullLength > this.currentMoveset.size()) {
			cullLength = this.currentMoveset.size();
		}
		for (int i = 0; i < cullLength; i++) {
			String move = this.currentMoveset.pop();
			newStack.push(move);

			if (move.equals("up")) {
				aiY -= 1f;
			}
			if (move.equals("down")) {
				aiY += 1f;
			}
			if (move.equals("left")) {
				aiX -= 1f;
			}
			if (move.equals("right")) {
				aiX += 1f;
			}
			if (move.equals("rightup")) {
				aiX += 1f;
				aiY -= 1f;
			}
			if (move.equals("leftup")) {
				aiX -= 1f;
				aiY -= 1f;
			}
			if (move.equals("rightdown")) {
				aiX += 1f;
				aiY += 1f;
			}
			if (move.equals("leftdown")) {
				aiX -= 1f;
				aiY += 1f;
			}
		}
		Stack<String> finalStack = new Stack<String>();
		for (int j = 0; j < newStack.size(); j++) {
			finalStack.push(newStack.pop());
		}
		this.currentMoveset = finalStack;
		int[] xy = new int[2];
		xy[0] = aiX;
		xy[1] = aiY;

		return xy;

	}

	public void setBump(String bump) {
		this.bump = bump;

	}

	@Override
	public void collidedWith(GameObject other) {
		// this.sethP(gethP() - 2);

	}

	public void setInitialDone(boolean bool) {
		this.isDone = bool;
		
	}

	@Override
	public void draw(Graphics g) {
		MathHandler hand = new MathHandler();
		// g.drawImage(bullet.getScaledCopy((int) (width*0.0095), (int)
		// (height*0.0095)), width/2, height/2);
		targetAng = hand.getTargetAngle(x, y, this.currentPlayerX, this.currentPlayerY);

		g.rotate(x, y, targetAng);
		g.drawImage(character.getScaledCopy(width, height), (int) (x - width / 2), (int) (y - height / 2));
		g.rotate(x, y, -targetAng);
	}
}