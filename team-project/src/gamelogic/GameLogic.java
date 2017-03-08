package gamelogic;

import java.util.ArrayList;
//<<<<<<< Updated upstream
import java.util.StringJoiner;

//=======
import java.awt.Font;

import org.newdawn.slick.Color;
//>>>>>>> Stashed changes
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
//<<<<<<< HEAD
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;

import ai.AIPlayer;
import audio.Sound;
import gamegraphics.Board;
import gamegraphics.Player;
import main.Application;

public class GameLogic {

	public static final int DEFAULTHEIGHT = Application.HEIGHT;
	public static final int DEFAULTWIDTH = Application.WIDTH;
	public static final int BOARDWIDTH = (int) (Board.DEFAULTWIDTH * 0.9);
	public static final int BOARDHEIGHT = (int) (Board.DEFAULTHEIGHT * 0.9);
	public static final int BOARDSTARTX = (int) (Board.DEFAULTWIDTH * 0.05);
	public static final int BOARDSTARTY = (int) (Board.DEFAULTHEIGHT * 0.05);
	static int wizardw = (int) (BOARDWIDTH * 0.1);
	static int wizardh = (int) (BOARDHEIGHT * 0.11);
	static int knightw = (int) (BOARDWIDTH * 0.07);
	static int knighth = (int) (BOARDHEIGHT * 0.1);
	static int charPX = (int) (BOARDWIDTH * 0.1);
	static int charPY = (int) (BOARDHEIGHT * 0.1);
	static int oppPX = charPX + (int) (BOARDWIDTH * 0.95);
	static int oppPY = charPY + (int) (BOARDHEIGHT * 0.9);
	static int charHealthX = charPX + (int) (wizardw);
	static int charHealthY = charPY - (int) (wizardh * 0.84);
	static int oppHealthX = oppPX - (int) (BOARDHEIGHT * 0.7);
	static int oppHealthY = charHealthY;
	static int charMPX = charHealthX;
	static int charMPY = charHealthY + (int) (charHealthY * 1.5);
	static int oppMPX = oppHealthX;
	static int oppMPY = charMPY;

	public static final int MAZECOLUMNS = 6;
	public static final int MAZEROWS = 4;
	public static final int MAZEBLOCKWIDTH = (int) (Board.BOARDWIDTH / MAZECOLUMNS);
	public static final int MAZEBLOCKHEIGHT = (int) (Board.BOARDHEIGHT / MAZEROWS);
	public static final int WALLWIDTH = 16;

	private ArrayList<Player> players;
	private ArrayList<AIPlayer> comPlayers;
	private int playerPos = 0;
	private int enemyPos = 1;
	private int aiPos = 0;
	private boolean isDead = false;
	private boolean hasWon = false;
	private boolean oppWon = false;
	private Board board;
	private boolean soundPlayed = false;

	public GameLogic(Board board) {
		this.board = board;
		this.players = board.players;
		this.comPlayers = board.comPlayers;

	}

	public void drawOnBoard(Graphics g) {
		if (players.get(playerPos).gethP() < 0) {
			players.get(playerPos).sethP(0);
		}

		if (board.type == 0 && comPlayers.get(aiPos) != null && comPlayers.get(aiPos).gethP() < 0) {
			comPlayers.get(aiPos).sethP(0);
		}

		if (board.type != 0 && players.get(enemyPos) != null && players.get(enemyPos).gethP() < 0) {
			players.get(enemyPos).sethP(0);
		}

		// Shows health bar and reduces when player collides with enemy
		Rectangle bar, bar2, bar3, bar4;
		GradientFill fill, fill2, fill3, fill4;

		if (players.get(playerPos).type == 2) {
			bar = new Rectangle(charHealthX, charHealthY, 300 * players.get(playerPos).gethP() / Player.HPDEFAULT, 10);
			fill = new GradientFill(charHealthX, 0, new Color(255, 40, 0), charHealthX + 300, 0, new Color(0, 255, 0));
			bar2 = new Rectangle(charMPX, charMPY, 200 * players.get(playerPos).getmP() / Player.MPDEFAULT, 10);
			fill2 = new GradientFill(charMPX, 0, new Color(0, 200, 255), charMPX + 200, 0, new Color(0, 0, 255));
		} else {
			bar = new Rectangle(charHealthX, charHealthY, 300 * players.get(playerPos).gethP() / Player.HPDEFAULT, 10);
			fill = new GradientFill(charHealthX, 0, new Color(255, 40, 0), charHealthX + 300, 0, new Color(0, 255, 0));
			bar2 = new Rectangle(charMPX, charMPY, 200 * players.get(playerPos).getmP() / Player.MPDEFAULT, 10);
			fill2 = new GradientFill(charMPX, 0, new Color(255, 100, 0), charMPX + 200, 0, new Color(255, 0, 0));
		}

		if (board.type != 0 && players.get(enemyPos) != null) {
			bar3 = new Rectangle(oppHealthX, oppHealthY, 300 * players.get(enemyPos).gethP() / Player.HPDEFAULT, 10);
			fill3 = new GradientFill(oppHealthX, 0, new Color(255, 40, 0), oppHealthX + 300, 0, new Color(0, 255, 0));
			bar4 = new Rectangle(oppMPX, oppMPY, 200 * players.get(enemyPos).getmP() / Player.MPDEFAULT, 10);
			fill4 = new GradientFill(oppMPX, 0, new Color(0, 200, 255), oppMPX + 200, 0, new Color(0, 0, 255));
		} else {
			bar3 = new Rectangle(oppHealthX, oppHealthY, 300 * comPlayers.get(aiPos).gethP() / Player.HPDEFAULT, 10);
			fill3 = new GradientFill(oppHealthX, 0, new Color(255, 40, 0), oppHealthX + 300, 0, new Color(0, 255, 0));
			bar4 = new Rectangle(oppMPX, oppMPY, 200 * comPlayers.get(aiPos).getmP() / Player.MPDEFAULT, 10);
			fill4 = new GradientFill(oppMPX, 0, new Color(0, 200, 255), oppMPX + 200, 0, new Color(0, 0, 255));
		}

		if (players.get(playerPos).gethP() > 0
				&& ((board.type == 0 && comPlayers.get(aiPos) != null && comPlayers.get(aiPos).gethP() > 0)
						|| (board.type != 0 && players.get(enemyPos) != null && players.get(enemyPos).gethP() > 0))) {

			// Health and Magic Bars for the characters.

			if (players.get(playerPos).type == 1) {
				g.setColor(Color.white);
				g.drawString("HP: " + players.get(playerPos).gethP(), charHealthX - (int) (wizardw * 0.75),
						charHealthY - (int) (wizardh * 0.09));
				g.drawString("EN: " + players.get(playerPos).getmP(), charMPX - (int) (wizardw * 0.75),
						charMPY - (int) (wizardh * 0.05));
			}

			if (players.get(playerPos).type == 2) {
				g.setColor(Color.white);
				g.drawString("HP: " + players.get(playerPos).gethP(), charHealthX - (int) (wizardw * 0.75),
						charHealthY - (int) (wizardh * 0.09));
				g.drawString("MP: " + players.get(playerPos).getmP(), charMPX - (int) (wizardw * 0.75),
						charMPY - (int) (wizardh * 0.05));
			}

			if (board.type != 0 && players.get(enemyPos) != null) {
				g.drawString(players.get(enemyPos).gethP() + " :HP", oppHealthX + 225 + (int) (wizardw * 0.75),
						oppHealthY - (int) (wizardh * 0.09));
				g.drawString(players.get(enemyPos).getmP() + " :MP", oppMPX + 225 + (int) (wizardw * 0.75),
						oppMPY - (int) (wizardh * 0.05));
			} else {
				g.drawString(comPlayers.get(aiPos).gethP() + " :HP", oppHealthX + 225 + (int) (wizardw * 0.75),
						oppHealthY - (int) (wizardh * 0.09));
				g.drawString(comPlayers.get(aiPos).getmP() + " :MP", oppMPX + 225 + (int) (wizardw * 0.75),
						oppMPY - (int) (wizardh * 0.05));
			}

			g.setColor(Color.darkGray);
			g.fillRect(charHealthX, charHealthY, 300, 10);
			g.fill(bar, fill);
			g.fillRect(charMPX, charMPY, 200, 10);
			g.fill(bar2, fill2);
			g.fillRect(oppHealthX, oppHealthY, 300, 10);
			g.fill(bar3, fill3);
			g.fillRect(oppMPX, oppMPY, 200, 10);
			g.fill(bar4, fill4);

			// Maze and map background
			g.drawImage(board.getMapBG().getScaledCopy(BOARDWIDTH, BOARDHEIGHT), BOARDSTARTX, BOARDSTARTY);

			for (int i = 0; i < board.getMaze().length; i++) {
				board.getMaze()[i].draw(g);
			}

			// The Characters
			players.get(playerPos).draw(g, board.container);

			if (board.type == 0) {
				comPlayers.get(aiPos).draw(g);
			}
			// player2.draw(g,container);
			if (board.type != 0) {
				players.get(enemyPos).draw(g, board.container);
			}

			for (int i = 0; i < board.collision.shotMax; i++) {
				// System.out.println("in for loop");
				if (board.collision.shots[i] != null) {
					// System.out.println("in if statement");
					board.collision.shots[i].draw(g);
				}
			}

			/*
			 * For debugging purposes. if(collision.slash != null) {
			 * collision.slash.draw(g); }
			 */
		} else {
			if (players.get(playerPos).gethP() <= 0) {
				isDead = true;
				UnicodeFont ufont = null;

				try {
					ufont = getNewFont("Onyx", 100);

				} catch (SlickException e) {
					e.printStackTrace();
				}

				if (!soundPlayed) {
					Sound.Battle.stop();
					soundPlayed = true;
				}

				g.setColor(Color.white);
				g.setFont(ufont);
				g.drawString("GAME OVER!!", (int) (BOARDWIDTH * 0.4), (int) (BOARDHEIGHT * 0.5));

			} else if (board.type == 0 && comPlayers.get(aiPos) != null && comPlayers.get(aiPos).gethP() <= 0) {

				hasWon = true;
				UnicodeFont ufont = null;

				try {
					ufont = getNewFont("Onyx", 100);

				} catch (SlickException e) {
					e.printStackTrace();
				}

				if (!soundPlayed) {
					Sound.Battle.stop();
					Sound.Win.play();
					soundPlayed = true;
				}

				g.setColor(Color.white);
				g.setFont(ufont);
				g.drawString("Victory!!!", (int) (BOARDWIDTH * 0.4), (int) (BOARDHEIGHT * 0.5));
			} else if (board.type != 0 && players.get(enemyPos) != null && players.get(playerPos).gethP() <= 0) {

				oppWon = true;
				UnicodeFont ufont = null;

				try {
					ufont = getNewFont("Onyx", 100);

				} catch (SlickException e) {
					e.printStackTrace();
				}

				if (!soundPlayed) {
					Sound.Battle.stop();
					Sound.Loss.play();
					soundPlayed = true;
				}

				g.setColor(Color.white);
				g.setFont(ufont);
				g.drawString("Victory for Opponent!!!", (int) (BOARDWIDTH * 0.4), (int) (BOARDHEIGHT * 0.5));
			}
		}

	}

	public UnicodeFont getNewFont(String fontName, int fontSize) throws SlickException {
		UnicodeFont ufont = null;
		Font font = new Font(fontName, Font.BOLD, fontSize);
		ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
		ufont.addAsciiGlyphs();
		ufont.addGlyphs(400, 600);
		ufont.getEffects().add(new ColorEffect(java.awt.Color.RED));
		ufont.loadGlyphs();
		return ufont;
	}

	public static UnicodeFont getNewFontC(String fontName, int fontSize, ColorEffect effect) throws SlickException {
		UnicodeFont ufont = null;
		Font font = new Font(fontName, Font.BOLD, fontSize);
		ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
		ufont.addAsciiGlyphs();
		ufont.addGlyphs(400, 600);
		ufont.getEffects().add(effect);
		ufont.loadGlyphs();
		return ufont;
	}

	public void updateBoard(int delta, String update) {
		if (!isDead && !hasWon && !oppWon) {

			if (board.type != 0) {
				for (int i = 0; i < players.size(); i++) {
					for (int j = 0; j < players.size(); j++) {
						if (i != j && players.get(i) != null && players.get(i).isMe
								&& players.get(i).collidesWith(players.get(j))) {
							players.get(i).collidedWith(players.get(j));
						}
					}
					players.get(i).setBump(board.collision.mazeCollision(board.getMaze(), players.get(i).getX(),
							players.get(i).getY()));
				}
			}

			if (board.type == 0) {
				for (int i = 0; i < players.size(); i++) {
					for (int j = 0; j < comPlayers.size(); j++) {
						if (comPlayers.get(i) != null && players.get(i).collidesWith(comPlayers.get(j))) {
							players.get(i).collidedWith(comPlayers.get(j));
							comPlayers.get(j).collidedWith(players.get(i));
						}
					}
					players.get(i).setBump(board.collision.mazeCollision(board.getMaze(), players.get(i).getX(),
							players.get(i).getY()));
				}

			}

			if (players.get(playerPos).isMe) {
				players.get(playerPos).move(board.container, delta);
			}

			if (board.type != 0) {
				board.collision.collideBoard(players.get(playerPos), players.get(enemyPos), board.container, null);

				players.get(enemyPos).updateEnemy(update);
			}

			if (board.type == 0) {
				board.collision.collideBoard(players.get(playerPos), null, board.container, comPlayers.get(aiPos));

				comPlayers.get(aiPos).AI(players.get(playerPos).getX(), players.get(playerPos).getY());

			}

		} else {

		}

	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isHasWon() {
		return hasWon;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	public boolean isOppWon() {
		return oppWon;
	}

	public void setOppWon(boolean oppWon) {
		this.oppWon = oppWon;
	}

}
