package gamegraphics;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Ammo extends GameObject {
	//protected int width, height;
	protected String character = "Wizard";
	//protected BufferedImage entityImage;
	//protected BufferedImage ammo;
	protected File f1;
	protected int mouseCount;
	protected String f;
	protected Image ammo;
	
	public Ammo(float x, float y, int width, int height){
		super(x, y, width, height);
		initAmmo();
	}
	
	
	public void initAmmo(){
		f = "images/Wizard/Ammo.png";
		try{
			ammo = new Image(f);
			entityImage = ammo; 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void move(float x1, float x2, float y1, float y2)
	{
		float speed = 5;
		//(x1, y1) is the character position
		//(x2, y2) is the mouse position
		float x3 = x1 - 18 - x2;
		float y3 = y1 - 18 - y2;
		if(y3 == 0){
			y3 = 1;
		}
		double tan = x3/y3;
		double angle = Math.atan(tan);
		
		if(y3 < 0)
		{
			y += (float) (speed * Math.cos(angle));
			x += (float) (speed * Math.sin(angle));
		}
		else if (y3 > 0)
		{
			y -= (float) (speed * Math.cos(angle));
			x -= (float) (speed * Math.sin(angle));
		}
		
		//x1 = x1;
		//y1 = y1;
		//x2 = x2+18;
		//y2 = y2+18;

	}
	
	/**
	 * Creates the hitbox for the sword type attack called Slash!
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 */
	public void slashPoint(float x1, float x2, float y1, float y2)
	{
		float speed = 10;
		//(x1, y1) is the character position
		//(x2, y2) is the mouse position
		float x3 = x1 - 18 - x2;
		float y3 = y1 - 18 - y2;
		if(y3 == 0){
			y3 = 1;
		}
		double tan = x3/y3;
		double angle = Math.atan(tan);
		
		if(y3 < 0)
		{
			y += (float) (speed * Math.cos(angle));
			x += (float) (speed * Math.sin(angle));
		}
		else if (y3 > 0)
		{
			y -= (float) (speed * Math.cos(angle));
			x -= (float) (speed * Math.sin(angle));
		}
		
		//x1 = x1;
		//y1 = y1;
		//x2 = x2+18;
		//y2 = y2+18;

	}
	
	
	
//	public void draw(Graphics g){
//		g.drawImage(entityImage.getScaledCopy(width, height), (int) x- width/2, (int) y - height/2);
//		
//	}
//	
//	

}
