package ai;
import java.util.Stack;
import java.util.concurrent.Callable;
public class UpdateMoveset implements Callable<Stack<String>>{
	
	private Stack<String> moveset;
	private int playerX;
	private int playerY;
	private Node[][] nodeMap;
	private int aiX;
	private int aiY;
	
	public UpdateMoveset(int playerX, int playerY, Node[][] nodeMap, int aiX, int aiY){
		this.playerX = playerX;
		this.playerY = playerY;
		this.nodeMap = nodeMap;
		this.aiX = aiX;
		this.aiY = aiY;
	}
	@Override
	public Stack<String> call() throws Exception {
		AStarSearch search = new AStarSearch();
		Node start = new Node(aiX, aiY);
		Node end = new Node(playerX, playerY);
		moveset = search.AStar(nodeMap, start, end);
		return moveset;
	}
	
}