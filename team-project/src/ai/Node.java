package ai;
import java.util.ArrayList;
public class Node{
	private int heuristic;
	private int currentDistance;
	private int totalCost;
	private int xCord;
	private int yCord;
	private String direction;
	private boolean explored;
	private boolean start;
	private boolean end;
	private boolean hasParent;
	private boolean solutionNode;
	private boolean obstacle;
	private Node parent;
	private ArrayList<Node> Neighbours;
	ArrayList<Node> touching = new ArrayList<Node>();
	public Node(int xCord, int yCord) {
		this.xCord = xCord;
		this.yCord = yCord;
		this.start = false;
		this.end = false;
		this.explored = false;
		this.totalCost = 0;
		this.hasParent = false;
	}
	
	public void setObstacle(boolean obstacle){
		this.obstacle = obstacle;
	}
	
	public boolean getObstacle(){
		return this.obstacle;
	}
	
	public void setDirection(String direction){
		this.direction = direction;
	}
	
	public String getDirection(){
		return this.direction;
	}
	
	public int getTotalCost(){
		return this.totalCost;
	}
	
	public void nodeSetX(int x){
		this.xCord = x;
	}
	
	public void nodeSetY(int y){
		this.yCord = y;
	}
	
	public void setCurrentDistance(int Distance){
		this.currentDistance = Distance;
	}
	
	public void setSolutionNode(boolean p){
		this.solutionNode = p;
	}
	
	public boolean getSolutionNode(){
		return this.solutionNode;
	}
	
	public int getCurrentDistance(){
		return this.currentDistance;
	}
	
	public void setTotalCost(int totalCost){
		this.totalCost = totalCost;
	}	
	
	public void calculateTotalCost(){
		this.totalCost = this.heuristic + this.currentDistance;
	}
	public int getHeuristic(){
		return this.heuristic;
	}
	
	public void setNeighbours(ArrayList<Node> Neighbours){
		this.Neighbours = Neighbours;
	}
	
	public ArrayList<Node> getNeighbours(){
		return this.Neighbours;
	}
	
	
	public void setParent(Node node){
		this.hasParent = true;
		this.parent = node;
	}
	
	public boolean getHasParent(){
		return this.hasParent;
	}
	
	public void setHasParent(boolean p){
		this.hasParent = p;
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	public void setHeuristic(int h){
		this.heuristic = h;
	}
	
	public void setStart(boolean start) {
		this.start = start;
	}
	public boolean getStart() {
		return this.start;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	public boolean getEnd() {
		return this.end;
	}
	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	public boolean getExplored() {
		return this.explored;
	}
	
	public void calculateHeuristic(Node endNode){
		int h = Math.abs(endNode.getX() - this.xCord) + Math.abs(endNode.getY() - this.yCord);
		this.heuristic = h;
	}
	public int getX() {
		return this.xCord;
	}
	public int getY() {
		return this.yCord;
	}
	
	public String toString() {
		return this.xCord + " " + this.yCord + " " + this.totalCost;
	}
}