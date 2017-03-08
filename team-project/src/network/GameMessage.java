package network;

import java.util.Observable;


// Class that gets all the changes in your character
public class GameMessage extends Observable{
	

	
	private String changes;

	//private boolean isAttacking;
	//private int posY;
	//private int posX;

	GameMessage(String changes){
		//this.posX = posX;
		//this.posY = posY;
		//this.isAttacking = isAttacking;
		this.changes = changes;
	
	}
	
	public String getChanges(){
		return changes;
	}
	
	/*public int getPosY(){
		return posY;
	}
	public boolean isAttacking(){
		return isAttacking;
	}
	*/
	public void setChanges(String str){
		//posX = x;
		changes = str;
		setChanged();
		//Made changes here - Isa.
		notifyObservers(changes);
	}
	/*public void setPosY(int y){
		posY = y;
		setChanged();
		notifyObservers();
	}
	
	public void setAttack(boolean at){
		isAttacking = at;
		setChanged();
		notifyObservers();
	}*/
	/*
	public void setPosx(int x){
		posX = x;
	}
	*/
	
}
