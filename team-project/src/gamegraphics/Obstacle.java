package gamegraphics;

import org.newdawn.slick.Graphics;

/**
 * Represents up to four GameObjects in a box structure, which will make up the maze shape, with positions which represent the location on the board
 * @author jxs989
 *
 */
public class Obstacle
{ 

	static int boardWidth = Board.BOARDWIDTH;
	static int boardHeight = Board.BOARDHEIGHT;
	static int width = Board.MAZEBLOCKWIDTH;
	static int height = Board.MAZEBLOCKHEIGHT;
	static int columns = Board.MAZECOLUMNS;
	static int rows = Board.MAZEROWS;
	static int mainX;
	static int mainY;
	static int gridWidth = Board.WALLWIDTH;
	boolean l;
	boolean u;
	boolean r;
	boolean d;
	GameObject left;
	GameObject top;
	GameObject right;
	GameObject bottom;
	
String imagePath = "images/Backgrounds/backgrounddetailed2.png";
	
	
	public Obstacle(int pos, boolean l, boolean u, boolean r, boolean d)
	{
		width = boardWidth / columns;
		height = boardHeight / rows;
		mainX = (((pos - 1) % columns) * width) + Board.BOARDSTARTX; 
		mainY = (((pos - 1) / columns)  * height) + Board.BOARDSTARTY;
		this.l = l;
		this.u = u;
		this.r = r;
		this.d = d;
		
		
		if(l){
			left = new GameObject(mainX, (mainY + height/2), gridWidth, height + gridWidth);
			//System.out.println("mainY = " + mainY);
			//System.out.println("left(" + mainX + "," +(mainY + gridWidth)+ "," + gridWidth + "," + (-(width - 2*gridWidth)) + ")" );
		}
		if(u){
			top = new GameObject((mainX + width/2), mainY, width + gridWidth, gridWidth);
		}
		if(r){
			right = new GameObject((mainX + width) , (mainY + height/2), gridWidth, height + gridWidth);
		}
		if(d){
			bottom = new GameObject((mainX + width/2) , (mainY + height), width + gridWidth, gridWidth);
		}
		
		setImages(imagePath);
		
	}
	
	private void setImages(String path){
		try{
			if(l){
				left.setImage(path);
			}
			if(u){
				top.setImage(path);
			}
			if(r){
				right.setImage(path);
			}
			if(d){
				bottom.setImage(path);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g){
		if(l){
			left.draw(g);
		}
		if(u){
			top.draw(g);
		}
		if(r){
			right.draw(g);
		}
		if(d){
			bottom.draw(g);
		}
	}
	
	public boolean getL(){
		return l;
	}
	public boolean getU(){
		return u;
	}
	public boolean getR(){
		return r;
	}
	public boolean getD(){
		return d;
	}
	
	public GameObject getLeft(){
		return left;
	}
	public GameObject getTop(){
		return top;
	}
	public GameObject getRight(){
		return right;
	}
	public GameObject getBottom(){
		return bottom;
	}
	
	//public int getPos(float X, float Y){
		
	//	int scaledx = (int)(X/width);
	//	int scaledy = (int)(Y/height);
	//}
	
	
	

	
	
}
