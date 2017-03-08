package audio;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import main.Application;

public class Sound {
	public static final AudioClip EnterScreen = Applet.newAudioClip(Sound.class.getResource("EnterScreen.wav"));
	public static final AudioClip Projectile = Applet.newAudioClip(Sound.class.getResource("Projectile.wav"));
	public static final AudioClip Walk = Applet.newAudioClip(Sound.class.getResource("Walk.wav"));
	public static final AudioClip Battle = Applet.newAudioClip(Sound.class.getResource("Battle.wav"));
	public static final AudioClip Loss = Applet.newAudioClip(Sound.class.getResource("Loss.wav"));
	public static final AudioClip Win = Applet.newAudioClip(Sound.class.getResource("Win.wav"));

	public static void main (String args[]){
		EnterScreen.loop();
		Projectile.loop();
		Walk.loop();
		Battle.loop();
		
		while(true){
			
		}
		
	}
}
